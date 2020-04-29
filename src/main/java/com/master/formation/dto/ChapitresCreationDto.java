package com.master.formation.dto;

import java.util.ArrayList;
import java.util.List;

import com.master.formation.beans.Chapitre;
import com.master.formation.beans.Formation;

public class ChapitresCreationDto {
	
	List<Chapitre> chapitres;

	public ChapitresCreationDto() {
		this.chapitres = new ArrayList<Chapitre>();
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}
	
	public void addChapitre(Chapitre chapitre) {
        this.chapitres.add(chapitre);
    }
	
	public void addFormation(Formation formation) {
        for(Chapitre chapitre : this.chapitres) {
        	chapitre.setFormation(formation);
        }
    }

	@Override
	public String toString() {
		return "ChapitresCreationDto [chapitres=" + chapitres + "]";
	}
	
	

}
