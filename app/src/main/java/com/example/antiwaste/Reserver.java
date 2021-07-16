package com.example.antiwaste;

public class Reserver {

    private long id_reservation;
    private Utilisateur utilisateurs;
    private Panier paniers;
    int nombreExemplairesReserves;
    String etat;


    public Reserver() {


    }


    public Reserver(int id_reservation , Utilisateur utilisateurs, Panier paniers, int nombreExemplairesReserves) {
        super();
        this.id_reservation=id_reservation;
        this.utilisateurs = utilisateurs;
        this.paniers = paniers;
        this.nombreExemplairesReserves=nombreExemplairesReserves;
        this.etat="réservé";
    }

    public long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Panier getPaniers() {
        return paniers;
    }

    public void setPaniers(Panier paniers) {
        this.paniers = paniers;
    }


    public int getNombreExemplairesReserves() {
        return nombreExemplairesReserves;
    }


    public void setNombreExemplairesReserves(int nombreExemplairesReserves) {
        this.nombreExemplairesReserves = nombreExemplairesReserves;
    }


    public String getEtat() {
        return etat;
    }


    public void setEtat(String etat) {
        this.etat = etat;
    }







}
