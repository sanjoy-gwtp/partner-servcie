package com.surjo.admin.repository;

import com.surjo.admin.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Long> {

    ProfileEntity findProfileEntityByUserId(String userId);
}
