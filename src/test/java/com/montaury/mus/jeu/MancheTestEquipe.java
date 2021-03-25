package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.*;
import com.montaury.mus.jeu.tour.phases.dialogue.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MancheTestEquipe {

  private Manche manche;

  @BeforeEach
  void setUp() {
    interfaceJoueurEsku = mock(InterfaceJoueur.class);
    interfaceJoueur2 = mock(InterfaceJoueur.class);
    interfaceJoueur3 = mock(InterfaceJoueur.class);
    interfaceJoueurZaku = mock(InterfaceJoueur.class);
    joueurEsku = new Joueur("J1", interfaceJoueurEsku);
    joueur2 = new Joueur("J2", interfaceJoueur2);
    joueur3 = new Joueur("J3", interfaceJoueur3);
    joueurZaku = new Joueur("J4", interfaceJoueurZaku);
    Equipe equipe1 = new Equipe(joueurEsku, joueur2, "equipe1");
    Equipe equipe2 = new Equipe(joueur3, joueurZaku, "equipe2");
    opposants = new Opposants(equipe1, equipe2);
    manche = new Manche(mock(AffichageEvenementsDeJeu.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Kanta());

    Manche.Resultat resultat = manche.jouer(opposants);

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }
/*
  @Test
  void devrait_terminer_la_manche_si_une_equipe_a_40_points() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Imido(), new Gehiago(2));
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Paso());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Paso());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Gehiago(40), new Tira());

    Manche.Resultat resultat = manche.jouer(opposants);

    assertThat(resultat.vainqueur()).isEqualTo(joueurEsku.equipe());
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Kanta());

    manche.jouer(opposants);

    assertThat(opposants.dansLOrdre()).containsExactly(joueurZaku, joueur2, joueur3, joueurEsku);
  }
*/
  private InterfaceJoueur interfaceJoueurEsku;
  private InterfaceJoueur interfaceJoueurZaku;
  private InterfaceJoueur interfaceJoueur2;
  private InterfaceJoueur interfaceJoueur3;
  private Joueur joueurEsku;
  private Joueur joueur2;
  private Joueur joueur3;
  private Joueur joueurZaku;
  private Opposants opposants;

}