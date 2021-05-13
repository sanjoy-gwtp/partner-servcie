package com.surjo.admin.model;



import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Picture implements Serializable {
    private Long id;
    private String small;
    private String smallImageName;
    private String smallImageType;
    private String big;
    private String bigImageName;
    private String bigImageType;
    private Long product;
}
