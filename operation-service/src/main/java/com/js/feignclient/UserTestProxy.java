package com.js.feignclient;


import com.js.api.TestControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserTestProxy extends TestControllerApi {

}
