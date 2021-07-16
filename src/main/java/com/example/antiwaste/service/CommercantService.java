package com.example.antiwaste.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.dao.CommercantRepository;

@Service
public class CommercantService {
	
	@Autowired
	CommercantRepository cr;
	
	public Commercant findCommercant (long id_commercant) {
		return cr.findById(id_commercant).get();
	}
	
	public Commercant commercantParPanier (long id_panier) {
		return cr.commercantParPanier(id_panier);
	}
	
	
	
	public Commercant saveCommercant (Commercant commercant) {
		return cr.save(commercant);	
	}
	
	public Commercant updateCommercant(Commercant commercant) {
		Commercant existingCommercant=cr.findById(commercant.getId_commercant()).get();
		existingCommercant.setNom(commercant.getNom());
		existingCommercant.setCategorie(commercant.getCategorie());
		existingCommercant.setDescription(commercant.getDescription());
		existingCommercant.setLogo(commercant.getLogo());
		
		return cr.save(existingCommercant);
		
	}
	
	
	public void deleteCommercant (long id_commercant) {
		cr.deleteById(id_commercant);
	}
	
	public Map<String,ArrayList<Commercant>> commercantParCategorie(){
		
		Map<String,ArrayList<Commercant>>listeCommercantParCategorie = new HashMap<>();
		
		
		ArrayList<String> categorie =cr.listeCategoriesCommercant(); 
		for (int i=0;i<categorie.size();i++){
			listeCommercantParCategorie.put(categorie.get(i),cr.listeCommercantsParCategorie(categorie.get(i)) );
		}
		return listeCommercantParCategorie;		
		
	}
	
	
	
	
	

}
