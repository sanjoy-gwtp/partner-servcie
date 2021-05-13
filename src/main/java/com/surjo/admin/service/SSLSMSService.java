package com.surjo.admin.service;


import com.surjo.admin.model.UserModel;
import com.surjo.admin.response.SSLSMSRequest;
import com.surjo.admin.response.SSLSMSResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "admin", url = "https://smsplus.sslwireless.com")
public interface SSLSMSService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/v3/send-sms", consumes = "application/json")
    SSLSMSResponse sendSMS(@RequestBody SSLSMSRequest request);
}
