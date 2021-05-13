package com.surjo.admin.repository;

import com.surjo.admin.entity.VideoEntity;
import com.surjo.admin.entity.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity,Long> {
    List<VideoEntity> findVideoEntitiesByVideoTypeOrderByIdDesc(VideoType videoType);
}
