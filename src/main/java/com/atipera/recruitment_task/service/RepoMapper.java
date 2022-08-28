package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.Repo;
import com.atipera.recruitment_task.model.RepoDTO;

import java.util.stream.Collectors;


public class RepoMapper {

    public static RepoDTO fromEntity(Repo repo) {

        return new RepoDTO(repo.getName(), repo.getOwner().getLogin(),
                repo.getBranches().stream().map(BranchMapper::fromEntity).collect(Collectors.toList()));

    }
}
