package com.master.formation.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.formation.DAO.ChapitreRepository;
import com.master.formation.beans.Chapitre;
import com.master.formation.beans.Formation;
import com.master.formation.services.ChapitreService;

@Service
public class ChapitreServiceImpl implements ChapitreService{

	@Autowired
	ChapitreRepository chapitreRepo;
	
	@Override
	public List<Chapitre> saveAll(List<Chapitre> chapitres) {
		// TODO Auto-generated method stub
		return chapitreRepo.saveAll(chapitres);
	}

	@Override
	public List<Chapitre> getByFormation(Formation formation) {
		// TODO Auto-generated method stub
		return chapitreRepo.findByFormation(formation);
	}

	@Override
	public void deleteAll(List<Chapitre> chapitres) {
		// TODO Auto-generated method stub
		chapitreRepo.deleteAll(chapitres);
	}

}
