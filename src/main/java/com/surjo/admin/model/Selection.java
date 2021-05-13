package com.surjo.admin.model;

import com.surjo.admin.entity.VoteEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Accessors(chain = true)
public class Selection implements Serializable {
    private Long id;
    private String userId;
    private Boolean selection;
    private Vote vote;
}

