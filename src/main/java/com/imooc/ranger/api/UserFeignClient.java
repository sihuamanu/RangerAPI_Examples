package com.imooc.ranger.api;

import com.imooc.ranger.RangerClientException;
import com.imooc.ranger.model.User;
import feign.Param;
import feign.RequestLine;


public interface UserFeignClient {
    @RequestLine("POST /service/xusers/secure/users")
    User createUser(User user) throws RangerClientException;

    @RequestLine("DELETE /service/xusers/secure/users/id/{id}?forceDelete={forceDelete}")
    void deleteUser(@Param("id") Integer id, @Param("forceDelete") boolean forceDelete);

    @RequestLine("GET /service/xusers/users/userName/{name}")
    User getUserByName(@Param("name") String name) throws RangerClientException;
}
