package com.master.formation.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.CV;
import com.master.formation.beans.User;
import com.master.formation.services.CVService;
import com.master.formation.services.UserService;

@RestController
@RequestMapping("/formateur")
public class ControllerFormateur {
	@Autowired
	UserService userservice;
	@Autowired
	CVService cvService;

	public String currentUserEmail() {
		return ((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	}

	@GetMapping("/addcv")
	public ModelAndView addCVGet() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("formateurcv");
		
		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;
	}

	@PostMapping("/addcv")
	public ModelAndView addCVPost(@RequestParam(value = "linkedin", required = true) String linkedin) {
		ModelAndView mv = new ModelAndView();
		User formateur =  userservice.getUserByEmail(this.currentUserEmail());
		CV cv = new CV(formateur,linkedin);
		formateur.setCv(cv);
		cvService.saveOrUpdateCv(cv);
		userservice.saveOrUpdateUser(formateur);
		mv.setViewName("redirect:/login");
		
		User cuser = userservice.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;
	}

}
