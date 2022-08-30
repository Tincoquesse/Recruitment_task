package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.RepoDTO;

import java.util.List;

public interface GithubClientService {

    List<Repo> getNotForkedRepos(String username);

    List<Branch> getBranchesFromRepo(String username, String repoName);


}
