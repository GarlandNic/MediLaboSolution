package com.projet9.front.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projet9.front.model.Note;
import com.projet9.front.model.Patient;
import com.projet9.front.service.NoteService;
import com.projet9.front.service.PatientService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MedilabFrontController {
	
	@Autowired 
	PatientService patientServ;
	
	@Autowired
	NoteService noteServ;
	
	// patients
	
	@GetMapping("/")
	public String index(Model model) {
		return "redirect:/patients/listPatient";
	}
	
	@GetMapping("/patients/listPatient")
	public String listOfPatients(Model model, Principal principal) {
		return displayHtmlListPatient(model, principal);		
	}
	
	@GetMapping("/patients/newPatient")
	public String newPatientForm(Model model, Principal principal) {
		Patient patient = new Patient();
		model.addAttribute("modif", patient.getId());
		return displayHtmlFichePatient(model, principal, patient);		
	}
	
	@GetMapping("/patients/{id}")
	public String patientInfo(Model model, Principal principal, @PathVariable("id") final int id) {
		Patient patient = patientServ.getPatient(id);
		return displayHtmlFichePatient(model, principal, patient);
	}

	@PostMapping(value="/patients/modif/{id}", params={"modif"})
	public String activateModifPatient(Model model, Principal principal, @PathVariable("id") final int id, 
			@ModelAttribute("patient") Patient patient, final HttpServletRequest req) {
	    final Integer idModif = Integer.valueOf(req.getParameter("modif"));
	    model.addAttribute("modif", idModif);
		return displayHtmlFichePatient(model, principal, patient);		
	}
	
	@PostMapping(value="/patients/modif/{id}", params={"save"})
	public String savePatient(Model model, Principal principal, @PathVariable("id") final int id, 
			@ModelAttribute("patient") Patient patient, final HttpServletRequest req) {
		Patient patientSaved = patientServ.save(patient);
		return "redirect:/patients/"+patientSaved.getId();		
	}
	
	@PostMapping(value="/patients/modif/{id}", params={"supprimer"})
	public String activateSupprPatient(Model model, Principal principal, @PathVariable("id") final int id, 
			@ModelAttribute("patient") Patient patient, final HttpServletRequest req) {
	    final Integer idSuppr = Integer.valueOf(req.getParameter("supprimer"));
	    model.addAttribute("modif", idSuppr);
		model.addAttribute("confirm", idSuppr);
		return displayHtmlFichePatient(model, principal, patient);
	}

	@PostMapping(value="/patients/modif/{id}", params={"confirmSuppr"})
	public String suppressionPatient(Model model, Principal principal, @PathVariable("id") final int id, 
			@ModelAttribute("patient") Patient patient, final HttpServletRequest req) {
		patientServ.delete(patient);
		return "redirect:/patients/listPatient";	
	}
	
	// notes
	
	@PostMapping(value = "/notes/modif/{id}", params= {"saveNote"})
	public String modifNote(Model model, @PathVariable("id") final int patId, 
			@ModelAttribute("thisNote") Note note, final HttpServletRequest req) {
		noteServ.save(note);
		return "redirect:/patients/"+patId;
	}
	
	@PostMapping(value = "/notes/add/{patId}")
	public String addNewNote(Model model, @PathVariable("patId") final int patId, @ModelAttribute("newNote") Note note) {
		noteServ.saveNew(note);
		return "redirect:/patients/"+patId;
	}


	// display
	
	private String displayHtmlListPatient(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		List<Patient> listOfPatients = patientServ.getListOfPatient();
		model.addAttribute("patientList", listOfPatients);
		return "list_patients";
	}
	
	private String displayHtmlFichePatient(Model model, Principal principal, Patient patient) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("patient", patient);
		model.addAttribute("listOfNotes", noteServ.getListOfNotes(patient.getId()));
		model.addAttribute("newNote", new Note(patient));
		return "fiche_patient";
	}
	
}
