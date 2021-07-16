package com.example.antiwaste.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.Entity.Panier;
import com.example.antiwaste.Entity.Reserver;
import com.example.antiwaste.Entity.Utilisateur;
import com.example.antiwaste.service.CommercantService;
import com.example.antiwaste.service.PanierService;
import com.example.antiwaste.service.UtilisateurService;

import net.minidev.json.JSONObject;

@RestController
public class UtilisateurController {

	@Autowired
	private UtilisateurService us;
	
	@Autowired
	private CommercantService cs;
	
	@Autowired
	private PanierService ps;
	
	
	
	@PostMapping("/inscriptionUtilisateur")
	public Utilisateur inscriptionUtilisateur(@RequestBody Utilisateur utilisateur){
		
		return us.saveUtilisateur(utilisateur);	
	}
	
	
	@PostMapping("/test")
	public String inscription(@RequestBody JSONObject message){
		System.out.println("message"); 
		return "message";
	}
	
	
	@GetMapping("/connexionUtilisateur/{email}/{motDePasse}")
	public Utilisateur connexionUtilisateur(@PathVariable String email,@PathVariable String motDePasse){
		System.out.println(email+" "+motDePasse);
		System.out.println(us.connexionUtilisateur(email,motDePasse).getEmail());		
		return us.connexionUtilisateur(email,motDePasse);
	}
	
	@GetMapping("/getUtilisateur/{id_utilisateur}")
	public Utilisateur getUtilisateur(@PathVariable long id_utilisateur){
		
		return us.getUtilisateur(id_utilisateur);
	}
	
	@PutMapping("/modifierUtilisateur/{id_utilisateur}/{nom}/{prenom}/{email}/{numeroDeTelephone}")
	public Utilisateur updateUtilisateur(@PathVariable long id_utilisateur,
			@PathVariable String nom,
			@PathVariable String prenom,
			@PathVariable String email,
			@PathVariable String numeroDeTelephone) {
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		utilisateur.setEmail(email);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setNumeroDeTelephone(numeroDeTelephone);
		
		return us.updateUtilisateur(utilisateur);
	}
	
	@PutMapping("/modifierMotDePasse/{id_utilisateur}/{motDePasse}")
	public Utilisateur updateMotDePasseUtilisateur(@PathVariable long id_utilisateur,
			@PathVariable String motDePasse) {
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		
		utilisateur.setMotDePasse(motDePasse);
		
		return us.updateUtilisateur(utilisateur);
	}
	
	
	@DeleteMapping("/supprimer/{id_utilisateur}")
	public void deleteUtilisateur(@PathVariable long id_utilisateur) {
		System.out.println(id_utilisateur);

		 us.deleteUtilisateur(id_utilisateur);
	}
	
	

	@PostMapping("/listeDesCommercants")
	public Map<String, ArrayList<Commercant>> listeDesCommercants(){
		return cs.commercantParCategorie();
	}
	
	@PostMapping("/listeDesPaniers")
	public Map<String, ArrayList<Panier>> listeDesPaniers(){
		return ps.panierParCategorie();
	}
	
	@PostMapping("/listeDesPaniersParHeure")
	public Map<String, ArrayList<Panier>>listeDesPaniersParHeure(){
		return ps.listePanierParHeure();
	}
	
	@GetMapping("/commercantParPanier/{id_panier}")
	public Commercant commercantParPanier (@PathVariable long id_panier) {
		return ps.commercantParPanier(id_panier);
	}
	

	@GetMapping("/listeDesPaniersParCommercant/{id_commercant}")
	public Map<String, ArrayList<Panier>> listePaniersParCommercant (@PathVariable long id_commercant) {
		return ps.listePaniersParCommercant(id_commercant);
	}
	
	@PostMapping("/ajouterCommercantAuxFavoris/{id_utilisateur}/{id_commercant}")
	public Commercant  ajouterCommercantFavoris (@PathVariable long id_utilisateur,@PathVariable long id_commercant) {
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		Commercant commercant = cs.findCommercant(id_commercant);
		utilisateur.getCommercants().add(commercant);
		commercant.getUtilisateurs().add(utilisateur);
		
		return cs.saveCommercant(commercant);
	}
	
	
	@PostMapping("/supprimerCommercantAuxFavoris/{id_utilisateur}/{id_commercant}")
	public Commercant  supprimerCommercantFavoris (@PathVariable long id_utilisateur,@PathVariable long id_commercant) {
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		Commercant commercant = cs.findCommercant(id_commercant);
		utilisateur.getCommercants().remove(commercant);
		commercant.getUtilisateurs().remove(utilisateur);
		
		return cs.saveCommercant(commercant);
	}
	
	
	@GetMapping("/listeDesCommercantsFavoris/{utilisateur}")
	public Map<String, ArrayList<Commercant>> listeDesCommercantsFavoris (Utilisateur utilisateur) {
		//Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);

		Map<String,ArrayList<Commercant>>listeCommercantFavoris = new HashMap<>();
		ArrayList<Commercant> listeDesCommercants = new ArrayList<>();
		listeDesCommercants.addAll(utilisateur.getCommercants());
		listeCommercantFavoris.put("Commercant",listeDesCommercants);
		
		return listeCommercantFavoris;
	}
	
	
	@PostMapping("/reserverPanier/{id_utilisateur}/{id_panier}/{nombreExemplairesReserves}")
	public Panier reserverPanier(@PathVariable long id_utilisateur, @PathVariable long id_panier,@PathVariable int nombreExemplairesReserves) {
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		Panier panier = ps.findPanier(id_panier);
		us.reserverPanier(utilisateur, panier, nombreExemplairesReserves);
		return panier;
	}
	
	@GetMapping("/listeDesReservations/{utilisateur}")
	public Map<String, ArrayList<Reserver>> listeDesReservations (Utilisateur utilisateur) {
		
		Map<String,ArrayList<Reserver>>listeDesReservations = new HashMap<>();
		listeDesReservations.put("Reservations",us.listeDesReservations(utilisateur));
		
		return listeDesReservations;
	}
	
	@PostMapping("/annulerReservationPanier/{id_utilisateur}/{id_reservation}")
	public Utilisateur annulerReservationPanier(@PathVariable long id_utilisateur, @PathVariable long id_reservation) {
		Reserver reserver  = us.findReserver(id_reservation);
		Utilisateur utilisateur = us.getUtilisateur(id_utilisateur);
		 us.annulerReservationPanier(utilisateur, reserver);
		return utilisateur;
	}
	
	
	
}
