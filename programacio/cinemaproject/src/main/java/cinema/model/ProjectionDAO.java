package cinema.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
