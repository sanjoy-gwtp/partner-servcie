package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanjoy on 7/14/18.
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleModel implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<RightModel> rights;

    public RoleModel(Long id) {
        this.id = id;
    }

    public RoleModel() {
    }

}
