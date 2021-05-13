package com.surjo.admin.controller;

import com.surjo.admin.model.AppVersion;
import com.surjo.admin.model.Cart;
import com.surjo.admin.service.AppVersionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/app-version",produces = MediaType.APPLICATION_JSON_VALUE)
public class AppVersionController {

    private final AppVersionService appVersionService;

    public AppVersionController(AppVersionService appVersionService) {
        this.appVersionService = appVersionService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAppVersion(@Valid @RequestBody AppVersion appVersion) {
        return ResponseEntity.ok(appVersionService.createAppVersion(appVersion));
    }

    @GetMapping
    public ResponseEntity getAllAppVersion() {
        List<AppVersion> appVersionList = appVersionService.getAllAppVersion();
        return ResponseEntity.ok(appVersionList);
    }

    @GetMapping(path = "/latest")
    public ResponseEntity getLatestAppVersion() {
        return ResponseEntity.ok(appVersionService.getLatestAppVersion());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getAppVersionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appVersionService.getAppVersionById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteAppVersion(@RequestParam(name="id",required = true)Long id) {
        appVersionService.deleteAppVersion(id);
        return ResponseEntity.accepted().build();
    }
}
