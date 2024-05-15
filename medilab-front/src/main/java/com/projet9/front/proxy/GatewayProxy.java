package com.projet9.front.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet9.front.model.Patient;
import com.projet9.front.model.Note;

import feign.Headers;

@FeignClient(name="medilab-gateway", url="localhost:8090")
@Headers("Authorization: Basic userForGateway:passwordForGateway")
public interface GatewayProxy {

    @GetMapping("/patients/getPatients")
    public List<Patient> listOfPatients();
    
    @GetMapping("/patients/getPatientById/{id}") 
    public Patient getPatientById(@PathVariable("id") int id);
    
    @PostMapping(path = "/patients/addPatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient addPatient(@RequestBody Patient patient);
    
    @PutMapping(path = "/patients/updatePatient",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@RequestBody Patient patient);
    
    @DeleteMapping("/patients/deletePatient/{id}")
    public void deletePatient(@PathVariable("id") int id);

    
	@GetMapping("/notes/getNotes")
	public List<Note> listOfNotes();
	
	@GetMapping("/notes/getNotesForPatient/{id}")
	public List<Note> listNotesForPatient(@PathVariable("id") int id);
	
    @PostMapping(path = "/notes/addNote",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Note addNote(@RequestBody Note note);
    
    @PutMapping(path = "/notes/updateNote",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Note updateNote(@RequestBody Note note);
    
    @DeleteMapping("/notes/deleteNote/{id}")
    public void deleteNote(@PathVariable("id") String id);

    
	@GetMapping("/diabete/getRiskForPatient/{id}")
	public String riskForPatient(@PathVariable("id") int id);
	
	@GetMapping("/diabete/getKeywordForPatient/{id}")
	public int keywordForPatient(@PathVariable("id") int id);


}
