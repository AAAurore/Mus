package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Partie {
  //ATTRIBUT
  private final AffichageEvenementsDeJeu affichage;
  private final TypePartie typePartie = TypePartie.SOLOORDI;

  //CONSTRUCTEUR
  public Partie(AffichageEvenementsDeJeu affichage) {
    this.affichage = affichage;
  }

  //MÉTHODES SPÉCIFIQUES
  public Resultat jouer(Opposants opposants) {
    affichage.nouvellePartie();
    Partie.Score score = new Partie.Score(opposants);
    Optional<Joueur> vainqueur;
    do {
      Manche.Resultat resultat = new Manche(affichage).jouer(opposants);
      vainqueur = score.enregistrer(resultat);
      affichage.mancheTerminee(score);
    } while (vainqueur.isEmpty());
    return new Resultat(vainqueur.get(), score);
  }

  //AJOUT DE LA CLASSE SCORE
  public static class Score {
    //ATTRIBUTS
    private static final int NB_MANCHES_A_GAGNER = 3;

    private final List<Manche.Resultat> resultatManches = new ArrayList<>();
    private final Map<Joueur, Integer> manchesGagneesParJoueur = new HashMap<>();

    //CONSTRUCTEUR
    public Score(Opposants opposants) {
      this.manchesGagneesParJoueur.put(opposants.joueurEsku(), 0);
      this.manchesGagneesParJoueur.put(opposants.joueurZaku(), 0);
    }

    //MÉTHODES SPÉCIFIQUES
    public Optional<Joueur> enregistrer(Manche.Resultat score) {
      resultatManches.add(score);
      manchesGagneesParJoueur.put(score.vainqueur(), manchesGagneesParJoueur.get(score.vainqueur()) + 1);
      return vainqueur();
    }

    //GETTERS
    public List<Manche.Resultat> resultatManches() {
      return resultatManches;
    }

    public Optional<Joueur> vainqueur() {
      return manchesGagneesParJoueur.keySet().stream()
        .filter(joueur -> manchesGagneesParJoueur.get(joueur) == NB_MANCHES_A_GAGNER).findAny();
    }
  }

  //AJOUT DE LA CLASSE RESULTAT
  public static class Resultat {
    //ATTRIBUTS
    private final Joueur vainqueur;
    private final Score score;

    //CONSTRUCTEUR
    private Resultat(Joueur vainqueur, Score score) {
      this.vainqueur = vainqueur;
      this.score = score;
    }

    //GETTERS
    public Joueur vainqueur() {
      return vainqueur;
    }

    public Score score() {
      return score;
    }
  }
}
