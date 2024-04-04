package com.projet9.patients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.patients.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepo;
	
	

}
