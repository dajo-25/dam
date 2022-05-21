package cinema.main;

import cinema.controller.CinemaController;
import cinema.model.Film;
import cinema.model.ProjectionDAO;
import cinema.model.Theater;
import cinema.model.TheaterDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CinemaMain {

    public static void main(String args[]) throws SQLException {

        Scanner scan = new Scanner(System.in);
        int option, resetStatus;

        //Reset DB

        do {
            System.out.println("~~~~ GESTIÓ DEL CINEMA ~~~~");
            System.out.println("\t0. Sortir");
            System.out.println("\t1. Veure totes les sales");
            System.out.println("\t2. Veure totes les pel·lícules");
            System.out.println("\t3. Veure les pel·lícules que es projecten a una hora determinada");
            System.out.println("\t4. Veure les pel·lícules que duren menys de un nombre determinat de minuts");
            System.out.println("\t5. Veure les projeccions de cada sala (pel·lícules classificades per sala)");
            System.out.println("\t6. Afegir una projecció");
            System.out.println("\t7. Afegir una pel·lícula");
            System.out.println("\t8. Modificar la capacitat d'una sala");
            //System.out.println("\t9. Debugging");
            System.out.print("Opció: ");
            option = Integer.parseInt(scan.nextLine());

            switch(option) {
                case 0:
                    System.out.println("Tancant el gestor del cinema...");
                    break;
                case 1:
                    System.out.println(CinemaController.theatersToString());
                    break;
                case 2:
                    System.out.println(CinemaController.filmsToString());
                    break;
                case 3:

                    System.out.println("De quina hora vol consultar les pel·lícules? (hh:mm)");

                    ArrayList<Film> films1 = CinemaController.filmsStartingAt(scan.nextLine());

                    System.out.println(CinemaController.filmsToString(films1));

                    break;
                case 4:

                    System.out.println("Menys de quants minuts ha de durar la pel·lícula?");

                    ArrayList<Film> films2 = CinemaController.filmsShorterThan(Integer.parseInt(scan.nextLine()));

                    System.out.println(CinemaController.filmsToString(films2));

                    break;
                case 5:

                    System.out.println(CinemaController.projectionsClassifiedByTheater());
                    break;
                case 6:

                    String givenTitle, givenTime;
                    int givenTheaterNum;

                    System.out.println("Introdueixi les dades de la projecció a continuació."
                        + "\nTitol de la pel·lícula que es projectarà:");
                    givenTitle = scan.nextLine();

                    System.out.println("Número de sala:");
                    givenTheaterNum = Integer.parseInt(scan.nextLine());

                    System.out.println("Hora a la que es projectarà:");
                    givenTime = scan.nextLine();

                    if(CinemaController.addProjection(givenTitle, givenTheaterNum, givenTime)){
                        System.out.println("Projecció programada exitosament.");
                    }else{
                        System.out.println("No s'ha pogut programar la projecció");
                    }

                    break;
                case 7:
                    Film givenFilm = new Film();

                    System.out.println("Introdueixi les dades de la pel·lícula que vol afegir:"
                        + "\nTítol:");
                    givenFilm.setTitle(scan.nextLine());

                    System.out.println("Descripció:");
                    givenFilm.setDescription(scan.nextLine());

                    System.out.println("Minuts de duració:");
                    givenFilm.setDuration(Integer.parseInt(scan.nextLine()));

                    if(CinemaController.addFilm(givenFilm)){
                        System.out.println("Pel·lícula afegida exitosament.");
                    }else{
                        System.out.println("No s'ha pogut afegir la pel·lícula");
                    }

                    break;
                case 8:

                    Theater modTheater = new Theater();

                    System.out.println("Quina sala vol modificar? (número)");
                    int theaterNum = Integer.parseInt(scan.nextLine());
                    modTheater.setNumber(theaterNum);

                    System.out.println("Quin és el nom de la sala?");
                    modTheater.setName(scan.nextLine());

                    System.out.println("Quina capacitat vol que tingui?");
                    modTheater.setCapacity(Integer.parseInt(scan.nextLine()));

                    if (CinemaController.updateTheater(modTheater)){
                        System.out.println("Sala modificada exitosament.");
                    } else {
                        System.out.println("No s'ha pogut modificar la sala");
                    }

                    break;
                case 9:

                    CinemaController.resetCinema("theaters.csv", "films.csv"
                            ,"projections.csv");

                    break;
                default:
                    System.out.println("Opció incorrecta o no implementada");
            }

            System.out.println("\n");

        } while(option != 0);

    }
}