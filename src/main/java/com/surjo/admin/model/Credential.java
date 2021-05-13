package com.surjo.admin.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Credential implements Serializable {

    private CredentialType type;
    private String username;
    private String device;
    private String value;

    public Credential(CredentialType type, String device, String value) {
        this.type = type;
        this.device = device;
        this.value = value;
    }

    public Credential(CredentialType type, String username, String device, String value) {
        this.type = type;
        this.username = username;
        this.device = device;
        this.value = value;
    }

    public Credential() {

    }
}
