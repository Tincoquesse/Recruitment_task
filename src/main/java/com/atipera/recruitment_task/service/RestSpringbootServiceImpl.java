package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestSpringbootServiceImpl implements RestSpringbootService {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<Repo> getNotForkedRepos(String username) {
        String uri = "https://api.github.com/search/repositories?q=user:"+ username + " forks:0";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity(uri, Object.class);
        Object response = responseEntity.getBody();

        if (response == null){
            throw new UserNotFoundException("User " + username + "was not found");
        }
        return List.of(mapper.convertValue(response, Response.class).getRepos());
    }

    @Override
    public List<Branch> getBranchesFromRepo(String username, String repoName) {
        String uri = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object[]> responseEntity =
                restTemplate.getForEntity(uri, Object[].class);
        Object[] objects = responseEntity.getBody();

        assert objects != null;
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Branch.class))
                .collect(Collectors.toList());
    }
}
