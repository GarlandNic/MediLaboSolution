package com.projet9.notes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.notes.model.Note;
import com.projet9.notes.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	NoteRepository noteRepo;

	public List<Note> listOfNotes() {
		List<Note> result = new ArrayList<>();
		noteRepo.findAll().forEach(n -> result.add(n));
		return result;
	}

	public List<Note> listNotesForPatient(int id) {
		List<Note> result = new ArrayList<>();
		noteRepo.findByPatId(id).forEach(n -> result.add(n));
		return result;
	}

	public Note create(Note note) {
		return noteRepo.insert(note);
	}

	public Note update(Note note) {
		return noteRepo.save(note);
	}

	public void delete(String id) {
		noteRepo.deleteById(id);
	}

	public List<String> listContentsFofPatient(int patId) {
		List<String> result = new ArrayList<>();
		noteRepo.findByPatId(patId).forEach(n -> result.add(n.getNote()));
		return result;
	}

}
