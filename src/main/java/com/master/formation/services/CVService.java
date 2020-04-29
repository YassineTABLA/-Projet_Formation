package com.master.formation.services;

import org.springframework.stereotype.Service;

import com.master.formation.beans.CV;
import com.master.formation.beans.User;

@Service
public interface CVService {

	public CV getCvById(Long id);

	public CV getCvByUser(User user);

	public CV saveOrUpdateCv(CV cv);

	public void deleteCv(Long id);
}
