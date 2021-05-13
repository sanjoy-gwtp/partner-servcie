package com.surjo.admin.exception;

/**
 * @author sanjoy
 * on 1/3/21
 */

public class ChildLimitException extends PartnerException{

    public ChildLimitException() {
        super("550", "Child Not More Than 4!");
    }
}
