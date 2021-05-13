package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Category implements Serializable {
    private Long id;
    private String name;
}
