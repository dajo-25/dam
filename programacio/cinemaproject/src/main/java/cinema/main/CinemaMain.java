package cinema.main;

import cinema.controller.CinemaController;

import java.sql.SQLException;
import java.util.Scanner;

public class CinemaMain {
    public static void main(String args[]) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        int option, resetStatus;

        //Reset DB

        do {
            System.out.println("~~~~ GESTIÓ DEL CINEMA ~~~~");
            System.out.println("\t0. Sortir");
            System.out.println("\t1. Veure totes les sales");
            System.out.println("\t2. Veure totes les pel·lícules");
            System.out.println("\t3. Veure les pel·lícules que es projecten a les 20:00");
            System.out.println("\t4. Veure les pel·lícules que duren menys de 2 hores");
            System.out.println("\t5. Veure les projeccions de cada sala (pel·lícules classificades per sala");
            System.out.println("\t6. Afegir una projecció");
            System.out.println("\t7. Afegir una pel·lícula");
            System.out.println("\t8. Modificar la capacitat d'una sala");
            System.out.println("\t9. Debugging");
            System.out.print("Opció: ");
            option = keyboard.nextInt();

            switch(option) {
                case 0:
                    System.out.println("Tancant el gestor del cinema...");
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:

                    CinemaController.resetCinema("a", "a", "a");

                    break;
                default:
                    System.out.println("Opció incorrecta o no implementada");
            }

        } while(option != 0);

    }
}