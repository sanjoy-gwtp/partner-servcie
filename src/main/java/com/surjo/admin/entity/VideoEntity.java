package com.surjo.admin.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@Accessors(chain = true)
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(EnumType.STRING)
    private VideoType videoType;
    private Boolean isActive;
}
