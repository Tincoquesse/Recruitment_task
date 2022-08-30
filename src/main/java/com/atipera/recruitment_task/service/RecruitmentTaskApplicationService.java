package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.RepoDTO;

import java.util.List;

public interface RecruitmentTaskApplicationService {

    List<RepoDTO> getReposForResponse(String username);
}
