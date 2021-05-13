package com.surjo.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "selection")
@Getter
@Setter
@Accessors(chain = true)
public class SelectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Boolean selection;
    @OneToOne
    private VoteEntity vote;
}

