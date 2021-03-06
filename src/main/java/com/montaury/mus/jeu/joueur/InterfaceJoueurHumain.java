package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterfaceJoueurHumain implements InterfaceJoueur {

  private Main main;

  @Override
  public boolean veutAllerMus() {
    while(true){
      println("Souhaitez-vous aller mus ? (o/n)");
      String saisie = new Scanner(System.in).next();

      if(saisie.equals("o")){return true;}
      else if(saisie.equals("n")){return false;}

      println("Erreur ! Veuillez saisir uniquement 'o' ou 'n'");
    }
  }

  @Override
  public List<Carte> cartesAJeter() {
    println("Veuillez saisir les cartes à jeter (ex: 1,3) :");
    String aJeter = new Scanner(System.in).next();
    return Arrays.stream(aJeter.split(","))
      .mapToInt(Integer::parseInt)
      .mapToObj(indiceCarte -> main.cartesDuPlusGrandAuPlusPetit().get(indiceCarte - 1))
      .collect(Collectors.toList());
  }

  @Override
  public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
    while(true) {
      print("Faites un choix entre (en toutes lettres): ");
      println(choixPossibles.stream().map(TypeChoix::nom).collect(Collectors.joining(" | ")));
      String choix = new Scanner(System.in).next();
      if (choixPossibles.contains(TypeChoix.PASO) && choix.equalsIgnoreCase("Paso")) return new Paso();
      if (choixPossibles.contains(TypeChoix.IMIDO) && choix.equalsIgnoreCase("Imido")) return new Imido();
      if (choixPossibles.contains(TypeChoix.HORDAGO) && choix.equalsIgnoreCase("Hordago")) return new Hordago();
      if (choixPossibles.contains(TypeChoix.IDOKI) && choix.equalsIgnoreCase("Idoki")) return new Idoki();
      if (choixPossibles.contains(TypeChoix.TIRA) && choix.equalsIgnoreCase("Tira")) return new Tira();
      if (choixPossibles.contains(TypeChoix.GEHIAGO) && choix.equalsIgnoreCase("Gehiago")) return new Gehiago(1);
      if (choixPossibles.contains(TypeChoix.KANTA) && choix.equalsIgnoreCase("Kanta")) return new Kanta();
      println("Erreur dans la saisie de l'action. Veuilez saisir l'un des choix proposé");
    }
  }

  @Override
  public void nouvelleMain(Main main) {
    this.main = main;
  }

  private void println(String string) {
    System.out.println(string);
  }

  private void print(String string) {
    System.out.print(string);
  }
}
