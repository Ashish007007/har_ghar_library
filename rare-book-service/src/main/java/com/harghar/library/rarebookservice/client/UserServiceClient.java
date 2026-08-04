package com.harghar.library.rarebookservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harghar.library.rarebookservice.client.dto.UserProfileClientResponse;
import com.harghar.library.rarebookservice.config.FeignClientConfig;

@FeignClient(name = "user-service", configuration = FeignClientConfig.class)
public interface UserServiceClient {

    @GetMapping("/api/users/{id}")
    UserProfileClientResponse getUserProfileById(@PathVariable("id") Long userId);
}
