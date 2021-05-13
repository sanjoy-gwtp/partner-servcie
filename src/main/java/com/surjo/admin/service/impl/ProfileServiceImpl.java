package com.surjo.admin.service.impl;

import com.surjo.admin.entity.ProfileEntity;
import com.surjo.admin.model.Product;
import com.surjo.admin.model.Profile;
import com.surjo.admin.repository.ProfileRepository;
import com.surjo.admin.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Profile createProfile(Profile profile) {
        ProfileEntity profileEntity=profileRepository.save(modelMapper.map(profile, ProfileEntity.class));
        return modelMapper.map(profileEntity,Profile.class);
    }

    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.
                findAll().stream()
                .map(profileEntity -> modelMapper.map(profileEntity,Profile.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.delete(profileRepository.getOne(id));
    }

    @Override
    public Profile getProfileById(Long id) {
        return modelMapper.map(profileRepository.getOne(id),Profile.class);
    }

    @Override
    public Profile getProfileByUserId(String userId) {
       ProfileEntity profileEntity= profileRepository.findProfileEntityByUserId(userId);
       if(profileEntity!=null){
           return modelMapper.map(profileEntity,Profile.class);
       }
       return new Profile();
    }
}
