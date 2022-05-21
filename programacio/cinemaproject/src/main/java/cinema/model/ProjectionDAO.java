package cinema.model;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class ProjectionDAO {

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


    public static boolean addProjection(String title, int theaterNum, String time){

        Connection bdconnection = null;
        Statement statement = null;
        String sql;
        int nRows = 0;

        try {
            bdconnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdconnection.createStatement();

            sql = "INSERT INTO projection VALUES(" + theaterNum
                    + ", (SELECT id FROM film WHERE title LIKE('"  + title + "'))"
                    + ", '" + time +"');";

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

    public static boolean loadToDBFromFile(String filePath) {

        try{
            String currentLine;

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            currentLine = bufferedReader.readLine();

            Connection dbConnection;
            Statement statement;
            String sentence = "";

            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = dbConnection.createStatement();

            String [] currentData;
            while (currentLine != null){

                currentData = currentLine.split(",");

                //System.out.println(Integer.parseInt(currentData[1]) + " | " + currentData[0] + " | " + currentData[2]);

                sentence = "INSERT INTO projection VALUES(" + Integer.parseInt(currentData[1])
                        + ", (SELECT id FROM film WHERE title LIKE('"  + currentData[0] + "'))"
                        + ", '" + currentData[2] +"');";

                statement.executeUpdate(sentence);

                /**
                 * Aquí no tinc funció addProjection(), així que sí que faré tot a manija.
                 */

                currentLine = bufferedReader.readLine();

            }

            bufferedReader.close();
            fileReader.close();

            statement.close();
            dbConnection.close();

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    public static boolean removeProjections() {

        //Esborra totes les sales del cinema

        Connection bdConnection;
        Statement statement;
        int nRows;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "DELETE FROM projection;";

            statement.executeUpdate(sqlSentence);
            nRows = statement.getMaxRows();

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return false;
        }

        return nRows == 0;



    }


    public static String projectionsFromTheaterToString(int givenTheaterNum) {

        String output = "Les projeccions programades per a la sala \"";

        try{

            Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            Statement statement = dbConnection.createStatement();
            String sql = "SELECT F.title, P.hour, T.name FROM projection P INNER JOIN theater T "
                    + "ON P.id_theater = T.number INNER JOIN film F ON P.id_film = F.id WHERE T.number = "
                    + givenTheaterNum + ";";

            output += statement.executeQuery("SELECT name FROM theater WHERE number =" + givenTheaterNum + ";")
                    .getString(0) + "\" són:\n";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                output += resultSet.getString("title") + " | A les "
                        + resultSet.getString("hour") + "\n";

            }

            statement.close();
            dbConnection.close();

            return output;
        }catch (Exception e){
            return "S'ha produït un error";
        }

    }
}
