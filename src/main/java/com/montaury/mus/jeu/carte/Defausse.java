package com.montaury.mus.jeu.carte;

import java.util.ArrayList;
import java.util.List;

public class Defausse {
  private final List<Carte> cartes = new ArrayList<>();

  public void jeter(List<Carte> cartes) {
    this.cartes.addAll(cartes);
  }

  public List<Carte> reprendreCartes() {
    List<Carte> cartesDefaussees = new ArrayList<>(this.cartes);
    this.cartes.clear();
    return cartesDefaussees;
  }
}
