package com.surjo.admin.repository;

import com.surjo.admin.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteEntity,Long> {
}
