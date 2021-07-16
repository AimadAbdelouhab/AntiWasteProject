package com.example.antiwaste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Commercant {
    Long id_commercant;
    String nom;
    String categorie,description,logo;
    Adresse adresse;
    ArrayList <Panier> paniers;
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();


    public Commercant() {
    }

    public Commercant(Long id_commercant,String nom, String categorie,String description,String logo,Adresse adresse,ArrayList <Panier> paniers,ArrayList<Utilisateur> utilisateurs) {
        this.id_commercant=id_commercant;
        this.nom = nom;
        this.categorie = categorie;
        this.description=description;
        this.logo=logo;
        this.adresse=adresse;
        this.paniers=paniers;
        this.utilisateurs= utilisateurs;

    }

    public Long getId_commercant() {
        return id_commercant;
    }

    public void setId_commercant(Long id_commercant) {
        this.id_commercant = id_commercant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}



