package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@SpringBootTest
class GithubClientServiceTests {

    @Autowired
    GithubClientService restSpringbootService;

    private WireMockServer wireMockServer;

    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer();
        this.wireMockServer.start();
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }

    @Test
    public void shouldGetNotForkedRepos() throws IOException {
        //GIVEN
        wireMockServer.stubFor(WireMock.get(urlMatching("/search/repositories/?q=user:Tincoquesse%20fork:false"))
                .willReturn(aResponse()
                        .withBody(readRepos())
                        .proxiedFrom("https://api.github.com")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));
        //WHEN
        List<Repo> notForkedRepos = restSpringbootService.getNotForkedRepos("Tincoquesse");
        int size = notForkedRepos.size();

        //THEN
        assertThat(size).isEqualTo(11);
    }

    @Test
    public void shouldGetBranchesFromRepo() throws IOException {
        //GIVEN
        wireMockServer.stubFor(WireMock.get(urlMatching("/Tincoquesse/Alledrogo_Frontend/branches"))
                .willReturn(aResponse()
                        .withBody(readBranches())
                        .proxiedFrom("https://api.github.com/repos")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));
        //WHEN
        List<Branch> branchesFromRepo = restSpringbootService.getBranchesFromRepo("Tincoquesse", "Alledrogo_Frontend");
        int size = branchesFromRepo.size();

        //THEN
        assertThat(size).isEqualTo(3);

    }

    private String readRepos() throws IOException {
        File file = ResourceUtils.getFile("src\\test\\resources\\repos_response_success.json");
        return new String(Files.readAllBytes(file.toPath()));
    }

    private String readBranches() throws IOException {
        File file = ResourceUtils.getFile("src\\test\\resources\\branches_response_success.json");
        return new String(Files.readAllBytes(file.toPath()));
    }
}
