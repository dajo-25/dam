package cinema.controller;

import cinema.model.FilmDAO;
import cinema.model.ProjectionDAO;
import cinema.model.TheaterDAO;

import java.io.File;

public class CinemaController {

    final private static String BASE_PATH = "." + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "data" + File.separator;

    final private static String THEATERS_FILENAME = "theaters.csv";
    final private static String FILMS_FILENAME = "files.csv";
    final private static String PROJECTIONS_FILENAME = "projections.csv";

    public static boolean resetCinema(String theatersFilePath, String filmsFilePath, String projectionsFilePath){

        boolean removed = ProjectionDAO.removeProjections() && FilmDAO.removeFilms() && TheaterDAO.removeTheaters();
        boolean added = TheaterDAO.loadToDBFromFile(BASE_PATH + THEATERS_FILENAME)
                && FilmDAO.loadToDBFromFile(BASE_PATH + FILMS_FILENAME)
                && ProjectionDAO.loadToDBFromFile(BASE_PATH + PROJECTIONS_FILENAME);


        return removed && added;
    }

}
