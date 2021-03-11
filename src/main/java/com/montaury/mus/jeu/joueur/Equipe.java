package com.montaury.mus.jeu.joueur;

public class Equipe {
    private final Joueur joueur;

    public Equipe(Joueur j) {
        this.joueur = j;
    }

    public Joueur joueur() {
        return joueur;
    }
}