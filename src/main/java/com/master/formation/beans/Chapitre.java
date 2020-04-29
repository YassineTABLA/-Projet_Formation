package com.master.formation.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chapitre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idChapitre;
	
	private String nomChapitre;
	private int temps;
	private float prix;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="id_formation")
	private Formation formation;
	
	public Chapitre() {
		super();
	}
	public Chapitre(long idChapitre, String nomChapitre, int temps, float prix) {
		super();
		this.idChapitre = idChapitre;
		this.nomChapitre = nomChapitre;
		this.temps = temps;
		this.prix = prix;
	}
	public long getIdChapitre() {
		return idChapitre;
	}
	public void setIdChapitre(long idChapitre) {
		this.idChapitre = idChapitre;
	}
	public String getNomChapitre() {
		return nomChapitre;
	}
	public void setNomChapitre(String nomChapitre) {
		this.nomChapitre = nomChapitre;
	}
	public int getTemps() {
		return temps;
	}
	public void setTemps(int temps) {
		this.temps = temps;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	@Override
	public String toString() {
		return "Chapitre [idChapitre=" + idChapitre + ", nomChapitre=" + nomChapitre + ", temps=" + temps + ", prix="
				+ prix + ", formation=" + formation + "]";
	}
	
	
}
