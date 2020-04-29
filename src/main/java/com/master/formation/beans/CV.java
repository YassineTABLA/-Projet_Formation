package com.master.formation.beans;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CV {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCv;
	@OneToOne
	private User formateur;
	@Column
	private String linkedIn;

	public CV() {
		super();
	}

	public CV(User formateur, String linkedIn) {
		super();
		this.formateur = formateur;
		this.linkedIn = linkedIn;
	}

	public Long getIdCv() {
		return idCv;
	}

	public void setIdCv(Long idCv) {
		this.idCv = idCv;
	}
	public User getFormateur() {
		return formateur;
	}

	public void setFormateur(User formateur) {
		this.formateur = formateur;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
}
