package com.example.antiwaste.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.antiwaste.Entity.Panier;
import com.example.antiwaste.Entity.Reserver;
import com.example.antiwaste.Entity.Utilisateur;

@RepositoryRestResource
public interface ReserverRepository extends JpaRepository<Reserver, Long>{

	@Query("select r from Reserver r "
			+ "where r.etat='réservé' and id_utilisateur=:utilisateur")
	public ArrayList<Reserver> listeDesReservations (@Param ("utilisateur")Utilisateur utilisateur);
}
