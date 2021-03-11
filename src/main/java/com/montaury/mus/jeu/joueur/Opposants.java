package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
  private Equipe equipeEsku;
  private Equipe equipeZaku;

  public Opposants(Equipe equipe1, Equipe equipe2) {
    this.equipeEsku = equipe1;
    this.equipeZaku = equipe2;
  }

  public void tourner() {
    Equipe tmp = equipeEsku;
    equipeEsku = equipeZaku;
    equipeZaku = tmp;
  }

  public Joueur joueurEsku() {
    return equipeEsku.joueur();
  }

  public Joueur joueurZaku() {
    return equipeZaku.joueur();
  }

  public Iterator<Joueur> itererDansLOrdre() {
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku(), joueurZaku());
  }

  private static class IteratorInfini implements Iterator<Joueur> {
    private final Opposants opposants;
    private Joueur suivant;

    public IteratorInfini(Opposants opposants) {
      this.opposants = opposants;
      suivant = opposants.joueurEsku();
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Joueur next() {
      Joueur next = suivant;
      suivant = suivant == opposants.joueurEsku() ? opposants.joueurZaku() : opposants.joueurEsku();
      return next;
    }
  }
}
