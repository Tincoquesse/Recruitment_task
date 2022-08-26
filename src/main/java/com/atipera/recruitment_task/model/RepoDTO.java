package com.atipera.recruitment_task.model;

import java.util.List;

public class RepoDTO {
    private final String repoName;
    private final String ownerLogin;
    private List<BranchDTO> branches;

    public RepoDTO(String repoName, String ownerLogin, List<BranchDTO> branches) {
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

    public List<BranchDTO> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDTO> branches) {
        this.branches = branches;
    }
}
