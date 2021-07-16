package com.example.antiwaste.Entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Adresse")
public class Adresse {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_adresse;
	private String rue,ville,codePostal,pays;
	private int numeroDeRue;
	
	@JsonIgnore
	@OneToMany(mappedBy="adresse",fetch=FetchType.LAZY )
	private Collection<Commercant> commercants;
	
	
	public Adresse() {
	}
	
	
	public Adresse(int numeroDeRue,String rue,   String codePostal,String ville, String pays) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.pays = pays;
		this.numeroDeRue = numeroDeRue;
		this.codePostal = codePostal;
	}


	public long getId_adresse() {
		return id_adresse;
	}


	public void setId_adresse(long id_adresse) {
		this.id_adresse = id_adresse;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public int getNumeroDeRue() {
		return numeroDeRue;
	}


	public void setNumeroDeRue(int numeroDeRue) {
		this.numeroDeRue = numeroDeRue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
	
	
	

}
