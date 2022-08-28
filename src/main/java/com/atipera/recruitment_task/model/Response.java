package com.atipera.recruitment_task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("items")
    private Repo[] repos;

    public Repo[] getRepos() {
        return repos;
    }

    public void setRepos(Repo[] repos) {
        this.repos = repos;
    }

}
