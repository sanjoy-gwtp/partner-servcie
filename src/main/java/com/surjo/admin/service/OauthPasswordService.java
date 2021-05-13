package com.surjo.admin.service;


import com.surjo.admin.model.Credential;
import com.surjo.admin.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "oauth", url = "http://oauth:8080/oauth")
public interface OauthPasswordService {
    @RequestMapping(method = RequestMethod.POST, value = "/password/forgot", consumes = "application/json")
    boolean forgotPassword(@RequestBody Credential credential);
}
