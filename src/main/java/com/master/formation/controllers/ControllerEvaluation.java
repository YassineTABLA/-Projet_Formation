package com.master.formation.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.FormationRepository;
import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.Evaluation;
import com.master.formation.beans.User;
import com.master.formation.services.EvaluationService;
import com.master.formation.services.UserService;

@RestController
@RequestMapping("/evaluation")
public class ControllerEvaluation {
	
	@Autowired
	EvaluationService evaluationService;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	UserService userService;
	
//	@GetMapping
//	public ModelAndView getAll() {
//		ModelAndView mv =new ModelAndView();
//		Collection<Evaluation> evaluations = evaluationService.getAll();
//				
//		mv.addObject("evaluations",evaluations);
//		
//		mv.setViewName("evaluation/index");
//		
//		User cuser = userService.getUserByEmail(
//				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
//		boolean isFormateur = false;
//		if(cuser.getRole().equals("formateur")) {
//			isFormateur=true;
//		}
//		mv.addObject("isFormateur", isFormateur);
//		
//		return mv;
//	}
	
	@GetMapping("/{id}/add")
	public ModelAndView saveEvaluation(@PathVariable long id) {
		ModelAndView mv =new ModelAndView();
		
		mv.addObject("formation", formationRepository.findById(id));
		mv.setViewName("evaluation/add");
		
		User cuser = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;
		
	}
	
	@PostMapping("/{id}/add")
	public ModelAndView addPage(Evaluation evaluation,@PathVariable long id , @RequestParam("note") String StringNote) {
		ModelAndView mv =new ModelAndView();
		User cuser = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		
		evaluation.setFormation(formationRepository.findById(id));
		evaluation.setUsers(cuser);
		
		int note=Integer.parseInt(""+StringNote.charAt(1));
		
		evaluation.setNote(note);
		evaluationService.saveOrUpdateEvaluation(evaluation);
				
		mv.setViewName("redirect:/formation/"+id);
		
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;
		
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deletePage(@PathVariable long id) {
		ModelAndView mv =new ModelAndView();
		
		evaluationService.deleteEvaluation(id);
				
		mv.setViewName("redirect:/evaluation");
		
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
