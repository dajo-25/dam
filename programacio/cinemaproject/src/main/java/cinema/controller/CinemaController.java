package cinema.controller;

import cinema.model.FilmDAO;
import cinema.model.ProjectionDAO;
import cinema.model.Theater;
import cinema.model.TheaterDAO;

public class CinemaController {

    public static boolean resetCinema(){

        boolean removed = TheaterDAO.removeTheaters() && FilmDAO.removeFilms() && ProjectionDAO.removeProjections();

        return removed;
    }

}
