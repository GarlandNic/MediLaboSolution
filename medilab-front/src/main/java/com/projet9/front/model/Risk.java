package com.projet9.front.model;

public enum Risk {
	None,
	Borderline,
	InDanger,
	EarlyOnset;

	public String toHtml() {
		switch (this) {
			case None:
		    	return "Aucun risque";
		    case Borderline:
		        return "Risque limité";
		    case InDanger:
		        return "Danger";
		    case EarlyOnset:
		        return "Apparition précoce";
		    default:
		        return null;
		}
	}
}
