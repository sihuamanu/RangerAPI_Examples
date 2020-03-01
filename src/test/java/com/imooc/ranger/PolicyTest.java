package com.imooc.ranger;

import com.imooc.ranger.config.RangerAuthConfig;
import com.imooc.ranger.config.RangerClientConfig;
import com.imooc.ranger.model.Policy;
import com.imooc.ranger.model.PolicyItem;
import com.imooc.ranger.model.PolicyItemAccess;
import com.imooc.ranger.model.PolicyResource;
import feign.Logger;
import org.junit.Test;

import java.util.*;

public class PolicyTest {

    public static final String ranger_url = "http://10.65.42.129:6080";
    private static RangerClient rangerClient;

    @Test
    public void policyTest(){
        RangerClientConfig rangerClientConfig = RangerClientConfig.builder().connectTimeoutMillis(1000).readTimeoutMillis(1000).level(Logger.Level.BASIC)
                .authConfig(RangerAuthConfig.builder().username("admin").password("1qaz@WSX").build())
                .url(ranger_url).build();

        rangerClient = new RangerClient(rangerClientConfig);

        rangerClient.start();

        Map<String, PolicyResource> policyResourceMap = new HashMap<>();
        PolicyResource policyResource = PolicyResource.builder().values(Arrays.asList("/testdir3")).isRecursive(true).build();
        policyResourceMap.put("path",policyResource);
        Set<String> users = new HashSet<>();
        users.add("hive");
        List<PolicyItemAccess> policyItemAccessList = new ArrayList<>();
        policyItemAccessList.add(PolicyItemAccess.builder().type("read").build());
        policyItemAccessList.add(PolicyItemAccess.builder().type("write").build());
        policyItemAccessList.add(PolicyItemAccess.builder().type("execute").build());

        PolicyItem policyItem = PolicyItem.builder().delegateAdmin(true).users(users).accesses(policyItemAccessList).build();

        Policy policy = Policy.builder().service("imooc_hdfs").name("test_ranger_api").isEnabled(true).policyType(0)
                .resources(policyResourceMap).policyItems(Arrays.asList(policyItem)).build();

        Policy policy1 = rangerClient.getPolicyApis().createPolicy(policy);
        System.out.println(policy1.getName());

        Policy policy2 = rangerClient.getPolicyApis().getPolicyByName("imooc_hdfs", "test_ranger_api");
        System.out.println(policy2.getName());
        //rangerClient.getPolicyApis().deletePolicy(policy2.getId());

    }
}