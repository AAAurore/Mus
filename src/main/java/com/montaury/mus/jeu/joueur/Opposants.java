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
    Joueur joueurEsku;
    if(numEquipeEsku == 1){
      joueurEsku = (numJoueurEsku == 1 ? equipe1.joueur1() : equipe1.joueur2());
    }
    else{
      joueurEsku = (numJoueurEsku == 1 ? equipe2.joueur1() : equipe2.joueur2());
    }
    return joueurEsku;
  }

  public Joueur joueurZaku() {
    Joueur joueurZaku;

    if(numEquipeEsku == 1){
      joueurZaku = (numJoueurEsku == 1 ? equipe2.joueur2() : equipe2.joueur1());
    }
    else{
      joueurZaku = (numJoueurEsku == 1 ? equipe1.joueur1() : equipe1.joueur2());
    }
    return joueurZaku;
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
