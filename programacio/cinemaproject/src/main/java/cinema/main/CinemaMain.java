package cinema.main;

import cinema.controller.CinemaController;
import cinema.model.Film;
import cinema.model.FilmDAO;
import cinema.model.Theater;
import cinema.model.TheaterDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                default:
                    System.out.println("Opció incorrecta o no implementada");
            }

        } while(option != 0);

    }
}