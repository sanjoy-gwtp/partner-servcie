package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by sanjoy on 7/14/18.
 */
@Getter
@Setter
@Accessors(chain = true)
public class RightModel implements Serializable {

    private Long id;
    private String name;
    private String description;

}
