package com.montaury.mus.jeu.carte.paires;


import com.montaury.mus.jeu.carte.ValeurCarte;

//Simple : La combinaison minimale de deux cartes de même rang
public class Simple extends Paires {
  private final ValeurCarte valeurCarte;

  public Simple(ValeurCarte valeurCarte) {
    super(1);
    this.valeurCarte = valeurCarte;
  }

  public ValeurCarte valeur() {
    return valeurCarte;
  }

  //Comparer les paires entre deux joueurs
  public boolean estMeilleureOuEgaleA(Paires paires) {
    return paires instanceof Simple && valeurCarte.valeur() >= ((Simple) paires).valeur().valeur();
  }
}