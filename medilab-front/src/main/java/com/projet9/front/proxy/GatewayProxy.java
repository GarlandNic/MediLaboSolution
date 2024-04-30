package com.projet9.front.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet9.front.model.Patient;

@FeignClient(name="medilab-gateway", url="localhost:8090")
public interface GatewayProxy {

    @GetMapping("/patients/getPatients")
    public List<Patient> listOfPatients();
    
    @GetMapping("/patients/getPatientById/{id}") 
    public Patient getPatientById(@PathVariable("id") int id);
    
    @PostMapping(path = "/patients/addPatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient addPatient(@RequestBody Patient patient);
    
    @PutMapping(path = "/patients/updatePatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@RequestBody Patient patient);
    
    @DeleteMapping("/patients/deletePatient/{id}")
    public void deletePatient(@PathVariable("id") int id);

}
