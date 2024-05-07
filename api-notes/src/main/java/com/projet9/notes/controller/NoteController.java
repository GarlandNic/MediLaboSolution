package com.projet9.notes.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet9.notes.model.Note;
import com.projet9.notes.service.NoteService;

@RestController
public class NoteController {
	
	@Autowired
	NoteService noteServ;
	
	@GetMapping("/getNotes")
	public List<Note> listOfNotes() {
		return noteServ.listOfNotes();
	}
	
	@GetMapping("/getNotesForPatient/{id}")
	public List<Note> listNotesForPatient(@PathVariable("id") int id) {
		return noteServ.listNotesForPatient(id);
	}
	
    @PostMapping(path = "/addNote",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Note addNote(@RequestBody Note note) {
    	return noteServ.create(note);
    }
    
    @PutMapping(path = "/updateNote",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Note updateNote(@RequestBody Note note) {
    	return noteServ.update(note);
    }
    
    @DeleteMapping("/deleteNote/{id}")
    public void deleteNote(@PathVariable("id") ObjectId id) {
    	noteServ.delete(id);
    }


}
