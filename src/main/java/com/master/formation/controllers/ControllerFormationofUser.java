package com.master.formation.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.Formation;
import com.master.formation.beans.User;
import com.master.formation.dto.FormationPrix;
import com.master.formation.services.FormationService;
import com.master.formation.services.UserService;

@RestController
public class ControllerFormationofUser {

	@Autowired
	FormationService formationServ;

	@Autowired
	UserService userService;

	@GetMapping("/myformations")
	public ModelAndView desplayFormationByUser() {
		ModelAndView mv = new ModelAndView();

		User user = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		List<Formation> formations = null;
		if(user.getRole().equals("formateur")) {
			isFormateur=true;
			formations = formationServ.getByFormateur(user.getEmail());
			mv.addObject("titre", "Les formations que vous avez cree");
		}else {
			formations = user.getFormations();
			mv.addObject("titre", "Les formations que vous suiviez");
		}
		mv.addObject("isFormateur", isFormateur);
		mv.addObject("formations", formations);

		mv.setViewName("formation/FormationByUser");

		return mv;

	}
	
	@GetMapping("/{id_user}/formations")
	public ModelAndView desplayFormationByFormateur(@PathVariable("id_user") long id) {
		ModelAndView mv = new ModelAndView();
		
		User user = userService.getUserById(id);
		List<FormationPrix> formations = formationServ.getFormationPrixByFormateur(user.getEmail());
		
		
		mv.addObject("formations", formations);
		mv.addObject("formateur", user);

		mv.setViewName("formation/byformateur");
		
		User user2 = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(user2.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		return mv;
	}

	@GetMapping("/{id_formation}/select")
	public ModelAndView slectFormationforUser(@PathVariable("id_formation") long id_formation) {
		ModelAndView mv = new ModelAndView();
		User user = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		Formation formation = formationServ.getById(id_formation);

		formation.getUsers().add(user);

		formationServ.save(formation);
		userService.saveOrUpdateUser(user);
		mv.setViewName("redirect:/formation/"+formation.getIdFormation());
		
		User cuser = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		return mv;
	}
	
	@GetMapping("/{id_formation}/deselect")
	public ModelAndView deslectFormationforUser(@PathVariable("id_formation") long id_formation) {
		ModelAndView mv = new ModelAndView();
		User user = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		Formation formation = formationServ.getById(id_formation);

		formation.getUsers().remove(user);
		user.getFormations().remove(formation);

		formationServ.save(formation);
		userService.saveOrUpdateUser(user);
		
		mv.setViewName("redirect:/formation/"+formation.getIdFormation());
		
		User cuser = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;
	}
}
