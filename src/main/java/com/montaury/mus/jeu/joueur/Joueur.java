package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import java.util.List;

public class Joueur {
  public static Joueur humain(String nom) {
    return new Joueur(nom, new InterfaceJoueurHumain());
  }

  public static Joueur ordinateur(int numero) {
    return new Joueur("Ordinateur"+numero, new InterfaceJoueurOrdinateur());
  }

  private final String nom;
  private Equipe equipe;
  public final InterfaceJoueur interfaceJoueur;
  private final Main main = Main.vide();

  public Joueur(String nom, InterfaceJoueur interfaceJoueur) {
    this.nom = nom;
    this.interfaceJoueur = interfaceJoueur;
  }

  public void setEquipe(Equipe equipe){ this.equipe = equipe;}

  public Equipe equipe() { return equipe; }

  public String nom() {
    return nom;
  }

  public Main main() {
    return main;
  }

  public void donnerCartes(List<Carte> cartes) {
    main.ajouter(cartes);
    interfaceJoueur.nouvelleMain(main);
  }
}
