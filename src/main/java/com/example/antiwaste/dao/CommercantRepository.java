package com.example.antiwaste.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.antiwaste.Entity.Commercant;


@RepositoryRestResource
public interface CommercantRepository extends JpaRepository<Commercant, Long>{
	
	@Query("select distinct categorie from Commercant c")
	public ArrayList<String> listeCategoriesCommercant();
	
	@Query("select c from Commercant c")
	public ArrayList<Commercant> listeCommercants();
	
	@Query("select c from Commercant c where categorie=:categorie")
	public ArrayList<Commercant> listeCommercantsParCategorie(@Param ("categorie")String categorie);
	
	@Query("select c from Commercant c inner join Panier p on c.id_commercant=p.commercant.id_commercant where  p.id_panier=:id_panier")
	public Commercant commercantParPanier(@Param ("id_panier")long id_panier);
	


}
