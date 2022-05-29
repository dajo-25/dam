package cinema.controller;

import cinema.model.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class CinemaController {

    final private static String BASE_PATH = "." + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "data" + File.separator;

    final private static String THEATERS_FILENAME = "theaters.csv";
    final private static String FILMS_FILENAME = "files.csv";
    final private static String PROJECTIONS_FILENAME = "projections.csv";

    public static boolean resetCinema(String theatersFilePath, String filmsFilePath, String projectionsFilePath){

        boolean removed = ProjectionDAO.removeProjections() && FilmDAO.removeFilms() && TheaterDAO.removeTheaters();

        boolean added = TheaterDAO.loadToDBFromFile(BASE_PATH + theatersFilePath)
                && FilmDAO.loadToDBFromFile(BASE_PATH + filmsFilePath)
                && ProjectionDAO.loadToDBFromFile(BASE_PATH + projectionsFilePath);

        return removed && added;
    }

    public static boolean addProjection(String givenTitle, int givenTheaterNum, String givenTime) {

        if(ProjectionDAO.addProjection(givenTitle, givenTheaterNum, givenTime)){
            return true;
        }else {
            return false;
        }

    }

    public static boolean addFilm(Film givenFilm) {

        if (FilmDAO.addFilm(givenFilm)){
            return true;
        } else {
            return false;
        }

    }

    public static boolean updateTheater(Theater modTheater) {

        if (TheaterDAO.updateTheater(modTheater)){
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Theater> getTheaters(){

        return TheaterDAO.getTheaters();
    }

    public static ArrayList<Film> getAllFilms(){

        return FilmDAO.getFilms();
    }

    public static ArrayList<Film> getFilmsShorterThan(int givenLength){

        return filmsShorterThan(givenLength);

    }

    public static String projectionsClassifiedByTheater() {

        String output = "Projeccions classificades per sales:\n\n";

        for (int i = 1; i <= TheaterDAO.getTheatersRows(); i++){

            output += ProjectionDAO.projectionsFromTheaterToString(i) + "\n";

        }

        return  output;
    }

    public static ArrayList<Film> filmsStartingAt(String givenTime) {

        return FilmDAO.filmsStartingAt(givenTime);
    }

    public static ArrayList<Film> filmsShorterThan(int givenLength) {

        return FilmDAO.filmsShorterThan(givenLength);
    }


}
