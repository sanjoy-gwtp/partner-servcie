package com.surjo.admin.service.impl;

import com.surjo.admin.entity.AppVersionEntity;
import com.surjo.admin.model.AppVersion;
import com.surjo.admin.repository.AppVersionRepository;
import com.surjo.admin.service.AppVersionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppVersionServiceImpl implements AppVersionService {

    private final AppVersionRepository appVersionRepository;
    private final ModelMapper modelMapper;

    public AppVersionServiceImpl(AppVersionRepository appVersionRepository, ModelMapper modelMapper) {
        this.appVersionRepository = appVersionRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public AppVersion createAppVersion(AppVersion appVersion) {
        AppVersionEntity entity=appVersionRepository.save(modelMapper.map(appVersion,AppVersionEntity.class));
        return modelMapper.map(entity,AppVersion.class);
    }

    @Override
    public List<AppVersion> getAllAppVersion() {
        return appVersionRepository.findAll().stream().map(appVersionEntity -> modelMapper.map(appVersionEntity,AppVersion.class)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAppVersion(Long id) {
        appVersionRepository.delete(appVersionRepository.getOne(id));
    }

    @Override
    public AppVersion getAppVersionById(Long id) {
        return modelMapper.map(appVersionRepository.getOne(id),AppVersion.class);
    }

    @Override
    public AppVersion getLatestAppVersion() {
        return modelMapper.map(appVersionRepository.findFirstByOrderByIdDesc(),AppVersion.class);
    }
}
