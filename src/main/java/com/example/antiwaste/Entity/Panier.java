package com.example.antiwaste.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Panier")
public class Panier {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_panier;
	
	private String titre,type,description,image;
	private LocalDateTime dateEtHeure;
	private long nbrDExemplaires;
	private double prix;
	//@JsonIgnore
	@ManyToOne//(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_commercant")
	private Commercant commercant;
	@JsonIgnore
	@OneToMany(mappedBy = "paniers", fetch = FetchType.LAZY)
	private List<Reserver> reservations=new ArrayList<>();
	
	
	
		
	public Panier() {
		super();
	}
	
	
	public Panier(String titre, String type, String description, String image, LocalDateTime dateEtHeure, 
			long nbrDExemplaires,double prix, Commercant commercant) {
		super();
		this.titre = titre;
		this.type = type;
		this.description = description;
		this.image = image;
		this.dateEtHeure = dateEtHeure;
		this.nbrDExemplaires = nbrDExemplaires;
		this.prix=prix;
		this.commercant=commercant;
	}
	
	
	
	public long getId_panier() {
		return id_panier;
	}
	public void setId_panier(long id_panier) {
		this.id_panier = id_panier;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public LocalDateTime getDateEtHeure() {
		return dateEtHeure;
	}
	public void setDateEtHeure(LocalDateTime dateEtHeure) {
		this.dateEtHeure = dateEtHeure;
	}

	public long getNbrDExemplaires() {
		return nbrDExemplaires;
	}
	public void setNbrDExemplaires(long nbrDExemplaires) {
		this.nbrDExemplaires = nbrDExemplaires;
	}


	public Commercant getCommercant() {
		return commercant;
	}


	public void setCommercant(Commercant commercant) {
		this.commercant = commercant;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public List<Reserver> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reserver> reservations) {
		this.reservations = reservations;
	}
	
	
	
	
}
