package com.projet9.diabete.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PatientRisk {
	
	public static enum Gender {
		M,
		F;
	}
	
	private int id;
	private LocalDate dateOfBirth;
	private Gender gender;
	private int keywordNumber;
	
	public PatientRisk(int id, LocalDate dateOfBirth, String gender, int keywordNumber) {
		this.id = id;
		this.dateOfBirth = dateOfBirth;
		this.gender = Gender.valueOf(gender.toUpperCase());
		this.keywordNumber = keywordNumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getKeywordNumber() {
		return keywordNumber;
	}
	public void setKeywordNumber(int keywordNumber) {
		this.keywordNumber = keywordNumber;
	}
	
	public int getAge() {
		LocalDate today = LocalDate.now();
		return (int) this.dateOfBirth.until(today, ChronoUnit.YEARS);
	}

}
