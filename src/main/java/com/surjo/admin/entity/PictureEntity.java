package com.surjo.admin.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "picture")
@Getter
@Setter
@Accessors(chain = true)
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] small;
    private String smallImageName;
    private String smallImageType;
    @Lob
    private byte[] big;
    private String bigImageName;
    private String bigImageType;
    @ManyToOne
    private ProductEntity product;
}
