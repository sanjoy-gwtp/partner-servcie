package com.surjo.admin.service;


import com.surjo.admin.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "admin", url = "http://admin:8080/admin")
public interface UserService {
    @RequestMapping(method = RequestMethod.POST, value = "/user", consumes = "application/json")
    void createUser(@RequestBody UserModel userModel);
}
