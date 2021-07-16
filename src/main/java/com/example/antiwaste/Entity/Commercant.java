package com.example.antiwaste.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Commercant")
public class Commercant {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_commercant;
	
	private String nom,categorie,description,logo;
	
	@JsonIgnore
	@OneToMany(mappedBy="commercant",fetch=FetchType.LAZY )
	private Collection<Panier> paniers;
	@ManyToOne//(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_adresse")
	private Adresse adresse;
	/*@ManyToMany(mappedBy = "commercants")
    private List<Utilisateur> utilisateurs;
*/
//	@OneToMany(mappedBy = "commercant")
//	private List<Favoris> favoris;
	
	@ManyToMany(fetch=FetchType.EAGER)
    private List<Utilisateur> utilisateurs = new ArrayList<>();
	
	public Commercant() {}
	
	
	public Commercant(String nom, String categorie, String description, String logo) {
		super();
		this.nom = nom;
		this.categorie = categorie;
		this.description = description;
		this.logo = logo;
	}

	
	public Commercant(String nom, String categorie, String description, String logo, Adresse adresse) {
		super();
		this.nom = nom;
		this.categorie = categorie;
		this.description = description;
		this.logo = logo;
		this.adresse=adresse;
	}


	public long getId_commercant() {
		return id_commercant;
	}


	public void setId_commercant(long id_commercant) {
		this.id_commercant = id_commercant;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}




	public Collection<Panier> getPaniers() {
		return paniers;
	}




	public void setPaniers(Collection<Panier> paniers) {
		this.paniers = paniers;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}


	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}


//	public List<Favoris> getFavoris() {
//		return favoris;
//	}
//
//
//	public void setFavoris(List<Favoris> favoris) {
//		this.favoris = favoris;
//	}

	

	
	
	
}
