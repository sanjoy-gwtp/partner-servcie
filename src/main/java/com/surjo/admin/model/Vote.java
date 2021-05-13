package com.surjo.admin.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Accessors(chain = true)
public class Vote implements Serializable {
    private Long id;
    private String description;
    private Boolean isActive;
}
