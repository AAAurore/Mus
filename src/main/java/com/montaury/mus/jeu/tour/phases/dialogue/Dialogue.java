package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

import java.util.*;

import static com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix.*;

public class Dialogue {
  private final List<ChoixJoueur> choix = new ArrayList<>();

  public final DialogueTermine derouler(AffichageEvenementsDeJeu affichage, Opposants opposants) {
    Iterator<Joueur> iteratorJoueur = opposants.itererDansLOrdre();
    Map<String, Joueur> joueurNonMuet = new HashMap<String, Joueur>();
    int max = opposants.solo() ? 2 : 4;
    for(int i = 0; i < max; i++){
      Joueur aAjouer = iteratorJoueur.next();
      joueurNonMuet.put(aAjouer.nom(), aAjouer);
    }
    iteratorJoueur = opposants.itererDansLOrdre();
    do {
      Joueur actuel = iteratorJoueur.next();
      if(joueurNonMuet.containsKey(actuel.nom())){
        Choix choixJoueur = actuel.interfaceJoueur.faireChoixParmi(prochainsChoixPossibles());
        affichage.choixFait(actuel, choixJoueur);
        ajouter(choixJoueur, actuel);
        if(joueurNonMuet.size() == 1){break;}
        if(choixJoueur.est(PASO) || choixJoueur.est(TIRA)){
          joueurNonMuet.remove(actuel.nom());
        }
        else{
          Joueur equipier = actuel.nom() == actuel.equipe().joueur1().nom() ? actuel.equipe().joueur2() : actuel.equipe().joueur1();
          joueurNonMuet.remove(equipier);
        }
      }
    }
    while (enCours());
    return new DialogueTermine(choix);
  }

  void ajouter(Choix choix, Joueur joueur) {
    this.choix.add(new ChoixJoueur(choix, joueur));
  }

  boolean enCours() {
    return choix.size() <= 1 || (!dernierChoix().metFinAuDialogue() && !dernierChoix().est(PASO));
  }

  private Choix dernierChoix() {
    return choix.get(choix.size() - 1).choix;
  }

  private List<TypeChoix> prochainsChoixPossibles() {
    return choix.isEmpty() ? TypeChoix.INITIAUX : dernierChoix().prochainsChoixPossibles();
  }

  public static class ChoixJoueur {
    public final Choix choix;
    public final Joueur joueur;

    public ChoixJoueur(Choix choix, Joueur joueur) {
      this.choix = choix;
      this.joueur = joueur;
    }

    public int mise() {
      return choix.mise();
    }
  }
}
