package com.projet9.diabete.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.diabete.proxy.GatewayProxy;

@Service
public class DiabeteService {
	
	public static final String[] DECLENCHEURS = 
		{"hémoglobine A1C",
		 "microalbumine",
		 "taille",
		 "poids",
		 "fumeur", "fumeuse",
		 "anormal",
		 "cholestérol",
		 "vertiges",
		 "rechute",
		 "réaction",
		 "anticorps"};
	
	public static enum Risk {
		None,
		Borderline,
		InDanger,
		EarlyOnset;
	}
	
	@Autowired
	GatewayProxy gate;

	public int riskCounting(List<String> notesLst) {
		int result = 0;
		boolean match = false;
		for(String decl : DECLENCHEURS) {
			match = false;
			for(String note : notesLst) {
				if(note.toLowerCase().contains(decl.toLowerCase())) {
					match = true;
					break;
				}
			}
			if(match) result++;
		}
		
		return result;
	}

	public List<String> splitString(String note) {
		List<String> notesLst;
		List<String> result = Arrays.asList(note);
		boolean declFirst = false;
		
		for(String decl : DECLENCHEURS) {
			notesLst = result;
			result = new ArrayList<>();
			for(String notePart : notesLst) {
				result.addAll(Arrays.asList(notePart.split("(?i)((?="+decl+")|(?<="+decl+"))")));
			}
			if(result.get(0).equalsIgnoreCase(decl)) declFirst=true;
		}

		if(declFirst) result.add(0, "");
		return result;
	}

	public int riskForPatient(int id) {
		return riskCounting(gate.listContentsForPatient(id));
	}

}
