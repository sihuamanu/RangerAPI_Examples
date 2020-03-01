package com.imooc.ranger.api;

import com.imooc.ranger.RangerClientException;
import com.imooc.ranger.model.User;
import feign.Param;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserApis {
    private final UserFeignClient userFeignClient;

    public User createUser(User user) throws RangerClientException {
        return userFeignClient.createUser(user);
    }

    public void deleteUser(@Param("id") Integer id, @Param("forceDelete") boolean forceDelete) {
        userFeignClient.deleteUser(id, forceDelete);
    }

    public User getUserByName(@Param("name") String name) throws RangerClientException {
        return userFeignClient.getUserByName(name);
    }
}