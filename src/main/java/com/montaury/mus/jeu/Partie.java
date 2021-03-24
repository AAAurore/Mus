package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Opposants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Partie {
  private final AffichageEvenementsDeJeu affichage;

  private TypePartie typePartie;

  public Partie(AffichageEvenementsDeJeu affichage ,TypePartie typePartie ) {
    this.affichage = affichage;
    this.typePartie = typePartie;
  }

  public TypePartie typePartie() { return typePartie; }

  public Resultat jouer(Opposants opposants) {
    affichage.nouvellePartie();
    Partie.Score score = new Partie.Score(opposants);
    Optional<Equipe> vainqueur;
    do {
      Manche.Resultat resultat = new Manche(affichage).jouer(opposants);
      vainqueur = score.enregistrer(resultat);
      affichage.mancheTerminee(score);
    } while (vainqueur.isEmpty());
    return new Resultat(vainqueur.get(), score);
  }

  public static class Score {
    private static final int NB_MANCHES_A_GAGNER = 3;

    private final List<Manche.Resultat> resultatManches = new ArrayList<>();
    private final Map<Equipe, Integer> manchesGagneesParEquipe = new HashMap<>();

    public Score(Opposants opposants) {
      this.manchesGagneesParEquipe.put(opposants.equipe1(), 0);
      this.manchesGagneesParEquipe.put(opposants.equipe2(), 0);
    }

    public Optional<Equipe> enregistrer(Manche.Resultat score) {
      resultatManches.add(score);
      manchesGagneesParEquipe.put(score.vainqueur(), manchesGagneesParEquipe.get(score.vainqueur()) + 1);
      return vainqueur();
    }

    public List<Manche.Resultat> resultatManches() {
      return resultatManches;
    }

    public Optional<Equipe> vainqueur() {
      return manchesGagneesParEquipe.keySet().stream()
        .filter(equipe -> manchesGagneesParEquipe.get(equipe) == NB_MANCHES_A_GAGNER).findAny();
    }
  }

  public static class Resultat {
    private final Equipe vainqueur;
    private final Score score;

    private Resultat(Equipe vainqueur, Score score) {
      this.vainqueur = vainqueur;
      this.score = score;
    }

    public Equipe vainqueur() {
      return vainqueur;
    }

    public Score score() {
      return score;
    }
  }
}
