package com.master.formation.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.formation.beans.Evaluation;
import com.master.formation.beans.Formation;
import com.master.formation.beans.User;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	Evaluation findByFormation(Formation formation);
	Evaluation findByUser(User user);
}
