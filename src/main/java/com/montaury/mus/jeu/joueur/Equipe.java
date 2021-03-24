package com.montaury.mus.jeu.joueur;

public class Equipe {
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final String nom;

    public Equipe(Joueur j1) {
        j1.setEquipe(this);
        Joueur jNull = new Joueur("", new InterfaceJoueurHumain());
        jNull.setEquipe(this);
        this.joueur1 = j1;
        this.joueur2 = jNull;
        this.nom = j1.nom();
    }

    public Equipe(Joueur j1, Joueur j2, String nom) {
        j1.setEquipe(this);
        j2.setEquipe(this);
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.nom = nom;
    }

    public Joueur joueur1() {
        return joueur1;
    }
    public Joueur joueur2() {
        return joueur2;
    }
    public String nom(){ return nom; }
}