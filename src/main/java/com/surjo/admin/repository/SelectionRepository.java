package com.surjo.admin.repository;

import com.surjo.admin.entity.SelectionEntity;
import com.surjo.admin.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionRepository extends JpaRepository<SelectionEntity,Long> {

    SelectionEntity findByUserIdAndVote(String userId, VoteEntity vote);

}
