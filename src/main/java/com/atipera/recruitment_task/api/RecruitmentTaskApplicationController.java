package com.atipera.recruitment_task.api;

import com.atipera.recruitment_task.model.RepoDTO;
import com.atipera.recruitment_task.service.RecruitmentTaskApplicationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RecruitmentTaskApplicationController {

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
}
