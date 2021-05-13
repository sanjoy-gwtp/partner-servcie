package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AppVersion {
    private Long id;
    private String minimumVersion;
    private String latestVersion;
    private int minimumBuild;
    private int latestBuild;
}
