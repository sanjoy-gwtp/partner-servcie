package com.surjo.admin.model;

import com.surjo.admin.entity.VideoType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Getter
@Setter
@Accessors(chain = true)
public class Video implements Serializable {
    private Long id;
    private String url;
    private String description;
    private VideoType videoType;
    private Boolean isActive;
}
