package com.example.antiwaste.dao;

//import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.antiwaste.Entity.Utilisateur;

@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	@Query("select u from Utilisateur u where u.email=:email and u.motDePasse=:motDePasse")
	public Utilisateur connexionUtilisateur( @Param ("email")String email,@Param ("motDePasse") String motDePasse);



	@Query("select u from Utilisateur u where u.email=:email ")
	public Utilisateur findByEmail(@Param ("email")String email);
	
	//@Query("select u from Utilisateur u where u.id_utilisateur=:id_utilisateur ")
//	public Utilisateur findById(@Param ("id_utilisateur")long id_utilisateur);

}
