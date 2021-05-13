package com.surjo.admin.service;

import com.surjo.admin.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile createProfile(Profile profile);

    List<Profile> getAllProfile();

    void deleteProfile(Long id);

    Profile getProfileById(Long id);

    Profile getProfileByUserId(String userId);
}
