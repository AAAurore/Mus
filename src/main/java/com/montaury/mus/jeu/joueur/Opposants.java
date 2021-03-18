package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
  private Equipe equipe1;
  private Equipe equipe2;
  private int numEquipeEsku = 1;
  private int numJoueurEsku = 1;


  public Opposants(Equipe equipe1, Equipe equipe2) {
    this.equipe1 = equipe1;
    this.equipe2 = equipe2;
  }

  public void tourner() {
    //if(numEquipeEsku == 2){ numJoueurEsku = (numJoueurEsku%2)+1; }
    numEquipeEsku = (numEquipeEsku%2)+1;
  }

  public Joueur joueurEsku() {
    return (numEquipeEsku == 1 ? equipe1.joueur() : equipe2.joueur());
  }

  public Joueur joueurZaku() {
    return (numEquipeEsku == 1 ? equipe2.joueur() : equipe1.joueur());
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
