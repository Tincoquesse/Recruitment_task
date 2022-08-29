package com.atipera.recruitment_task.gateway;

import com.atipera.recruitment_task.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@PropertySource("classpath:main_application.properties")
public class BranchesGateway {

    private final RestTemplate restTemplate;
    private final String reposUrl;

    @Autowired
    public BranchesGateway(
            final RestTemplate restTemplate,
            @Value("${branchesFromRepo.url}") final String reposUrl) {

        this.restTemplate = restTemplate;
        this.reposUrl = reposUrl;
    }
    public Optional<Branch[]> getBranchesByUsername() {
        String url = String.format("%s", reposUrl);
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, Branch[].class));
        } catch (RestClientException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
