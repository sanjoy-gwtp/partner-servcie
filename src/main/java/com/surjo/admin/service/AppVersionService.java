package com.surjo.admin.service;

import com.surjo.admin.model.AppVersion;
import com.surjo.admin.model.Cart;

import java.util.List;

public interface AppVersionService {

    AppVersion createAppVersion(AppVersion appVersion);

    List<AppVersion> getAllAppVersion();

    void deleteAppVersion(Long id);

    AppVersion getAppVersionById(Long id);

    AppVersion getLatestAppVersion();

}
