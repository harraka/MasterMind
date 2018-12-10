/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermindcode;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HARRAK
 */
public class MastermindCode {

    private static final int NB_COULEURS = 6;
    private static final int MAX = 4; // de saisir ces valeurs
    private static final int ESSAIS_MAX = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] solution = new int[NB_COULEURS];
        int[] chiffres = new int[NB_COULEURS];
        int[] MPBP=new int[2];
        String s1 = "";
        boolean victoire = false;
        int count = 0;
        int MP = 0;
        int BP = 0;
        boolean tmp = false;
        solution = genererSolution();
        System.out.println("J’ai choisit ma combinaison, à vous de deviner ! Les couleurs possibles sont R\n"
                + "J B O V et N. Tapez (RJBO) pour tenter les couleurs R,J,B et O dans l’ordre.\n"
                + "Voici la grille actuelle:");
        System.out.println("|-----------------|");
        System.out.println("|....|...|...|1/" + ESSAIS_MAX + "|");
        System.out.println("|-----------------|");

        do {
            ///Recuperer les données
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (charToIntTab(str) != null && str.length() == MAX) {
                chiffres = charToIntTab(str);
            } else {
                System.out.print("Vous n avez pas respecté les consignes");
                break;
            }

            ////Calcul de BP et MP (mal placé et bien placé)
            MPBP=calculMpBp(chiffres,solution);
            BP=MPBP[0];
            MP=MPBP[1];
            
            /*
            for (int i = 0; i < MAX; i++) {
                if (chiffres[i] == solution[i]) {
                    BP = BP + 1;
                    tmp = true;
                }
                for (int j = 0; j < MAX; j++) {
                    if (i != j && chiffres[i] == solution[j] && !tmp) {
                        MP = MP + 1;
                        break;
                    }
                }
                tmp = false;
            }
            */

            ////////Affichage
            for (int j = 0; j < MAX; j++) {
                System.out.print(solution[j]);
            }
            System.out.println("");
             for (int j = 0; j < MAX; j++) {
                System.out.print(chiffres[j]);
            }
            System.out.println("|----------------------------|");
            s1 = s1 + ("|" + str + "|" + MP + "|" + BP + "|" + count + "/" + ESSAIS_MAX + "|") + '\n';
            System.out.print(s1);
            System.out.println("|----------------------------|");
            MP = 0;
            BP = 0;
            count++;
            /////Victoire
            if (BP == MAX) {
                victoire = true;
                System.out.println("Bravo ! Vous avez gagné en "+count+ "tours !");
            }
            if(count==ESSAIS_MAX) System.out.println("Vous avez perdu");
            
        } while (!victoire && count != ESSAIS_MAX);

    }

    private static int[] calculMpBp(int[] chiffres,int[] solution) {
        
        int[] MPBP=new int[2];
        ArrayList  test=new ArrayList();
        
        for (int i = 0; i < MAX; i++) {
                if (chiffres[i] == solution[i]) {
                    test.add(i);
                    MPBP[0] = MPBP[0]  + 1;
                }
        }
          for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    if (i != j && chiffres[i] == solution[j] && !test.contains(j)) {
                        MPBP[1]  = MPBP[1]  + 1;
                        break;
                    }
                }
               
            }
          
          return MPBP;
    }
    private static int[] charToIntTab(String str) {

        int[] chiffres = new int[MAX];
        if (str.length() == MAX) {
            for (int i = 0; i < MAX; i++) {
                switch (str.charAt(i)) {
                    case 'R':
                        chiffres[i] = 0;
                        break;
                    case 'J':
                        chiffres[i] = 1;
                        break;
                    case 'B':
                        chiffres[i] = 2;
                        break;
                    case 'O':
                        chiffres[i] = 3;
                        break;
                    case 'V':
                        chiffres[i] = 4;
                        break;
                    case 'N':
                        chiffres[i] = 5;
                        break;
                    default:
                        return null;

                }

            }
        } else {
            return null;
        }
        return chiffres;
    }

    private static int[] genererSolution() {
        int[] solution = new int[MAX];
        Random r = new Random();
        for (int i = 0; i < MAX; i++) {
            solution[i] = r.nextInt(NB_COULEURS);
        }
        return solution;
    }
}
