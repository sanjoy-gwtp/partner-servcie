package com.surjo.admin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Getter
@Setter
@Accessors(chain = true)
public class OtpResponse implements Serializable {
    private String secret;
    private String otp;
    private boolean status;

    public OtpResponse(String secret, String otp) {
        this.secret = secret;
        this.otp = otp;
    }

    public OtpResponse(boolean status) {
        this.status = status;
    }
}
