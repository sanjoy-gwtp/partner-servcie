package com.surjo.admin.repository;

import com.surjo.admin.entity.AppVersionEntity;
import com.surjo.admin.model.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersionEntity,Long> {

    AppVersionEntity findFirstByOrderByIdDesc();


}
