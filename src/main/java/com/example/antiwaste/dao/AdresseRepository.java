package com.example.antiwaste.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.antiwaste.Entity.Adresse;

@RepositoryRestResource
public interface AdresseRepository extends JpaRepository<Adresse,Long> {

}
