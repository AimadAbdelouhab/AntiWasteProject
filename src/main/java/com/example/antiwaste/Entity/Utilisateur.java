package com.example.antiwaste.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_utilisateur;
	private String nom, prenom, email, motDePasse, numeroDeTelephone;

	@JsonIgnore
	@OneToMany(mappedBy = "utilisateurs", fetch=FetchType.LAZY)
	private List<Reserver> reservations=new ArrayList<>();

	/*
	 * @JsonIgnore
	 * 
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "Favoris", joinColumns = @JoinColumn(name =
	 * "id_utilisateur", referencedColumnName = "id_utilisateur"),
	 * inverseJoinColumns = @JoinColumn(name = "id_commercant", referencedColumnName
	 * = "id_commercant")) private List<Commercant> commercants;
	 */

//	@OneToMany(mappedBy = "utilisateur")
//	private List<Favoris> favoris;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "utilisateurs")
	private List<Commercant> commercants = new ArrayList<Commercant>();

	public Utilisateur() {
	}

	public Utilisateur(String nom, String prenom, String email, String motDePasse, String numeroDeTelephone) {
		super();

		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public long getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}

	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}


	public List<Commercant> getCommercants() {
		return commercants;
	}

	public void setCommercants(List<Commercant> commercants) {
		this.commercants = commercants;
	}

	public List<Reserver> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reserver> reservations) {
		this.reservations = reservations;
	}
	
	
	
	
	

//	public List<Favoris> getFavoris() {
//		return favoris;
//	}
//
//	public void setFavoris(List<Favoris> favoris) {
//		this.favoris = favoris;
//	}

}
