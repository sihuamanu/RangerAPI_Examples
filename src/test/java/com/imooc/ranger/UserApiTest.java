package com.imooc.ranger;

import com.imooc.ranger.config.RangerAuthConfig;
import com.imooc.ranger.config.RangerClientConfig;
import com.imooc.ranger.model.User;
import feign.Logger;
import org.junit.Test;

import java.util.Arrays;

public class UserApiTest {
    public static final String ranger_url = "http://10.65.42.129:6080";
    private static RangerClient rangerClient;

    @Test
    public void userTest(){

        RangerClientConfig rangerClientConfig = RangerClientConfig.builder().connectTimeoutMillis(1000).readTimeoutMillis(1000).level(Logger.Level.BASIC)
                .authConfig(RangerAuthConfig.builder().username("admin").password("1qaz@WSX").build())
                .url(ranger_url).build();

        rangerClient = new RangerClient(rangerClientConfig);

        rangerClient.start();

        User user = User.builder().name("test").firstName("first").lastName("last").password("user@123")
                .isVisible(1).status(1).userSource(0).userRoleList(Arrays.asList("ROLE_USER")).build();

        //User user1 = rangerClient.getUserApis().createUser(user);
        //System.out.println(user1.getName());

        User user2 = rangerClient.getUserApis().getUserByName("test");
        System.out.println(user2.getName());

        rangerClient.getUserApis().deleteUser(user2.getId(),true);



    }
}