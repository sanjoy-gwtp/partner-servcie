package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by sanjoy on 7/14/18.
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserModel implements Serializable {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Date lastPasswordResetDate;
    private List<RoleModel> roles;
    private List<RightModel> rights;
    private Long menuGroup;
    private Long branch;
    private Credential credential;
    private String clientId;

    public UserModel() {
    }

    public UserModel(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
