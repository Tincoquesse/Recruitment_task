package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Objects;

@Service
class GithubClientServiceImpl implements GithubClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public List<Repo> getNotForkedRepos(String username) {
        String uri = "https://api.github.com/search/repositories?q=user:" + username + " fork:false";
        try {
            Response response = webClientBuilder.build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(Response.class)
                    .block();
            return List.of(Objects.requireNonNull(response).getRepos());

        } catch (WebClientResponseException e) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Branch> getBranchesFromRepo(String username, String repoName) {
        String uri = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        try {
            Branch[] branches = webClientBuilder.build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(Branch[].class)
                    .block();
            return List.of(Objects.requireNonNull(branches));
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, " API rate limit exceeded");
        }
    }
}
