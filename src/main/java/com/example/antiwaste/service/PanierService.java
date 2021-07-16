package com.example.antiwaste.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.Entity.Panier;
import com.example.antiwaste.dao.CommercantRepository;
import com.example.antiwaste.dao.PanierRepository;
import com.example.antiwaste.dao.PanierRepository;

@Service
public class PanierService {

	@Autowired
	CommercantRepository cr;
	@Autowired
	PanierRepository pr;
	
	
	public Panier findPanier (long id_panier) {
		return pr.findById(id_panier).get();
	}
	
	public Panier savePanier (Panier panier) {
		return pr.save(panier);	
	}
	
	public Panier updatePanier(Panier panier) {
		Panier existingPanier=pr.findById(panier.getId_panier()).get();
		existingPanier.setTitre(panier.getTitre());
		existingPanier.setType(panier.getType());
		existingPanier.setDescription(panier.getDescription());
		existingPanier.setImage(panier.getImage());
		
		return pr.save(existingPanier);
		
	}
	
	
	public void deletePanier (long id_panier) {
		pr.deleteById(id_panier);
	}
	
		
	public Map<String,ArrayList<Panier>> panierParCategorie(){
			
			Map<String,ArrayList<Panier>>listePanierParCategorie = new HashMap<>();
			
			ArrayList<String> categorie =cr.listeCategoriesCommercant(); 
			for (int i=0;i<categorie.size();i++){
				listePanierParCategorie.put(categorie.get(i),pr.listePaniersParCategorie(categorie.get(i)) );
			}
			return listePanierParCategorie;		
			
		}
	
	
	public Map<String,ArrayList<Panier>> listePanierParHeure(){
		Map<String,ArrayList<Panier>> listePanierParHeure= new HashMap<> ();
		listePanierParHeure.put("aSauver",pr.listePaniersParHeure());
		return 	listePanierParHeure;	
		
	}
	
	public Commercant commercantParPanier (long id_panier) {
		return pr.commercantParPanier(id_panier);
	}
	
	
	public Map<String,ArrayList<Panier>> listePaniersParCommercant (long id_commercant) {
		
		Map<String,ArrayList<Panier>> listePaniersParCommercant= new HashMap<> ();
		listePaniersParCommercant.put("Commercant",pr.listePaniersParCommercant(id_commercant));
		return listePaniersParCommercant;
	}
	
	
}
