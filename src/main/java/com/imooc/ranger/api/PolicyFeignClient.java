package com.imooc.ranger.api;

import com.imooc.ranger.RangerClientException;
import com.imooc.ranger.model.Policy;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface PolicyFeignClient {
    @RequestLine("POST /service/public/v2/api/policy")
    Policy createPolicy(Policy policy) throws RangerClientException;

    @RequestLine("DELETE /service/plugins/policies/{id}")
    void deletePolicy(@Param("id") Integer id);

    @RequestLine("GET /service/public/v2/api/service/{serviceName}/policy/{policyName}")
    Policy getPolicyByName(@Param("serviceName") String serviceName, @Param("policyName") String policyName) throws RangerClientException;

    @RequestLine("GET /service/public/v2/api/service/{serviceName}/policy")
    List<Policy> getAllPoliciesByService(@Param("serviceName") String serviceName) throws RangerClientException;
}