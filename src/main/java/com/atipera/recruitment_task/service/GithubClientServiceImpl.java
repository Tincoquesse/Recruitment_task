package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class GithubClientServiceImpl implements GithubClientService {

    private final RestTemplate restTemplate;

    public GithubClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Repo> getNotForkedRepos(String username) {
        try {
            String uri = "https://api.github.com/search/repositories?q=user:" + username + " fork:false";
            Response response = restTemplate.getForEntity(uri, Response.class).getBody();
            return List.of(Objects.requireNonNull(response).getRepos());
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Branch> getBranchesFromRepo(String username, String repoName) {
        String uri = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        Branch[] branches = restTemplate.getForEntity(uri, Branch[].class).getBody();
        return List.of(Objects.requireNonNull(branches));
    }
}
