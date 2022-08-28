package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.exception.HeaderValueException;
import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class RestSpringbootServiceImpl implements RestSpringbootService {

    @Autowired
    private ObjectMapper mapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Repo> getNotForkedRepos(String username) {
        try {
            String uri = "https://api.github.com/search/repositories?q=user:" + username + " forks:0";
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
