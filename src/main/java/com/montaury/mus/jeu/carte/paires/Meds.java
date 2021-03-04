package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;

public class Meds extends Paires {

  private final ValeurCarte valeurCarte;

  public Meds(ValeurCarte valeurCarte) {
    super(2);
    this.valeurCarte = valeurCarte;
  }

  @Override
  public boolean estMeilleureOuEgaleA(Paires paires) {
    return paires instanceof Simple ||
      paires instanceof Meds && valeurCarte.valeur() >= ((Meds) paires).valeurCarte.valeur();
  }
}
