package com.projet9.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.front.model.Patient;
import com.projet9.front.proxy.GatewayProxy;

@Service
public class PatientService {
	
	// proxy ??
	@Autowired 
	GatewayProxy gate;

	public List<Patient> getListOfPatient() {
		List<Patient> listOfPatient = gate.listOfPatients();
		// TODO tests non-null
		return listOfPatient;
	}

	public Patient getPatient(int id) {
		Patient patient = gate.getPatientById(id);
		// TODO test non-null
		return patient;
	}

	public Patient save(Patient patient) {
		Patient pat = gate.getPatientById(patient.getId());
		Patient patientFinal;
		if(pat==null) patientFinal = gate.addPatient(patient);
		else patientFinal = gate.updatePatient(patient);
		return patientFinal;
	}

	public void delete(Patient patient) {
		gate.deletePatient(patient.getId());
	}

}
