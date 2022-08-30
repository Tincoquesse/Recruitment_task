package com.atipera.recruitment_task.gateway;

import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Response;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class GatewaysTest {

    @Autowired
    BranchesGateway branchesGateway;
    @Autowired
    ReposGateway reposGateway;

    private WireMockServer wireMockServer;

    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer(8090);
        this.wireMockServer.start();
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }

    @Test
    public void success_getBranchesBy_username_Tincoquesse() throws IOException {
        //GIVEN
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/repos/Tincoquesse/Alledrogo_Frontend/branches"))
                .willReturn(aResponse()
                        .withBody(readBranches())
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        //WHEN
        Optional<Branch[]> response = branchesGateway.getBranchesByUsername();
        List<Branch> branches = Arrays.stream(response.get()).toList();

        //THEN
        assertThat(response).isPresent();
        assertThat(branches.size()).isEqualTo(3);

    }

    @Test
    public void success_getReposBy_username_Tincoquesse() throws IOException {
        //GIVEN
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/search/repositories?q=user:Tincoquesse%20fork:false"))
                .willReturn(aResponse()
                        .withBody(readRepos())
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        //WHEN
        Optional<Response> reposByUsername = reposGateway.getReposByRepoName();
        int size = reposByUsername.get().getRepos().length;

        //THEN
        assertThat(reposByUsername).isPresent();
        assertThat(size).isEqualTo(11);

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

