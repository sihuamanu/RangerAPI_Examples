package com.imooc.ranger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String createDate;
    private String updateDate;
    private String owner;
    private String updateBy;
    private String name;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String description;
    private int status;
    private int isVisible;
    private int userSource;
    private List<String> userRoleList;
}
