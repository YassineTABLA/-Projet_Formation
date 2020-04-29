package com.master.formation.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.formation.DAO.FormationRepository;
import com.master.formation.beans.Formation;
import com.master.formation.beans.User;
import com.master.formation.dto.FormationPrix;
import com.master.formation.services.FormationService;

@Service
public class FormationServiceImpl implements FormationService{

	@Autowired
	FormationRepository formationRepo;
	
	@Override
	public Formation save(Formation formation) {
		// TODO Auto-generated method stub
		return formationRepo.save(formation);
	}

	@Override
	public List<Formation> getAll() {
		// TODO Auto-generated method stub
		return formationRepo.findAll();
	}

	@Override
	public Formation getById(long id) {
		// TODO Auto-generated method stub
		return formationRepo.findById(id);
	}

	@Override
	public List<Formation> getByUsers(User user) {
		// TODO Auto-generated method stub
		return formationRepo.findByUsers(user);
	}

	@Override
	public void delete(Formation formation) {
		// TODO Auto-generated method stub
		formationRepo.delete(formation);
	}

	@Override
	public List<Formation> getByFormateur(String email) {
		// TODO Auto-generated method stub
		return formationRepo.findByFormateur(email);
	}

	@Override
	public List<FormationPrix> getFormationPrix() {
		// TODO Auto-generated method stub
		return formationRepo.findFormationPrix();
	}

	@Override
	public List<FormationPrix> getFormationPrixByFormateur(String email) {
		// TODO Auto-generated method stub
		return formationRepo.findFormationPrixByFormateur(email);
	}
	

}
