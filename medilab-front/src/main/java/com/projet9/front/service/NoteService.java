package com.projet9.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.front.model.Note;
import com.projet9.front.proxy.GatewayProxy;

@Service
public class NoteService {
	
	@Autowired 
	GatewayProxy gate;

	public List<Note> getListOfNotes(int patId) {
		return gate.listNotesForPatient(patId);
	}

	public Note save(Note note) {
		if(note.getNote().isBlank()) {
			gate.deleteNote(note.getId());
			return null;
		} else {
			return gate.updateNote(note);
		}
	}

	public Note saveNew(Note note) {
		if(note.getNote().isBlank()) {
			return null;
		} else {
			return gate.addNote(note);
		}
	}

}
