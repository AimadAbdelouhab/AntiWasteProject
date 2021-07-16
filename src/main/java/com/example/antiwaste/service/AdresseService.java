package com.example.antiwaste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiwaste.Entity.Adresse;
import com.example.antiwaste.dao.AdresseRepository;

@Service
public class AdresseService {

	@Autowired
	AdresseRepository ar;
	
	
	public Adresse findAdresse (Adresse adresse) {
		return ar.findById(adresse.getId_adresse()).get();
	}
	
	public Adresse saveAdresse (Adresse adresse) {
		return ar.save(adresse);	
	}
	
	public Adresse updateAdresse(Adresse Adresse) {
		Adresse existingAdresse=ar.findById(Adresse.getId_adresse()).get();
		existingAdresse.setNumeroDeRue(Adresse.getNumeroDeRue());
		existingAdresse.setRue(Adresse.getRue());
		existingAdresse.setCodePostal(Adresse.getCodePostal());
		existingAdresse.setVille(Adresse.getVille());
		existingAdresse.setPays(Adresse.getPays());

		return ar.save(existingAdresse);
		
	}
	
	
	public void deleteAdresse (long id_adresse) {
		ar.deleteById(id_adresse);
	}



}

