package com.master.formation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.master.formation.DAO.MyUserPrincipale;
import com.master.formation.beans.User;
import com.master.formation.services.UserService;

@RestController
public class ControllerAuthentification {
	@Autowired
	UserService userService;

	@GetMapping("/index")
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("isFormateur", false);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/error")
	public ModelAndView error(Model model) {
		ModelAndView mv = new ModelAndView();
		User cuser = userService.getUserByEmail(
				((MyUserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		boolean isFormateur = false;
		if (cuser.getRole().equals("formateur")) {
			isFormateur = true;
		}
		mv.addObject("isFormateur", isFormateur);
		mv.setViewName("error");
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");

		mv.addObject("isFormateur", false);

		return mv;
	}

	@GetMapping("/signup")
	public ModelAndView signup(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		mv.addObject("isFormateur", false);
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("isFormateur", false);
		return mv;
	}
}
