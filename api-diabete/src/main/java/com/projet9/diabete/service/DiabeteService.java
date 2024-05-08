package com.projet9.diabete.service;

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
	
	// fct pour découper le string
	
	// fct pour compter les mots-clef
	
	// fct

	// Si 1 déclencheur est présent plusieurs fois, on le compte plusieurs fois ?
	// parcourir déclencheur et les rechercher dans notes,
	// ou parcourir notes et compter déclencheurs ?
	
}
