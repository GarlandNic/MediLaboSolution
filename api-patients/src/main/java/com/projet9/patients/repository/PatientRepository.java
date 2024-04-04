package com.projet9.patients.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projet9.patients.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
