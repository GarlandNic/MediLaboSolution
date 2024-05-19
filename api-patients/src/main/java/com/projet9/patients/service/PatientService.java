package com.projet9.patients.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.patients.model.Patient;
import com.projet9.patients.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepo;

	public List<Patient> listOfPatients() {
		List<Patient> result = new ArrayList<>();
		patientRepo.findAll().forEach(p -> result.add(p));
		return result;
	}

	public Patient patientById(int id) {
		Optional<Patient> p = patientRepo.findById(id);
		if(p.isEmpty()) return null;
		else return p.get();
	}

	public Patient create(Patient patient) {
		return patientRepo.save(patient);
	}

	public Patient update(Patient patient) {
		return patientRepo.save(patient);
	}

	public void delete(int id) {
		patientRepo.deleteById(id);
	}

	public String genderOf(int id) {
		Optional<Patient> p = patientRepo.findById(id);
		if(p.isEmpty()) return null;
		else return p.get().getGender().toString();
	}

	public LocalDate birthOf(int id) {
		Optional<Patient> p = patientRepo.findById(id);
		if(p.isEmpty()) return null;
		else return p.get().getDateOfBirth();
	}	

}
