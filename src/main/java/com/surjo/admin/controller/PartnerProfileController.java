package com.surjo.admin.controller;


import com.surjo.admin.model.Profile;
import com.surjo.admin.model.Type;
import com.surjo.admin.service.ProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/profile",produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerProfileController {

    private final ProfileService profileService;

    public PartnerProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProfile(@Valid @RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity getAllProfile() {
        List<Profile> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getProfileById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping(path = "/userid/{userid}")
    public ResponseEntity getProfileByUserId(@PathVariable("userid") String userid) {
        return ResponseEntity.ok(profileService.getProfileByUserId(userid));
    }


    @DeleteMapping
    public ResponseEntity deleteProfile(@RequestParam(name="id",required = true)Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.accepted().build();
    }
}
