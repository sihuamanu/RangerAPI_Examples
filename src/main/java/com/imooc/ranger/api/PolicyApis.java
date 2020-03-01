package com.imooc.ranger.api;

import com.imooc.ranger.RangerClientException;
import com.imooc.ranger.model.Policy;
import feign.Param;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PolicyApis {

    private final PolicyFeignClient policyFeignClient;

    public Policy getPolicyByName(@Param("serviceName") String serviceName, @Param("policyName") String policyName) throws RangerClientException {
        return policyFeignClient.getPolicyByName(serviceName, policyName);
    }

    public List<Policy> getAllPoliciesByService(@Param("serviceName") String serviceName) throws RangerClientException {
        return policyFeignClient.getAllPoliciesByService(serviceName);
    }

    public Policy createPolicy(Policy policy) throws RangerClientException {
        return policyFeignClient.createPolicy(policy);
    }

    public void deletePolicy(@Param("id") Integer id) {
        policyFeignClient.deletePolicy(id);
    }


}