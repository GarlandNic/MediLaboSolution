package com.projet9.diabete.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Headers;

@FeignClient(name="medilab-gateway", url="localhost:8090")
@Headers("Authorization: Basic userForGateway:passwordForGateway")
public interface GatewayProxy {
	
    @GetMapping("/notes/getContentsForPatient/{patId}")
    public List<String> listContentsForPatient(@PathVariable("patId") int patId);

}
