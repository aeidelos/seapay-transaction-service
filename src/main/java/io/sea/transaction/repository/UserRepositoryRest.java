package io.sea.transaction.repository;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.sea.transaction.model.User;
import io.sea.transaction.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryRest implements UserRepository {

    @Value("${user-service.rest.host}")
    private String userServiceHost;

    @Value("${user-service.rest.port}")
    private String userServicePort;

    @Override
    public void updateBalance(List<User> users) {
        UserAPI userAPI = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(UserAPI.class, getUserServiceUrl() + "/user");
        ResponseTemplate<List<User>> result = userAPI.updateUser(users);
        if (!result.isSuccess()) {
            throw new RuntimeException("failed to update balance.." + users);
        }
    }

    private String getUserServiceUrl() {
        return String.format("http://%s:%s", userServiceHost, userServicePort);
    }
}

interface UserAPI {
    @RequestLine("PUT /users")
    ResponseTemplate<List<User>> updateUser(List<User> users);
}
