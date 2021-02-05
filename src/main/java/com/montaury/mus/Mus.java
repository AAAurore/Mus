package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.jeu.joueur.AffichageConsoleEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import java.util.Scanner;

public class Mus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    Scanner scanner = new Scanner(System.in);//réglage de l'input sur le clavier
    String nomJoueur="";
    for(;;)
    {
      nomJoueur = scanner.nextLine();//scanne de l'input user
      if(!nomJoueur.equals("")){break;}
      System.out.println("Merci de rentrer votre nom");
    }
    Joueur humain = Joueur.humain(nomJoueur);//enregistrement du nom du joueur
    Partie partie = new Partie(new AffichageConsoleEvenementsDeJeu(humain));
    Partie.Resultat resultat = partie.jouer(new Opposants(humain, Joueur.ordinateur()));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
