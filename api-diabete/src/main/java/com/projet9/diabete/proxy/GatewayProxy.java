package com.projet9.diabete.proxy;

import java.time.LocalDate;
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
	
    @GetMapping("/patients/getGenderOf/{id}") 
    public String getGenderOf(@PathVariable("id") int id);
    
    @GetMapping("/patients/getBirthOf/{id}") 
    public LocalDate getBirthOf(@PathVariable("id") int id);

}
