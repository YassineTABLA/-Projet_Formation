package com.master.formation.services;

import java.util.List;

import com.master.formation.beans.Chapitre;
import com.master.formation.beans.Formation;

public interface ChapitreService {

	public List<Chapitre> saveAll(List<Chapitre> chapitres);
	public List<Chapitre> getByFormation(Formation formation);
	public void deleteAll(List<Chapitre> chapitres);
	
}
