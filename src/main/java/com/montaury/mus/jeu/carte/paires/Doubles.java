package com.montaury.mus.jeu.carte.paires;

//Double : La combinaison de 4 cartes d'un joueur dont toutes sont du même rang
//ou si elles sont appariées 2 par 2
public class Doubles extends Paires {

  private final Simple meilleurePaire;
  private final Simple moinsBonnePaire;

  public Doubles(Simple paire1, Simple paire2) {
    super(3);
    this.meilleurePaire = paire1.valeur().valeur() > paire2.valeur().valeur() ? paire1 : paire2;
    this.moinsBonnePaire = paire1.valeur().valeur() > paire2.valeur().valeur() ? paire2 : paire1;
  }

  @Override
  //Comparer les paires entre deux joueurs
  public boolean estMeilleureOuEgaleA(Paires paires) {
    if (!(paires instanceof Doubles)) {
      return true;
    }
    Doubles autresDoubles = (Doubles) paires;
    if (meilleurePaire.valeur().valeur() > autresDoubles.meilleurePaire.valeur().valeur()) {
      return true;
    }
    if (meilleurePaire.valeur() == autresDoubles.meilleurePaire.valeur()) {
      return moinsBonnePaire.valeur().valeur() >= autresDoubles.moinsBonnePaire.valeur().valeur();
    }
    return false;
  }
}
