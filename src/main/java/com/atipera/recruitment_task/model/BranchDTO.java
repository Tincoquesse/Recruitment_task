package com.atipera.recruitment_task.model;

public class BranchDTO {
    private String commitName;
    private String commitSha;

    public BranchDTO(String commitName, String commitSha) {
        this.commitName = commitName;
        this.commitSha = commitSha;
    }

    public String getCommitName() {
        return commitName;
    }

    public String getCommitSha() {
        return commitSha;
    }

}
