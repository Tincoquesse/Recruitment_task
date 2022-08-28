package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.naming.NameNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestSpringbootServiceImpl implements RestSpringbootService {

    @Autowired
    private ObjectMapper mapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Repo> getNotForkedRepos(String username) {
        try {
            String uri = "https://api.github.com/search/repositories?q=user:" + username + " forks:0";
            ResponseEntity<Object> responseEntity =
                    restTemplate.getForEntity(uri, Object.class);
            Object response = responseEntity.getBody();
            return List.of(mapper.convertValue(response, Response.class).getRepos());
        } catch (Exception e) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<Branch> getBranchesFromRepo(String username, String repoName) {

        String uri = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        ResponseEntity<Object[]> responseEntity =
                restTemplate.getForEntity(uri, Object[].class);
        Object[] objects = responseEntity.getBody();

        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Branch.class))
                .collect(Collectors.toList());
    }
}
