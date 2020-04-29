package com.master.formation.dto;

import java.util.Date;

public interface FormationPrix {
	
	public long getIdFormation();
	
	public String getNomFormation();
	
	public Date getDate();
	
	public String getLocale();
	
	public String getObjectifs();
	
	public int getScore();
	
	public Float getPrix();
	

}
