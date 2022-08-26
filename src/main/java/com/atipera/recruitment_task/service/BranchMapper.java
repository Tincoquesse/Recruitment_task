package com.atipera.recruitment_task.service;

import com.atipera.recruitment_task.model.Branch;
import com.atipera.recruitment_task.model.BranchDTO;

public class BranchMapper {

    public static BranchDTO fromEntity(Branch branch){
        return new BranchDTO(branch.getName(), branch.getCommit().getSha());
    }
}
