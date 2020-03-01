package com.imooc.ranger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Policy {
    Map<String,PolicyResource> resources;
    List<PolicyItem> policyItems = Lists.newArrayList();
    List<PolicyItem> denyPolicyItems = Lists.newArrayList();
    List<PolicyItem> allowExceptions = Lists.newArrayList();
    List<PolicyItem> denyExceptions = Lists.newArrayList();
    List<Object> dataMaskPolicyItems = Lists.newArrayList();
    List<Object> rowFilterPolicyItems = Lists.newArrayList();

    private int id;
    private String guid;
    private boolean isEnabled;
    private int version;
    private String service;
    private String name;
    private int policyType;
    private String description;
    private boolean isAuditEnabled;

}

