package cinema.main;

import cinema.controller.CinemaController;
import cinema.model.Film;
import cinema.model.FilmDAO;
import cinema.model.Theater;
import cinema.model.TheaterDAO;

import java.io.*;

public class DebuggingMain {

    public static void main(String[] args) {

        //DebuggingMain.test1();

        //DebuggingMain.test2();

        CinemaController.resetCinema("a","a","a");

    }

    private static void test2() {

        System.out.println("____________________________________");

        Film film1 = new Film("Batman", "Un senyor amb diners fa coses", 300);

        FilmDAO.addFilm(film1);

    }

    private static void test1() {

        TheaterDAO.removeTheaters();

        Theater newTheater1 = new Theater(1, "James Bond", 250);
        Theater newTheater2 = new Theater(2, "Oceans Eleven", 170);

        System.out.println(TheaterDAO.addTheater(newTheater1));
        System.out.println(TheaterDAO.addTheater(newTheater2));

        for (Theater curTheater:
                TheaterDAO.getTheaters()) {

            System.out.println(curTheater.toString());

        }

        TheaterDAO.udateTheater(new Theater(1, "Doctor Strange", 100));

        for (Theater curTheater:
                TheaterDAO.getTheaters()) {

            System.out.println(curTheater.toString());

        }

        TheaterDAO.removeTheaters();

        for (Theater curTheater:
                TheaterDAO.getTheaters()) {

            System.out.println(curTheater.toString());

        }
    }

}
