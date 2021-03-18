package com.montaury.mus.jeu.joueur;

public class Equipe {
    private final Joueur joueur1;
    private final Joueur joueur2;

    public Equipe(Joueur j1) {
        this.joueur1 = j1;
        this.joueur2 = new Joueur("", new InterfaceJoueurHumain());
    }

    public Equipe(Joueur j1, Joueur j2) {
        this.joueur1 = j1;
        this.joueur2 = j2;
    }

    public Joueur joueur1() {
        return joueur1;
    }
    public Joueur joueur2() {
        return joueur2;
    }
}