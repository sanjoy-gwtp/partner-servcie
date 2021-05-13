package com.surjo.admin.controller;


import com.surjo.admin.response.OtpResponse;
import com.surjo.admin.service.TotpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sanjoy on 5/17/17.
 */
@RestController
@RequestMapping(path = "/twofa/")
public class TotpController {

    @Autowired
    TotpService totpService;

    @RequestMapping(method = RequestMethod.GET,path = "pass")
    public OtpResponse getOneTimePass(@RequestParam String mobileNumber) {
        return totpService.getTotp(mobileNumber);
    }

    @RequestMapping(method = RequestMethod.GET,path = "verify")
    public OtpResponse verifyOneTimePass(@RequestParam String secret,@RequestParam String otp) {
        return totpService.verifyTotp(secret,otp);
    }

}
