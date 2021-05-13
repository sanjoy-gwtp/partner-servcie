package com.surjo.admin.service.impl;


import com.surjo.admin.response.OtpResponse;
import com.surjo.admin.service.SmsNotificationService;
import com.surjo.admin.service.TotpService;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.jboss.aerogear.security.otp.api.Clock;
import org.springframework.stereotype.Service;

/**
 * Created by sanjoy on 5/17/17.
 */
@Service
public class TotpServiceImpl implements TotpService {

    private final SmsNotificationService notificationService;

    public TotpServiceImpl(SmsNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public OtpResponse getTotp(String mobileNumber) {
        String secret = Base32.random();
        Totp totp=new Totp(secret,new Clock(300));
        String otp=totp.now();
        try {
            System.out.println("OTP "+otp);
            notificationService.sendSms(mobileNumber,"Your OTP is : "+otp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new OtpResponse(secret,otp);
    }

    @Override
    public OtpResponse verifyTotp(String secret, String otp) {
        Totp totp=new Totp(secret,new Clock(300));
        return new OtpResponse(totp.verify(otp));
    }
}
