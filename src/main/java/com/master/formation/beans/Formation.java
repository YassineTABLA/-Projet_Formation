package com.master.formation.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFormation;
	@Column
	private String nomFormation;
	@Column
	private String formateur;
	@Column
	private Date date;
	@Column
	private String locale;
	@Column
	private String objectifs;
	@Column
	private String prerequis;
	@Column
	private boolean permiteEvaluation;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_formation", joinColumns = @JoinColumn(name = "idFormation"), inverseJoinColumns = @JoinColumn(name = "idUser"))
	private List<User> users;
	@OneToMany(mappedBy = "formation")
	private List<Chapitre> chapitres;

	public Formation() {
		super();
	}

	public Formation(long idFormation, String nomFormation, Date date, String locale, String objectifs,
			String prerequis) {
		super();
		this.idFormation = idFormation;
		this.nomFormation = nomFormation;
		this.date = date;
		this.locale = locale;
		this.objectifs = objectifs;
		this.prerequis = prerequis;
	}

	public long getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(long idFormation) {
		this.idFormation = idFormation;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	public boolean isPermiteEvaluation() {
		return permiteEvaluation;
	}

	public void setPermiteEvaluation(boolean permiteEvaluation) {
		this.permiteEvaluation = permiteEvaluation;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Formation [nomFormation=" + nomFormation + ", date=" + date + ", locale=" + locale + ", objectifs="
				+ objectifs + ", prerequis=" + prerequis + ", permiteEvaluation=" + permiteEvaluation + "]";
	}

	public String getFormateur() {
		return formateur;
	}

	public void setFormateur(String formateur) {
		this.formateur = formateur;
	}

}
