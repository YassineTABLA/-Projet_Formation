package com.master.formation.services;

import java.util.List;

import com.master.formation.beans.Formation;
import com.master.formation.beans.User;
import com.master.formation.dto.FormationPrix;

public interface FormationService {
	
	public Formation save(Formation formation);
	
	public List<Formation> getAll();
	
	public Formation getById(long id);
	
	public List<Formation> getByUsers(User user);
	
	public List<Formation> getByFormateur(String email);
	
	public void delete(Formation formation);
	
	public List<FormationPrix> getFormationPrix();
	
	public List<FormationPrix> getFormationPrixByFormateur(String email);

}
