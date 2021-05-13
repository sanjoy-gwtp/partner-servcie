package com.surjo.admin.exception;



public class PartnerException extends RuntimeException {

    private static final String MODULE = "PARTNER";
    private static final String BASE_CODE = "8000";

    public PartnerException(String code, String message) {
        super(code+message);
    }
}
