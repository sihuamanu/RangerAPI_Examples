package com.imooc.ranger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyItem {
    private List<PolicyItemAccess> accesses = Lists.newArrayList();
    private Set<String> users = Sets.newHashSet();
    private List<String> groups = Lists.newArrayList();
    private List<PolicyItemCondition> conditions = Lists.newArrayList();
    private Boolean delegateAdmin;


}

