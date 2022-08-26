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

    public void setCommitName(String commitName) {
        this.commitName = commitName;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }
}
