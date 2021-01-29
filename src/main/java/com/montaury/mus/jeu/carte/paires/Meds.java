package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;

//Med : La combinaison des trois cartes de même rang
public class Meds extends Paires {

  private final ValeurCarte valeurCarte;

  public Meds(ValeurCarte valeurCarte) {
    super(2);
    this.valeurCarte = valeurCarte;
  }

  @Override
  //Comparer les paires entre deux joueurs
  public boolean estMeilleureOuEgaleA(Paires paires) {
    return paires instanceof Simple ||
      paires instanceof Meds && valeurCarte.valeur() >= ((Meds) paires).valeurCarte.valeur();
  }
}
