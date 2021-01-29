package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import com.montaury.mus.jeu.tour.Tour;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Manche {
  //ATTRIBUT
  private final AffichageEvenementsDeJeu affichage;

  //CONSTRUCTEUR
  public Manche(AffichageEvenementsDeJeu affichage) {
    this.affichage = affichage;
  }

  //MÉTHODES SPÉCIFIQUES
  public Resultat jouer(Opposants opposants) {
    affichage.nouvelleManche();
    Score score = new Score(opposants);
    do {
      new Tour(affichage).jouer(opposants, score);
      affichage.tourTermine(opposants, score);
      opposants.tourner();
    } while (score.vainqueur().isEmpty());
    return new Resultat(score.vainqueur().get(), score.pointsVaincu().get());
  }

  //AJOUT DE LA CLASSE SCORE
  public static class Score {
    //ATTRIBUTS
    private static final int POINTS_POUR_TERMINER_MANCHE = 40;

    private final Map<Joueur, Integer> scoreParJoueur = new HashMap<>();

    //CONSTRUCTEUR
    public Score(Opposants opposants) {
      scoreParJoueur.put(opposants.joueurEsku(), 0);
      scoreParJoueur.put(opposants.joueurZaku(), 0);
    }

    //GETTER
    public Map<Joueur, Integer> scoreParJoueur() {
      return scoreParJoueur;
    }

    //MÉTHODES SPÉCIFIQUES
    public void scorer(Joueur joueur, int points) {
      if (vainqueur().isEmpty()) {
        scoreParJoueur.put(joueur, Math.min(scoreParJoueur.get(joueur) + points, POINTS_POUR_TERMINER_MANCHE));
      }
    }

    public void remporterManche(Joueur joueur) {
      scoreParJoueur.put(joueur, POINTS_POUR_TERMINER_MANCHE);
    }

    public Optional<Joueur> vainqueur() {
      return scoreParJoueur.keySet().stream().filter(joueur -> scoreParJoueur.get(joueur) == POINTS_POUR_TERMINER_MANCHE).findAny();
    }

    public Optional<Integer> pointsVaincu() {
      return vainqueur().isEmpty() ?
        Optional.empty() :
        scoreParJoueur.values().stream().filter(points -> points < POINTS_POUR_TERMINER_MANCHE).findAny();
    }
  }

  //AJOUT DE LA CLASSE RESULTAT
  public static class Resultat {
    //ATTRIBUTS
    private final Joueur vainqueur;
    private final int pointsVaincu;

    //CONSTRUCTEUR
    public Resultat(Joueur joueur, int pointsVaincu) {
      vainqueur = joueur;
      this.pointsVaincu = pointsVaincu;
    }

    //GETTER / SETTER
    public Joueur vainqueur() {
      return vainqueur;
    }

    public int pointsVaincu() {
      return pointsVaincu;
    }
  }
}
