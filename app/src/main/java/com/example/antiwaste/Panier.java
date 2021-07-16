package com.example.antiwaste;

public class Panier {
    private long id_panier;

    private String titre,type,description,image,dateEtHeure;
    private long nbrDExemplaires;
    private Commercant commercant;
    private double prix;
    private Utilisateur utilisateur;

    public Panier() {
    }

    public Panier(long id_panier,String titre, String type, String description, String image, String dateEtHeure,
                  long nbrDExemplaires, double prix,Commercant commercant, Utilisateur utilisateur) {
        super();
        this.id_panier=id_panier;
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dateEtHeure = dateEtHeure;
        this.nbrDExemplaires = nbrDExemplaires;
        this.prix=prix;
        this.commercant=commercant;
    }

    public long getId_panier() {
        return id_panier;
    }

    public void setId_panier(long id_panier) {
        this.id_panier = id_panier;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateEtHeure() {
        return dateEtHeure;
    }

    public void setDateEtHeure(String dateEtHeure) {
        this.dateEtHeure = dateEtHeure;
    }


    public long getNbrDExemplaires() {
        return nbrDExemplaires;
    }

    public void setNbrDExemplaires(long nbrDExemplaires) {
        this.nbrDExemplaires = nbrDExemplaires;
    }

    public Commercant getCommercant() {
        return commercant;
    }

    public void setCommercant(Commercant commercant) {
        this.commercant = commercant;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
