package com.master.formation.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Evaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvaluation;
	@Column
	private Integer note;
	@OneToOne
	private Formation formation;
	@OneToOne
	private User user;

	public Evaluation() {
	}

	public Evaluation(Long idEvaluation, Integer note, Formation formation, User users) {
		super();
		this.idEvaluation = idEvaluation;
		this.note = note;
		this.formation = formation;
		this.user = user;
	}

	public Long getIdEvaluation() {
		return idEvaluation;
	}

	public void setIdEvaluation(Long idEvaluation) {
		this.idEvaluation = idEvaluation;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public User getUsers() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

}
