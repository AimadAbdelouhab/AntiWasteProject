package com.example.antiwaste.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.antiwaste.Entity.Commercant;
import com.example.antiwaste.Entity.Panier;

@RepositoryRestResource
public interface PanierRepository extends JpaRepository<Panier, Long>{
	
	@Query("select p from Panier p "
			+ "where DATE(date_et_heure) = CURDATE() "
			+ "and TIMEDIFF(Time(date_et_heure), CURRENT_TIME)<'03:00' "
			+ "and TIMEDIFF(Time(date_et_heure), CURRENT_TIME)>='00:00' "
			+ "and p.nbrDExemplaires>0 "
			+ "order by date_et_heure asc")
	public ArrayList<Panier> listePaniersParHeure();
	
	
	
	@Query("select p from Panier p "
			+ "where commercant.categorie=:categorie "
			+ "and p.nbrDExemplaires>0 "
			+ "and DATE(date_et_heure) >= CURDATE() "
			+ "and TIMEDIFF(TIMESTAMP(date_et_heure), NOW())>='00:00' order by date_et_heure asc")
	public ArrayList<Panier> listePaniersParCategorie(@Param ("categorie")String categorie);
	
	@Query("select commercant from Panier p where p.id_panier=:id_panier")
	public Commercant commercantParPanier(@Param ("id_panier")long id_panier);
	
	
	@Query("select p from Panier p "
			+ "where p.commercant.id_commercant=:id_commercant "
			+ "and p.nbrDExemplaires>0 "
			+ "and DATE(date_et_heure) >= CURDATE() "
			+ "and TIMEDIFF(TIMESTAMP(date_et_heure), NOW())>='00:00' order by date_et_heure asc")
	public ArrayList<Panier> listePaniersParCommercant(@Param ("id_commercant")long id_commercant);
	

}
