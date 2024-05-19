package com.projet9.patients.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet9.patients.model.Patient;
import com.projet9.patients.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientServ;

    @GetMapping("/getPatients")
    public List<Patient> listOfPatients() {
        return patientServ.listOfPatients();
    }
        
    @GetMapping("/getPatientById/{id}") 
    public Patient getPatientById(@PathVariable("id") int id) {
    	return patientServ.patientById(id);
    }

    @PostMapping(path = "/addPatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient addPatient(@RequestBody Patient patient) {
    	return patientServ.create(patient);
    }
    
    @PutMapping(path = "/updatePatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@RequestBody Patient patient) {
    	return patientServ.update(patient);
    }
    
    @DeleteMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable("id") int id) {
    	patientServ.delete(id);
    }
    
    @GetMapping("/getGenderOf/{id}") 
    public String getGenderOf(@PathVariable("id") int id) {
    	return patientServ.genderOf(id);
    }
    
    @GetMapping("/getBirthOf/{id}") 
    public LocalDate getBirthOf(@PathVariable("id") int id) {
    	return patientServ.birthOf(id);
    }
    
}
