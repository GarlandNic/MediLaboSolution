package com.projet9.patients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projet9.patients.service.PatientService;

@Controller
public class PatientController {
	
	@Autowired
	PatientService patientServ;

}
