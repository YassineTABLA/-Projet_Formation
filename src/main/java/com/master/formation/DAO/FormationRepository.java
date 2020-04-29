package com.master.formation.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.master.formation.beans.Formation;
import com.master.formation.beans.User;
import com.master.formation.dto.FormationPrix;

public interface FormationRepository extends JpaRepository<Formation, Long>{
	
	public Formation findById(long id);
	
	public List<Formation> findByUsers(User user);
	
	public List<Formation> findByFormateur(String formateur);
	
	@Query(nativeQuery = true,value = "select f.id_formation as idFormation, f.nom_formation as nomFormation, COALESCE(AVG(e.note),0) as score,f.locale as locale,f.objectifs as objectifs,f.date as date, SUM(c.prix) as prix FROM formation f left join chapitre c ON f.id_formation=c.id_formation left join evaluation e on f.id_formation = e.formation_id_formation GROUP BY f.id_formation, f.nom_formation ORDER BY 1 ASC")
	public List<FormationPrix> findFormationPrix();
	
	@Query(nativeQuery = true,value = "select f.id_formation as idFormation, f.nom_formation as nomFormation, COALESCE(AVG(e.note),0) as score,f.locale as locale,f.objectifs as objectifs,f.date as date, SUM(c.prix) as prix FROM formation f left join chapitre c ON f.id_formation=c.id_formation left join evaluation e on f.id_formation = e.formation_id_formation WHERE f.formateur="
			+ "?1 GROUP BY f.id_formation, f.nom_formation ORDER BY 1 ASC")
	public List<FormationPrix> findFormationPrixByFormateur(String email);
	
	
}
