package com.surjo.admin.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class SSLSMSResponse implements Serializable {
    private String status;
    private String status_code;
    private String error_message;
}
