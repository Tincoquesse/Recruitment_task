package com.atipera.recruitment_task.api;

import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.RepoDTO;
import com.atipera.recruitment_task.service.RecruitmentTaskApplicationService;
import com.atipera.recruitment_task.service.RepoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RecruitmentTaskApplicationController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final RecruitmentTaskApplicationService taskApplicationService;

    public RecruitmentTaskApplicationController(RecruitmentTaskApplicationService taskApplicationService) {
        this.taskApplicationService = taskApplicationService;
    }

    @GetMapping(value = "/user/{username}/repos")
    public ResponseEntity<List<RepoDTO>> getUserData(@RequestHeader(value = HttpHeaders.ACCEPT)
                                                     @PathVariable String username) {
        List<RepoDTO> reposForResponse = taskApplicationService.getReposForResponse(username);
        return new ResponseEntity<>(reposForResponse, HttpStatus.OK);
    }

    @GetMapping("/repos")
    public Flux<RepoDTO> getRepos (){

        List<String> repoNames = new ArrayList<>();
        repoNames.add("Alledrogo_Frontend");
        repoNames.add("Recruitment_task");

        Flux<Repo> repos = Flux.fromIterable(repoNames).flatMap(x -> {
            return webClientBuilder.build()
                    .get()
                    .uri("https://api.github.com/repos/Tincoquesse/{repoName}/branches", x)
                    .retrieve()
                    .bodyToMono(Repo[].class)
                    .map(Arrays::asList)
                    .flatMapIterable(ls->ls);
        });
        System.out.printf(repos.toString());
        return repos.map(RepoMapper::fromEntity);
    }
}
