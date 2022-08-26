package com.atipera.recruitment_task.model;

import java.util.List;

public class RepoDTO {
    private String repoName;
    private String ownerLogin;
    private List<Branch> branches;

    public RepoDTO(String repoName, String ownerLogin, List<Branch> branches) {
        this.repoName = repoName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }
}
