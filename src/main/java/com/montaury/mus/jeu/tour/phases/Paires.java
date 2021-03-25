package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

public class Paires extends Phase {
  public Paires() {
    super("Paires");
  }

  @Override
  protected boolean peutParticiper(Joueur joueur) {
    String affichageJoueur = joueur.equipe().joueur2().nom().equals("") ? joueur.nom() : joueur.nom()+" ("+joueur.equipe().nom()+")";
    System.out.print(affichageJoueur);
    if(joueur.main().aDesPaires()){
      System.out.println(" a une ou plusieurs paires");
    }else{
      System.out.println(" n'a pas de paires");
    }
    return joueur.main().aDesPaires();
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEsku = opposants.joueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurZaku = opposants.joueurZaku().main().getPaires();
    return pairesJoueurEsku.estMeilleureOuEgaleA(pairesJoueurZaku) ? opposants.joueurEsku() : opposants.joueurZaku();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
