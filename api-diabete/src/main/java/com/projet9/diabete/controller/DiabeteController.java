package com.projet9.diabete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet9.diabete.service.DiabeteService;

@RestController
public class DiabeteController {
	
	@Autowired
	DiabeteService diabeteServ;
	
	@PostMapping(path = "/keywordCounting",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public int keywordCounting(@RequestBody List<String> notesLst) {
    	return diabeteServ.keywordCounting(notesLst);
    }
	
	@PostMapping(path = "/splitString",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> splitString(@RequestBody String note) {
    	return diabeteServ.splitString(note);
    }
	
	@GetMapping("/getKeywordForPatient/{id}")
	public int keywordForPatient(@PathVariable("id") int id) {
		return diabeteServ.keywordForPatient(id);
	}

	@GetMapping("/getRiskForPatient/{id}")
	public String riskForPatient(@PathVariable("id") int id) {
		return diabeteServ.riskForPatient(id).toString();
	}

}
