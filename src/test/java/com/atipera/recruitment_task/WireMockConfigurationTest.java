package com.atipera.recruitment_task;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class WireMockConfigurationTest {

    @Autowired
    private RestTemplate restTemplate;

    private WireMockServer wireMockServer;

    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer(options()
                .dynamicPort()
        );
        this.wireMockServer.start();
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }

    @Test
    @DisplayName("Should return the configured HTTP response")
    void shouldReturnHttpConfiguredHttpResponse() {
        givenThat(get(urlEqualTo("/api/search/repositories?q=user:Tincoquesse fork:false")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("json/repos_response_success.json")
        ));

        String serverUrl = buildApiMethodUrl("Tincoquesse");
        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl,
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType())
                .isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(response.getBody())
                .isEqualTo("{ \"message\": \"Hello World!\" }");
    }

    private String buildApiMethodUrl(String username) {
        return String.format("http://localhost:%d/api/search/repositories?q=user:%s fork:false",
                this.wireMockServer.port(), username
        );
    }

    @Test
    @DisplayName("Should ignore the request method and URL")
    void shouldIgnoreRequestMethod() {
        wireMockServer.stubFor(
                WireMock.get("/api/anything")
                        .willReturn(aResponse()
                                .withBody("[]")
                                .withStatus(200))
        );


        String serverUrl = buildApiMethodUrl();
        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl,
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private String buildApiMethodUrl() {
        return String.format("http://localhost:%d/api/anything",
                this.wireMockServer.port()
        );
    }

}
