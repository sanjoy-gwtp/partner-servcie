package com.surjo.admin.exception;

/**
 * @author sanjoy
 * on 1/3/21
 */

public class InsufficientFundException extends PartnerException{

    public InsufficientFundException() {
        super("", "Insufficient Fund!");
    }
}
