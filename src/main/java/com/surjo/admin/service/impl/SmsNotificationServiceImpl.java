package com.surjo.admin.service.impl;

import com.surjo.admin.response.SSLSMSRequest;
import com.surjo.admin.response.SSLSMSResponse;
import com.surjo.admin.service.SSLSMSService;
import com.surjo.admin.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by sanjoy on 5/17/17.
 */
@Service
public class SmsNotificationServiceImpl implements SmsNotificationService {

    @Value("${ssl.token:5}")
    private String token;

    @Value("${ssl.sid:5}")
    private String sid;

    private final SSLSMSService sslsmsService;

    public SmsNotificationServiceImpl(SSLSMSService sslsmsService) {
        this.sslsmsService = sslsmsService;
    }


    @Override
    public boolean sendSms(String mobileNo, String msg) throws Exception {
        SSLSMSRequest request=new SSLSMSRequest().setApi_token(token).setSid(sid)
                .setCsms_id(UUID.randomUUID().toString().replace("-","").substring(2))
                .setMsisdn(mobileNo).setSms(msg);
        SSLSMSResponse response=sslsmsService.sendSMS(request);
        return response.getStatus_code().equals("200");
        }
    }

