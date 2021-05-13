package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author sanjoy
 * on 1/3/21
 */
@Getter
@Setter
@Accessors(chain = true)
public class PaymentMethod implements Serializable {
    private Long id;
    private String name;
    private String number;

}
