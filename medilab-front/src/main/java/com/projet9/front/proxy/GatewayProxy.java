package com.projet9.front.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet9.front.model.Patient;

@FeignClient(name="medilab-gateway", url="localhost:8090")
public interface GatewayProxy {

    @RequestMapping("/patients/getPatients")
    public List<Patient> listOfPatients();
    
    @RequestMapping("/patients/getPatientById") 
    public Patient getPatientById(@RequestParam int id);
    
    @RequestMapping("/patients/addPatient")
    public Patient addPatient(@RequestParam Patient patient);
    
    @RequestMapping("/patients/updatePatient")
    public Patient updatePatient(@RequestParam Patient patient);
    
    @RequestMapping("/patients/deletePatient")
    public void deletePatient(@RequestParam int id);

}
