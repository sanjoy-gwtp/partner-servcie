package com.surjo.admin.exception;

public class InvalidCredential extends PartnerException {
    public InvalidCredential() {
        super("400", "Invalid Credential");
    }
}
