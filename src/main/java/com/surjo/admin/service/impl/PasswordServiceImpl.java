package com.surjo.admin.service.impl;


import com.surjo.admin.entity.OtpEntity;
import com.surjo.admin.model.Credential;
import com.surjo.admin.model.CredentialType;
import com.surjo.admin.model.Partner;
import com.surjo.admin.repository.OtpRepository;
import com.surjo.admin.response.OtpResponse;
import com.surjo.admin.service.OauthPasswordService;
import com.surjo.admin.service.PartnerService;
import com.surjo.admin.service.PasswordService;
import com.surjo.admin.service.SmsNotificationService;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.jboss.aerogear.security.otp.api.Clock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by sanjoy on 5/17/17.
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    private final String candidateChars = "1234567890";

    private final SmsNotificationService notificationService;
    private final PartnerService partnerService;
    private final OtpRepository otpRepository;
    private final OauthPasswordService passwordService;
    private final SmsNotificationService smsNotificationService;



    public PasswordServiceImpl(SmsNotificationService notificationService, PartnerService partnerService, OtpRepository otpRepository, OauthPasswordService passwordService, SmsNotificationService smsNotificationService) {
        this.notificationService = notificationService;
        this.partnerService = partnerService;
        this.otpRepository = otpRepository;
        this.passwordService = passwordService;
        this.smsNotificationService = smsNotificationService;
    }

    @Transactional
    public OtpResponse request(String mobileNumber) {
        Partner partner= partnerService.getPartnerByMobileNumber(mobileNumber);
        if (partner!=null) {
            String secret = Base32.random();
            Totp totp = new Totp(secret, new Clock(300));
            String otp = totp.now();
            try {
                System.out.println("OTP " + otp);
                if(notificationService.sendSms(mobileNumber, "Your OTP is : " + otp)){
                    otpRepository.save(new OtpEntity(otp,mobileNumber,mobileNumber));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return new OtpResponse(secret, otp);
        }else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public OtpResponse verify(String secret, String otp) {
        Totp totp=new Totp(secret,new Clock(300));
        if(totp.verify(otp)){
            OtpEntity otpEntity=otpRepository.findOtpEntityByOtp(otp);
            String pass=generateRandomChars(candidateChars,6);
            System.out.println(otpEntity.getMobileNumber()+": "+pass);
            if(passwordService.forgotPassword(new Credential(CredentialType.PASSWORD,otpEntity.getUserId(),"web",pass))){
                try {
                    smsNotificationService.sendSms(otpEntity.getMobileNumber(),
                            "Dear Customer." + "\n"
                                    + "Your New Password : " + pass + "\n"
                                    + "www.dsell3.com");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new OtpResponse(true);
        }else {
           return new OtpResponse(false);
        }
    }

    private String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }


}
