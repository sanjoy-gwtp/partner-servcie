package com.surjo.admin.exception;

/**
 * @author sanjoy
 * on 1/3/21
 */

public class ReferenceNoFundException extends PartnerException{

    public ReferenceNoFundException() {
        super("550", "Reference Not Fund!");
    }
}
