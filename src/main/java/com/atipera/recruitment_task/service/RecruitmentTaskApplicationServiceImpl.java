package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.RepoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentTaskApplicationServiceImpl implements RecruitmentTaskApplicationService{

    private final GithubClientService githubClientService;

    public RecruitmentTaskApplicationServiceImpl(GithubClientService githubClientService) {
        this.githubClientService = githubClientService;
    }

    @Override
    public List<RepoDTO> getReposForResponse(String username) {
        List<Repo> repos = githubClientService.getNotForkedRepos(username);
        repos.forEach(repo ->
                repo.setBranches(githubClientService.getBranchesFromRepo(username, repo.getName())));
        return repos.stream()
                .map(RepoMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
