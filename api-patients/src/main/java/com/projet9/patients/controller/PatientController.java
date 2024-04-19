package com.projet9.patients.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet9.patients.model.Patient;
import com.projet9.patients.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientServ;


    @RequestMapping("/getPatients")
    public List<Patient> listOfPatients()
    {
        return patientServ.listOfPatients();
    }
    
//    @RequestMapping("/getPatientBy") 
//    public List<Patient> getPatientBy(@RequestParam Map<String, String> param) {
//    	return patientServ.patientBy(param);
//    }
    
    @RequestMapping("/getPatientById") 
    public Patient getPatientById(@RequestParam int id) {
    	return patientServ.patientById(id);
    }
    
    @RequestMapping("/addPatient")
    public Patient addPatient(@RequestParam Patient patient) {
    	return patientServ.create(patient);
    }
    
    @RequestMapping("/updatePatient")
    public Patient updatePatient(@RequestParam Patient patient) {
    	return patientServ.update(patient);
    }
    
    @RequestMapping("/deletePatient")
    public void deletePatient(@RequestParam int id) {
    	patientServ.delete(id);
    }
    
}
