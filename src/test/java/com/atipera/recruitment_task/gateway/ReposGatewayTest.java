package com.atipera.recruitment_task.gateway;

import com.atipera.recruitment_task.model.Response;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReposGatewayTest {

    @Autowired
    private ReposGateway reposGateway;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void success_getReposBy_username_Tincoquesse() throws IOException {
        //GIVEN
        wireMockRule.stubFor(WireMock.get(urlPathEqualTo("1"))
                .willReturn(aResponse()
                        .withBody(read())
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));


        //WHEN
        Optional<Response> response = reposGateway.getReposByUsername();

        //THEN
        assertThat(response).isPresent();

    }
    private String read() throws IOException {
        File file = ResourceUtils.getFile("src\\main\\post\\repos_response_success.json");
        return new String(Files.readAllBytes(file.toPath()));
    }

}
