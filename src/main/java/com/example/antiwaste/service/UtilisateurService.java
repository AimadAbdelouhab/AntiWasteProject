package com.example.antiwaste.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.Entity.Panier;
import com.example.antiwaste.Entity.Reserver;
import com.example.antiwaste.Entity.Utilisateur;
import com.example.antiwaste.dao.CommercantRepository;
import com.example.antiwaste.dao.PanierRepository;
import com.example.antiwaste.dao.ReserverRepository;
import com.example.antiwaste.dao.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository ur;
	
	@Autowired
	private CommercantRepository cr;
	
	@Autowired
	private ReserverRepository rr;
	
	@Autowired
	private PanierRepository pr;
	
	public Utilisateur getUtilisateur (long id_utilisateur) {
		return  ur.findById(id_utilisateur).get();
	}
	
	public Utilisateur saveUtilisateur (Utilisateur utilisateur) {
		if (ur.findByEmail(utilisateur.getEmail())==null) {
			
			return 	ur.save(utilisateur);
		}else return new Utilisateur();
		
	}
	
	public Utilisateur connexionUtilisateur( String email,String motDePasse) {
		if (ur.connexionUtilisateur(email,motDePasse)!=null) {				
			return 	ur.connexionUtilisateur(email,motDePasse);
		}else return new Utilisateur();
	}
	
	
	public void deleteUtilisateur (long id_utilisateur) {
		ur.deleteById(id_utilisateur);
	}
	
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		Utilisateur existingUtilisateur=ur.findById(utilisateur.getId_utilisateur()).get();
		existingUtilisateur.setEmail(utilisateur.getEmail());
		existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
		existingUtilisateur.setNom(utilisateur.getNom());
		existingUtilisateur.setPrenom(utilisateur.getPrenom());
		existingUtilisateur.setNumeroDeTelephone(utilisateur.getNumeroDeTelephone());
		return ur.save(existingUtilisateur);
		
	}
	/*
	public void  ajouterCommercantFavoris ( long id_utilisateur, long id_commercant) {
		Utilisateur utilisateur = getUtilisateur(id_utilisateur);
		Commercant commercant = cr.findById(id_commercant).get();
		utilisateur.getCommercants().add(commercant);
		commercant.getUtilisateurs().add(utilisateur);
		
	}*/
	
	public Reserver findReserver(long id_reserver) {
		return rr.findById(id_reserver).get();
	}

	public void reserverPanier(Utilisateur utilisateur, Panier panier, int nombreExemplairesReserves){
		Reserver reserver= rr.save(new Reserver (utilisateur,panier,nombreExemplairesReserves));

		long nombreExemplaire=panier.getNbrDExemplaires();
		panier.setNbrDExemplaires(nombreExemplaire-nombreExemplairesReserves);
		pr.save(panier);
		
	}
	
	public ArrayList<Reserver> listeDesReservations(Utilisateur utilisateur) {
		return rr.listeDesReservations(utilisateur);
	}
	
	public void annulerReservationPanier(Utilisateur utilisateur, Reserver reserver){
		Panier panier = reserver.getPaniers();
		panier.setNbrDExemplaires(panier.getNbrDExemplaires()+reserver.getNombreExemplairesReserves());
		pr.save(panier);
		rr.delete(reserver);

		
		
	}
	
	
}
