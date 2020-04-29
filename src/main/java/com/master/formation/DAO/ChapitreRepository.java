package com.master.formation.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.formation.beans.Chapitre;
import com.master.formation.beans.Formation;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {

	List<Chapitre> findByFormation(Formation formation);

}
