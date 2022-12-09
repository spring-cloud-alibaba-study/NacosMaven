package com.js.feignclient;


import com.js.api.TestControllerApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = "user-service")
public interface UserTestProxy extends TestControllerApi {

}
