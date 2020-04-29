package com.master.formation.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.User;
import com.master.formation.services.UserService;

@RestController
public class ControllerUser {
	@Autowired
	UserService userservice;

	public String currentUserEmail() {
		return ((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	}

	@PostMapping("/register")
	public ModelAndView addUser(@Valid @ModelAttribute("User") User user) {
		ModelAndView mv = new ModelAndView();
		PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
		user.setPassword(passwordEnocder.encode(user.getPassword()));
		userservice.saveOrUpdateUser(user);
		System.out.println(user.getRole());
		if (user.getRole().equals("formateur")) {
			mv.setViewName("redirect:/formateur/addcv");
		} else {
			mv.setViewName("redirect:/login");
		}
		
		if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
			User cuser = userservice.getUserByEmail(
					((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getEmail());
			boolean isFormateur = false;
			if (cuser.getRole().equals("formateur")) {
				isFormateur = true;
			}
			mv.addObject("isFormateur", isFormateur);
		}

		return mv;
	}

	@PostMapping("/updateUser")
	public ModelAndView updateUser(@Valid @ModelAttribute("User") User user) {
		ModelAndView mv = new ModelAndView();
		User userDatabase = userservice.getUserByEmail(this.currentUserEmail());
		if (userDatabase != null) {
			if (!user.getEmail().isEmpty()) {
				userDatabase.setEmail(user.getEmail());
			}
			if (!user.getUsername().isEmpty()) {
				userDatabase.setUsername(user.getUsername());
			}
			userservice.saveOrUpdateUser(userDatabase);
			mv.setViewName("redirect:/index");
		} else {
			mv.setViewName("redirect:/error");
		}

		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if (cuser.getRole().equals("formateur")) {
			isFormateur = true;
		}
		mv.addObject("isFormateur", isFormateur);

		return mv;
	}

	@GetMapping("/updateUser")
	public ModelAndView updateUserPage() {
		ModelAndView mv = new ModelAndView();
		String email = this.currentUserEmail();
		String username = ((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();

		mv.addObject("email", email);
		mv.addObject("username", username);

		mv.setViewName("updateUser");

		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if (cuser.getRole().equals("formateur")) {
			isFormateur = true;
		}
		mv.addObject("isFormateur", isFormateur);

		return mv;
	}

	@GetMapping("/password")
	public ModelAndView changePasswordPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatePassword");

		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if (cuser.getRole().equals("formateur")) {
			isFormateur = true;
		}
		mv.addObject("isFormateur", isFormateur);

		return mv;
	}

	@PostMapping("/password")
	public ModelAndView changePassword(@RequestParam("oldPassword") String oldPass,
			@RequestParam("newPassword") String newPass) {
		ModelAndView mv = new ModelAndView();
		User userDatabase = userservice.getUserByEmail(this.currentUserEmail());
		PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
		if (passwordEnocder.matches(oldPass, userDatabase.getPassword())) {
			userDatabase.setPassword(passwordEnocder.encode(newPass));
			userservice.saveOrUpdateUser(userDatabase);
			mv.setViewName("redirect:/index");
		} else {
			mv.setViewName("redirect:/error");
		}

		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if (cuser.getRole().equals("formateur")) {
			isFormateur = true;
		}
		mv.addObject("isFormateur", isFormateur);

		return mv;
	}

}
