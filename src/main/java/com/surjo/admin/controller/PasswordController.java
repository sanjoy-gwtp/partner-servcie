package com.surjo.admin.controller;


import com.surjo.admin.response.OtpResponse;
import com.surjo.admin.service.PasswordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sanjoy on 5/17/17.
 */
@RestController
@RequestMapping(path = "/password")
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @RequestMapping(method = RequestMethod.GET,path = "otp")
    public OtpResponse getOtp(@RequestParam String mobileNumber) {
        return passwordService.request(mobileNumber);
    }

    @RequestMapping(method = RequestMethod.GET,path = "forgot")
    public OtpResponse forgot(@RequestParam String secret,@RequestParam String otp) {
        return passwordService.verify(secret,otp);
    }

}
