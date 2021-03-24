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

    Joueur humain = Joueur.humain(nomJoueur);//enregistrement du nom du joueur
    Opposants adversaires = null;
    Equipe equipeHumaine = null;
    switch (typeDeLaPartie) {
      case SOLOORDI -> {
        equipeHumaine = new Equipe(humain);
        adversaires = new Opposants(equipeHumaine, new Equipe(Joueur.ordinateur(1)));
      }
      case MULTIORDIS -> {
        System.out.print("Entrez votre nom d'équipe: ");
        String nomEquipeHumain="";
        String nomEquipeOrdinateurs = "Ordinateurs";
        while(true)
        {
          nomEquipeHumain = scanner.nextLine();//scanne de l'input user
          if(!nomEquipeHumain.equals("")){break;}
          System.out.println("Merci de rentrer votre nom d'équipe");
        }
        equipeHumaine = new Equipe(humain, Joueur.ordinateur(1),nomEquipeHumain);

        if(nomEquipeHumain.equals(nomEquipeOrdinateurs)){nomEquipeOrdinateurs = "Adversaires";}
        adversaires = new Opposants(equipeHumaine, new Equipe(Joueur.ordinateur(2), Joueur.ordinateur(3),nomEquipeOrdinateurs));
      }
    }
    Partie partie = new Partie(new AffichageConsoleEvenementsDeJeu(equipeHumaine),typeDeLaPartie);
    Partie.Resultat resultat = partie.jouer(adversaires);
    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
