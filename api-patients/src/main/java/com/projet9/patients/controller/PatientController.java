package com.projet9.patients.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet9.patients.model.Patient;
import com.projet9.patients.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientServ;

    @GetMapping("/getPatients")
    public List<Patient> listOfPatients()
    {
        return patientServ.listOfPatients();
    }
    
//    @RequestMapping("/getPatientBy") 
//    public List<Patient> getPatientBy(@RequestParam Map<String, String> param) {
//    	return patientServ.patientBy(param);
//    }
    
    @GetMapping("/getPatientById/{id}") 
    public Patient getPatientById(@PathVariable("id") int id) {
    	return patientServ.patientById(id);
    }
    
    @PostMapping("/addPatient")
    public Patient addPatient(@RequestParam("patient") Patient patient) {
    	return patientServ.create(patient);
    }
    
    @PutMapping("/updatePatient")
    public Patient updatePatient(@RequestParam("patient") Patient patient) {
    	return patientServ.update(patient);
    }
    
    @DeleteMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable("id") int id) {
    	patientServ.delete(id);
    }
    
}
