package com.master.formation.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.master.formation.beans.Evaluation;
import com.master.formation.beans.Formation;

@Service
public interface EvaluationService {
	
	public Collection<Evaluation> getAll();

	public Evaluation getEvaluationById(Long id);

	public Evaluation getEvaluationByFormation(Formation formation);

	public Evaluation saveOrUpdateEvaluation(Evaluation evaluation);

	public void deleteEvaluation(Long id);
}
