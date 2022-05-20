package cinema.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class TheaterDAO {

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

    public static boolean addTheater(Theater theater) {

        Connection bdconnection = null;
        Statement statement = null;
        String sql;
        int nRows = 0;

        try {
            bdconnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdconnection.createStatement();
            sql = "INSERT INTO theater values(" + theater.getNumber() +", '" +
                    theater.getName() + "', " + theater.getCapacity() + ");";

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

    public static ArrayList<Theater> getTheaters(){

        ArrayList<Theater> theaters = new ArrayList<>();

        Connection bdConnection;
        Statement statement;
        ResultSet result;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "SELECT * FROM theater;";

            result = statement.executeQuery(sqlSentence);

            while (result.next()){

                Theater theater = new Theater();
                theater.setNumber(result.getInt("number"));
                theater.setName(result.getString("name"));
                theater.setCapacity(result.getInt("capacity"));

                theaters.add(theater);

            }

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return null;
        }

        return theaters;

    }

    public static boolean removeTheaters(){
        //Esborra totes les sales del cinema

        Connection bdConnection;
        Statement statement;
        int nRows;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "DELETE FROM theater;";

            nRows = statement.executeUpdate(sqlSentence);

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return false;
        }

        return nRows == 0;


    }

    public static boolean updateTheater(Theater theater){
        //Ha d'actualitzar les dades de la sala (nom i capacitat);
        Connection bdConnection;
        Statement statement;
        int nRows = -1;
        String sqlSentence;

        try{

            bdConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = bdConnection.createStatement();

            sqlSentence = "UPDATE theater\n" +
                    "SET name = '" + theater.getName() + "', capacity = " + theater.getCapacity() + "\n" +
                    "WHERE number = " + theater.getNumber() + ";";

            nRows = statement.executeUpdate(sqlSentence);

            statement.close();
            bdConnection.close();

        }catch (SQLException exception){
            return false;
        }

        return true;

    }

    public static boolean loadToDBFromFile(String filePath) {

        try{
            String currentLine;

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            currentLine = bufferedReader.readLine();

            String [] currentData;
            while (currentLine != null){

                currentData = currentLine.split(",");

                Theater currentTheater = new Theater();
                currentTheater.setNumber(Integer.parseInt(currentData[0]));
                currentTheater.setName(currentData[1]);
                currentTheater.setCapacity(Integer.parseInt(currentData[2]));

                /**
                 * Utilitzo el addTheater(). Cada cop que es crida genera molta ineficiencia per l'obertura
                 * i tancament dels ports, però per comoditat reaprofito el codi. Si fos necessari, ho reescriuria.
                 */
                TheaterDAO.addTheater(currentTheater);

                currentLine = bufferedReader.readLine();

            }

            bufferedReader.close();
            fileReader.close();

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }

    }
}
