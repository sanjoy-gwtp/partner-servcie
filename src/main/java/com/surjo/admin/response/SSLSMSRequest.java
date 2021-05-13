package com.surjo.admin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Getter
@Setter
@Accessors(chain = true)
public class SSLSMSRequest implements Serializable {
     private String api_token;
     private String sid;
     private String msisdn;
     private String sms;
     private String csms_id;
}
