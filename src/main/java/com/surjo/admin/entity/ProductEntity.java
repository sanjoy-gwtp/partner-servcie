package com.surjo.admin.entity;

import com.surjo.asset.repository.schema.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sanjoy on 3/9/19.
 */

@Entity
@Table(name = "product")
@Getter
@Setter
@Accessors(chain = true)
public class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private BigDecimal discount;
    private String shortDetails;
    private String description;
    private Long stock;
    private String state;
    private Boolean newPro;
    private String brand;
    private Boolean sale;
    @OneToMany(mappedBy = "product")
    private List<PictureEntity> pictures;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
