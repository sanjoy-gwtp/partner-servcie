package com.surjo.admin.repository;

import com.surjo.admin.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
}
