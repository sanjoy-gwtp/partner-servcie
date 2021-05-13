package com.surjo.admin.service;

import com.surjo.admin.response.OtpResponse;

/**
 * Created by sanjoy on 5/17/17.
 */
public interface TotpService {
    OtpResponse getTotp(String mobileNumber);
    OtpResponse verifyTotp(String secret,String token);
}
