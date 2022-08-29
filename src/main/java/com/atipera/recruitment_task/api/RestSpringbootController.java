package com.atipera.recruitment_task.api;

import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.RepoDTO;
import com.atipera.recruitment_task.service.RepoMapper;
import com.atipera.recruitment_task.service.RestSpringbootService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class RestSpringbootController {

    private final RestSpringbootService service;

    public RestSpringbootController(RestSpringbootService service) {
        this.service = service;
    }

    @GetMapping(value = "/user/{username}/repos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RepoDTO>> getUserData(@RequestHeader(value = HttpHeaders.ACCEPT)
                                                     @PathVariable String username) {

        List<Repo> repos = this.service.getNotForkedRepos(username);
        repos.forEach(repo ->
                repo.setBranches(this.service.getBranchesFromRepo(username, repo.getName())));

        return ResponseEntity.ok(repos.stream()
                .map(RepoMapper::fromEntity)
                .collect(Collectors.toList()));
    }
}
