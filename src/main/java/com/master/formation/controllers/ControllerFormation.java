package com.master.formation.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.Chapitre;
import com.master.formation.beans.Formation;
import com.master.formation.beans.User;
import com.master.formation.dto.ChapitresCreationDto;
import com.master.formation.dto.FormationPrix;
import com.master.formation.services.ChapitreService;
import com.master.formation.services.FormationService;
import com.master.formation.services.UserService;

@RestController
@RequestMapping("/formation")
public class ControllerFormation {

	@Autowired
	FormationService formationServ;

	@Autowired
	ChapitreService chapitreServ;

	@Autowired
	UserService userServ;

	@GetMapping
	public ModelAndView getAll() {
		ModelAndView mv = new ModelAndView();
		List<FormationPrix> formations = formationServ.getFormationPrix();

		mv.addObject("formations", formations);

		mv.setViewName("formation/index");

		mv.addObject("isFormateur", false);
		
		return mv;
	}

	@GetMapping("/add")
	public ModelAndView savePage() {
		ModelAndView mv = new ModelAndView();

		// mv.addObject("chapitre",new ChapitresCreationDto());
		mv.addObject("formation", new Formation());

		mv.setViewName("formation/add");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

	@PostMapping("/add")
	public ModelAndView addPage(Formation formation, ChapitresCreationDto chapitres,
			@RequestParam("imageFile") MultipartFile imageFile) {
		ModelAndView mv = new ModelAndView();

		formation.setFormateur(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		formation.setPermiteEvaluation(false);
		Formation savedFormation = formationServ.save(formation);

		chapitres.addFormation(savedFormation);

		chapitreServ.saveAll(chapitres.getChapitres());
		if (!imageFile.isEmpty()) {
			Path currentPath = Paths.get("");
			Path absolutePath = currentPath.toAbsolutePath();
			String absPath = absolutePath + "/src/main/resources/static/images/formation/";

			try {
				byte[] bytes = imageFile.getBytes();
				Path path = Paths.get(absPath + savedFormation.getIdFormation() + ".png");
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		mv.setViewName("redirect:/formation");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

	@GetMapping("/{id}/delete")
	public ModelAndView deletePage(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();

		Formation formation = formationServ.getById(id);
		ChapitresCreationDto chapitres = new ChapitresCreationDto();
		List<Chapitre> chapitresList = chapitreServ.getByFormation(formation);
		chapitres.setChapitres(chapitresList);

		formationServ.delete(formation);
		chapitreServ.deleteAll(chapitres.getChapitres());

		mv.setViewName("redirect:/formation");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

	@GetMapping("/{id}")
	public ModelAndView getFormation(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();

		String currentEmail = ((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
		Formation formation = formationServ.getById(id);
		ChapitresCreationDto chapitresdto = new ChapitresCreationDto();
		List<Chapitre> chapitresList = chapitreServ.getByFormation(formation);
		chapitresdto.setChapitres(chapitresList);
		User formateur = userServ.getUserByEmail(formation.getFormateur());

		boolean isCreateur = false;
		boolean isSuivi = false;
		if (currentEmail.equals(formation.getFormateur())) {
			isCreateur = true;
		}else {
			if(userServ.getUserByEmail(currentEmail).getFormations().contains(formation)) {
				isSuivi = true;
			}
		}

		mv.addObject("formation", formation);
		mv.addObject("formateur", formateur);
		mv.addObject("isCreateur", isCreateur);
		mv.addObject("isSuivi", isSuivi);
		mv.addObject("chapitresdto", chapitresdto);
		mv.addObject("cv",formateur.getCv().getLinkedIn());

		mv.setViewName("formation/course");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

	@GetMapping("/{id}/update")
	public ModelAndView updatePage(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();

		Formation formation = formationServ.getById(id);
		ChapitresCreationDto chapitresdto = new ChapitresCreationDto();
		List<Chapitre> chapitresList = chapitreServ.getByFormation(formation);
		chapitresdto.setChapitres(chapitresList);

		mv.addObject("formation", formation);
		mv.addObject("chapitresdto", chapitresdto);
		mv.addObject("date", formation.getDate());

		mv.setViewName("formation/update");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

	@PostMapping("/update")
	public ModelAndView updateFormationAndChapitres(Formation formation, ChapitresCreationDto chapitres) {
		ModelAndView mv = new ModelAndView();

		formationServ.save(formation);
		chapitreServ.saveAll(chapitres.getChapitres());

		mv.setViewName("redirect:/formation");

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}
	
	@GetMapping("/{id}/evaluating")
	public ModelAndView EvaluatingPage(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();

		Formation formation = formationServ.getById(id);
		
		formation.setPermiteEvaluation(true);
		formationServ.save(formation);
		
		mv.setViewName("redirect:/formation/"+id);

		User cuser = userServ.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if(cuser.getRole().equals("formateur")) {
			isFormateur=true;
		}
		mv.addObject("isFormateur", isFormateur);
		
		return mv;

	}

}
