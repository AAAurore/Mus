package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
  private final Equipe equipe1;
  private final Equipe equipe2;
  private final boolean solo;
  private int numEquipeEsku = 1;
  private int numJoueurEsku = 1;


  public Opposants(Equipe equipe1, Equipe equipe2) {
    this.equipe1 = equipe1;
    this.equipe2 = equipe2;
    this.solo = (equipe1.joueur2().nom().equals(""));
  }

  public Equipe equipe1() {
    return equipe1;
  }

  public Equipe equipe2() {
    return equipe2;
  }

  public void tourner() {
    if(!solo){
      if(numEquipeEsku == 2){ numJoueurEsku = (numJoueurEsku%2)+1; }
    }
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
    if(!solo){ // Si il n'existe pas de deuxième joueur la condition d'échange ne fonctionne pas comme en équipe
      if(numEquipeEsku == 1){
        joueurZaku = (numJoueurEsku == 1 ? equipe2.joueur2() : equipe2.joueur1());
      }
      else{
        joueurZaku = (numJoueurEsku == 1 ? equipe1.joueur1() : equipe1.joueur2());
      }
    }
    else{
      joueurZaku = (numEquipeEsku == 1 ? equipe2.joueur1() : equipe1.joueur1());
    }
    return joueurZaku;
  }

  public Iterator<Joueur> itererDansLOrdre() {
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    if(solo){
      return List.of(joueurEsku(), joueurZaku());
    }
    else {
      Joueur deuxieme;
      Joueur troisieme;
      if(numEquipeEsku == 1){
        deuxieme = (numJoueurEsku == 1 ? equipe2.joueur1() : equipe2.joueur2());
        troisieme = (numJoueurEsku == 1 ? equipe1.joueur2() : equipe1.joueur1());
      }
      else{
        deuxieme = (numJoueurEsku == 1 ? equipe1.joueur2() : equipe1.joueur1());
        troisieme = (numJoueurEsku == 1 ? equipe2.joueur2() : equipe2.joueur1());
      }
      return List.of(joueurEsku(), deuxieme, troisieme, joueurZaku());
    }
  }

    public boolean solo() {
      return solo;
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
      if(opposants.solo) {
        suivant = suivant == opposants.joueurEsku() ? opposants.joueurZaku() : opposants.joueurEsku();
      }
      else{
        String nomSuivant = suivant.nom();
        String nomE1J1 = opposants.equipe1().joueur1().nom();
        String nomE1J2 = opposants.equipe1().joueur2().nom();
        String nomE2J1 = opposants.equipe2().joueur1().nom();
        String nomE2J2 = opposants.equipe2().joueur2().nom();


        if(nomSuivant.equals(nomE1J1)){
          suivant = opposants.equipe2().joueur1();
        }
        else{
          if(nomSuivant.equals(nomE1J2)){
            suivant = opposants.equipe2().joueur2();
          }
          else{
            if(nomSuivant.equals(nomE2J1)){
              suivant = opposants.equipe1().joueur2();
            }
            else{
              if(nomSuivant.equals(nomE2J2)){
                suivant = opposants.equipe1().joueur1();
              }
            }
          }
        }
      }
      return next;
    }
  }
}
