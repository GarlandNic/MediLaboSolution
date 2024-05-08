package com.projet9.diabete.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;

@FeignClient(name="medilab-gateway", url="localhost:8090")
@Headers("Authorization: Basic userForGateway:passwordForGateway")
public interface GatewayProxy {


}
