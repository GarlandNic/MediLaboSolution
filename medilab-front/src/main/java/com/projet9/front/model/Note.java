package com.projet9.front.model;

public class Note {
	
	private String id;
	
	private int patId;
	
	private String patient;
	
	private String note;
	
	public Note() {}
	public Note(Patient patient) {
		this.id = null;
		this.patId = patient.getId();
		this.patient = patient.getLastName();
		this.note = "";
	}

	
	public int getPatId() {
		return patId;
	}
	public void setPatId(int patId) {
		this.patId = patId;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
