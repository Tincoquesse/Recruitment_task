package com.atipera.recruitment_task;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class WireMockConfigTest {
    @Rule
    WireMockRule wireMockRule = new WireMockRule();

    @Test
    void test(){
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        RestTemplate restTemplate = new RestTemplate();

        wireMockServer.stubFor(get(urlMatching("/api")).willReturn(aResponse().withBody("Welcome to Baeldung!")));

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api", String.class);

        assertThat(response.getBody()).isEqualTo("Welcome to Baeldung!");
        wireMockServer.stop();


    }
}
