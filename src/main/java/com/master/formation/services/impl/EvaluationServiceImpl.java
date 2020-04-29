package com.master.formation.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.formation.DAO.EvaluationRepository;
import com.master.formation.beans.Evaluation;
import com.master.formation.beans.Formation;
import com.master.formation.services.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService{
	
	@Autowired
	EvaluationRepository evaluationRepo;
	
	@Override
	public Collection<Evaluation> getAll() {
		return evaluationRepo.findAll();
	}

	@Override
	public Evaluation getEvaluationById(Long id) {
		return evaluationRepo.getOne(id);
	}

	@Override
	public Evaluation getEvaluationByFormation(Formation formation) {
		return evaluationRepo.findByFormation(formation);
	}

	@Override
	public Evaluation saveOrUpdateEvaluation(Evaluation evaluation) {
		return evaluationRepo.save(evaluation);
	}

	@Override
	public void deleteEvaluation(Long id) {
		evaluationRepo.deleteById(id);
	}

}
