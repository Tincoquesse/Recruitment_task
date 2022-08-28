package com.atipera.recruitment_task.model;

public class BranchDTO {
    private final String branchName;
    private final String lastCommitSha;

    public BranchDTO(String commitName, String commitSha) {
        this.branchName = commitName;
        this.lastCommitSha = commitSha;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

}
