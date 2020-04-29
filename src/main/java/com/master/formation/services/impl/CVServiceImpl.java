package com.master.formation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.formation.DAO.CVRepository;
import com.master.formation.beans.CV;
import com.master.formation.beans.User;
import com.master.formation.services.CVService;

@Service
public class CVServiceImpl implements CVService {
	@Autowired
	CVRepository cvRepository;

	@Override
	public CV getCvById(Long id) {
		return cvRepository.findByIdCv(id);
	}

	@Override
	public CV getCvByUser(User user) {
		return cvRepository.findByFormateur(user);
	}

	@Override
	public CV saveOrUpdateCv(CV cv) {
		return cvRepository.save(cv);
	}

	@Override
	public void deleteCv(Long id) {
		cvRepository.deleteByIdCv(id);
	}
}
