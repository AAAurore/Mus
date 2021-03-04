package com.montaury.mus.jeu.joueur;

public class Equipe {
    private final Joueur joueur1;
    private final Joueur joueur2;

    public Equipe(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public Joueur joueur1() {
        return joueur1;
    }
    public Joueur joueur2() {
        return joueur2;
    }
}
