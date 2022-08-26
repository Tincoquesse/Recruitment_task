package com.atipera.recruitment_task.api;

import com.atipera.recruitment_task.exception.UserNotFoundException;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.service.RestSpringbootService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestSpringbootController {


    private final RestSpringbootService service;

    public RestSpringbootController(RestSpringbootService service) {
        this.service = service;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Repo>> getUserData(@PathVariable String username) {

        List<Repo> repos = this.service.getNotForkedRepos(username);
        if (repos == null) {
            throw new UserNotFoundException("\"User \" + username + \" was not found\"");
        }
        repos.forEach(repo -> {
                    repo.setBranches(this.service.getBranchesFromRepo(username, repo.getName()));
                }
        );

        return ResponseEntity.ok(repos);
    }
}
