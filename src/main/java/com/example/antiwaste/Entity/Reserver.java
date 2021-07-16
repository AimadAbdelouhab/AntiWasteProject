package com.example.antiwaste.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reserver")
public class Reserver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_reservation;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;

	@ManyToOne
	@JoinColumn(name="id_panier")
	private Panier paniers;
	
	int nombreExemplairesReserves;
	String etat;
	
	
	public Reserver() {
		
		
	}
	

	public Reserver(Utilisateur utilisateurs, Panier paniers, int nombreExemplairesReserves) {
		super();
		this.utilisateurs = utilisateurs;
		this.paniers = paniers;
		this.nombreExemplairesReserves=nombreExemplairesReserves;
		this.etat="réservé";
	}

	public long getId_reservation() {
		return id_reservation;
	}

	public void setId_reservation(long id_reservation) {
		this.id_reservation = id_reservation;
	}

	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Panier getPaniers() {
		return paniers;
	}

	public void setPaniers(Panier paniers) {
		this.paniers = paniers;
	}


	public int getNombreExemplairesReserves() {
		return nombreExemplairesReserves;
	}


	public void setNombreExemplairesReserves(int nombreExemplairesReserves) {
		this.nombreExemplairesReserves = nombreExemplairesReserves;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	
	

	
	
	
}
