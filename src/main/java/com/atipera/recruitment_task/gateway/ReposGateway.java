package com.atipera.recruitment_task.gateway;

import com.atipera.recruitment_task.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@PropertySource("classpath:main_application.properties")
public class ReposGateway {

    private final RestTemplate restTemplate;
    private final String reposUrl;

    @Autowired
    public ReposGateway(
            final RestTemplate restTemplate,
            @Value("${notForkedRepos.url}") final String reposUrl) {

        this.restTemplate = restTemplate;
        this.reposUrl = reposUrl;
    }
    public Optional<Response> getReposByRepoName() {
        String url = String.format("%s", reposUrl);
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, Response.class));
        } catch (RestClientException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
