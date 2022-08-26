package com.atipera.recruitment_task.model;

import java.util.ArrayList;
import java.util.List;

public class RepoDTO {
    private final String repoName;
    private final String ownerLogin;
    private List<Branch> branches = new ArrayList<>();

    public RepoDTO(String repoName, String ownerLogin, List<Branch> branches) {
        this.repoName = repoName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}
