package com.master.formation.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.formation.beans.CV;
import com.master.formation.beans.User;

public interface CVRepository extends JpaRepository<CV, Long>{
	CV findByFormateur(User user);

	CV findByIdCv(Long id);

	CV deleteByIdCv(Long id);
}
