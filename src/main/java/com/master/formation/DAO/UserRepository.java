package com.master.formation.DAO;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.formation.beans.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String name);

	User findByIdUser(Long id);

	Collection<User> findByRole(String role);

	User findByEmail(String email);

	User deleteByIdUser(Long id);
}
