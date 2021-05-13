package com.surjo.admin.service;

import com.surjo.admin.response.OtpResponse;

/**
 * Created by sanjoy on 5/17/17.
 */
public interface PasswordService {
    OtpResponse request(String mobileNumber);
    OtpResponse verify(String secret,String token);
}
