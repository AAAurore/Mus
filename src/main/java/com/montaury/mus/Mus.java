package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.jeu.TypePartie;
import com.montaury.mus.jeu.joueur.AffichageConsoleEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Opposants;
import java.util.Scanner;

public class Mus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    Scanner scanner = new Scanner(System.in);//réglage de l'input sur le clavier
    String nomJoueur="";
    while(true)
    {
      nomJoueur = scanner.nextLine();//scanne de l'input user
      if(!nomJoueur.equals("")){break;}
      System.out.println("Merci de rentrer votre nom");
    }
    TypePartie typeDeLaPartie;
    while(true)
    {
      System.out.println("Quelle type de partie souhaitez-vous jouer ? (Solo contre un ordinateur : 1; Duo avec des ordinateurs : 2)");
      String saisie = new Scanner(System.in).next();
      if(saisie.equals("1")){typeDeLaPartie = TypePartie.SOLOORDI; break;} // Choix par défaut
      else if(saisie.equals("2")){typeDeLaPartie = TypePartie.MULTIORDIS; break;}
      System.out.println("Erreur ! Veuillez saisir uniquement 1 ou 2");
    }
    Equipe humain = new Equipe (Joueur.humain(nomJoueur));//enregistrement du nom du joueur
    Equipe ordinateur = new Equipe(Joueur.ordinateur(1));
    Partie partie = new Partie(new AffichageConsoleEvenementsDeJeu(humain),typeDeLaPartie);
    Opposants adversaires = new Opposants(humain, ordinateur);
    /*
    switch(partie.typePartie()){
      case SOLOORDI -> adversaires = new Opposants(humain, Joueur.ordinateur(1));

      case MULTIORDIS -> adversaires = new Opposants(Equipe(humain, Joueur.ordinateur(1)),Equipe(Joueur.ordinateur(2), Joueur.ordinateur(3)))
    }
    */
    Partie.Resultat resultat = partie.jouer(adversaires);
    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
