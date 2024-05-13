package com.projet9.diabete.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.diabete.model.PatientRisk;
import com.projet9.diabete.model.PatientRisk.Gender;
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
		 "vertige",
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

	public int keywordCounting(List<String> notesLst) {
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

	public int keywordForPatient(int id) {
		return keywordCounting(gate.listContentsForPatient(id));
	}

	public Risk riskForPatient(int id) {
		PatientRisk patient = new PatientRisk(id, gate.getBirthOf(id), gate.getGenderOf(id), keywordForPatient(id));
		
//		● apparition précoce (Early onset) : Encore une fois, cela dépend de l'âge et du sexe. Si
//		le patient est un homme de moins de 30 ans, alors au moins cinq termes déclencheurs
//		sont nécessaires. Si le patient est une femme et a moins de 30 ans, il faudra au moins
//		sept termes déclencheurs. Si le patient a plus de 30 ans, alors il en faudra huit ou plus.
		if(	(patient.getAge()<=30 && patient.getGender()==Gender.M && patient.getKeywordNumber()>=5) ||
			(patient.getAge()<=30 && patient.getGender()==Gender.F && patient.getKeywordNumber()>=7) ||
			(patient.getAge()>30 && patient.getKeywordNumber()>=8)	) {
			return Risk.EarlyOnset;
		}
//		● danger (In Danger) : Dépend de l'âge et du sexe du patient. Si le patient est un homme
//		de moins de 30 ans, alors trois termes déclencheurs doivent être présents. Si le patient
//		est une femme et a moins de 30 ans, il faudra quatre termes déclencheurs. Si le patient
//		a plus de 30 ans, alors il en faudra six ou sept ;
		if(	(patient.getAge()<=30 && patient.getGender()==Gender.M && patient.getKeywordNumber()>=3) ||
			(patient.getAge()<=30 && patient.getGender()==Gender.F && patient.getKeywordNumber()>=4) ||
			(patient.getAge()>30 && patient.getKeywordNumber()>=6)	) {
			return Risk.InDanger;
		}
//		● risque limité (Borderline) : Le dossier du patient contient entre deux et cinq
//		déclencheurs et le patient est âgé de plus de 30 ans ;
		if(	(patient.getAge()>30 && patient.getKeywordNumber()>=2)	) {
			return Risk.Borderline;
		}
		
		return Risk.None;
	}

}
