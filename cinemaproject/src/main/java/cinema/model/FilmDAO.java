package cinema.model;

import java.sql.*;
import java.util.ArrayList;

public class FilmDAO {

    private static final String DB_URL = "jdbc:postgresql://tyke.db.elephantsql.com/pypgybci";
    private static final String DB_USER = "pypgybci";
    private static final String DB_PASSWD = "RcrHKFW4ktP4eTIdjfgLi-nl7EuVJg8F";

    /**
     * Passos per establir connexió amb la BD
     * 1. Definir les dades bàsiques de connexió (URL, USER, PASSWD)
     * 2. A partir de Driver, obternir la connexió.
     * 3. Definir la instrucció SQL
     * 4. Executar l'SQL
     * 5. Recollir les dades obtingudes per l'execució SQL (si s'escau (si s'utilitza SELECT))
     * 6. Tancar tota connexió.
     *
     * IMPORTANTS EL PAS 2 I 6. ÉS EXTREMADAMENT INEFICIENT, PERÒ S'HA DE FER PER SEGURETAT. QUAN S'OBRA LA
     * CONNEXIÓ S'OBRE UN PORT AL TEU ORDINADOR I UN PORT AL SERVIDOR, PER TANT, S'HA DE TANCAR.
     */

    public static boolean addFilm(Film film) {

        Connection bdconnection = null;
        Statement statement = null;
        String sql;
        int nRows = 0;

        try {
            bdconnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdconnection.createStatement();
            sql = "INSERT INTO film (title, description, duration) values('" + film.getTitle() +"', '" +
                    film.getDescription() + "', " + film.getDuration() + ");";

            /**
             * executeQuery() per als SELECT
             * executeUpdate() per als UPDATE, DELETE, INSERT
             *
             */

            nRows = statement.executeUpdate(sql);

            statement.close();
            bdconnection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nRows == 1;

    }

    public static boolean removeFilms(){

        //Esborra totes les sales del cinema

        Connection bdConnection;
        Statement statement;
        int nRows;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "DELETE FROM film;";

            nRows = statement.executeUpdate(sqlSentence);

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return false;
        }

        return nRows == 0;


    }

    public static ArrayList<Film> getFilms(){

        ArrayList<Film> films = new ArrayList<>();

        Connection bdConnection;
        Statement statement;
        ResultSet result;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "SELECT * FROM film;";

            result = statement.executeQuery(sqlSentence);

            while (result.next()){

                Film film = new Film();
                film.setTitle(result.getString("title"));
                film.setDescription(result.getString("description"));
                film.setDuration(result.getInt("duration"));

                films.add(film);

            }

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return null;
        }

        return films;


    }

    public static ArrayList<Film> getProjectedFilmsByTheater(int tNumber){

        ArrayList<Film> films = new ArrayList<>();

        Connection bdConnection;
        Statement statement;
        ResultSet result;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "SELECT * "
                + "FROM film F projection P"
                + "WHERE F.id = P.id_film AND P.id_theater = " + tNumber + ";";

            result = statement.executeQuery(sqlSentence);

            while (result.next()){

                Film film = new Film();
                film.setTitle(result.getString("title"));
                film.setDescription(result.getString("description"));
                film.setDuration(result.getInt("duration"));

                films.add(film);

            }

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return null;
        }

        return films;

    }

    public static ArrayList<Film> getProjectedFilmsByHour(String hour){

        ArrayList<Film> films = new ArrayList<>();

        Connection bdConnection;
        Statement statement;
        ResultSet result;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "SELECT * "
                    + "FROM film F projection P"
                    + "WHERE F.id = P.id_film AND P.hour LIKE '" + hour + "';";

            result = statement.executeQuery(sqlSentence);

            while (result.next()){

                Film film = new Film();
                film.setTitle(result.getString("title"));
                film.setDescription(result.getString("description"));
                film.setDuration(result.getInt("duration"));

                films.add(film);

            }

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return null;
        }

        return films;

    }

    public static Film getFilmByTitle(String title){

        Film foundFilm;

        Connection bdConnection;
        Statement statement;
        ResultSet result;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "SELECT * FROM film WHERE title = " + title + ";";

            result = statement.executeQuery(sqlSentence);

            foundFilm = new Film(result.getString("title"), result.getString("description"),
                    result.getInt("duration"));

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return null;
        }

        return foundFilm;

    }

}
