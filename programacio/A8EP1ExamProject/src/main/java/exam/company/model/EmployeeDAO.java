package exam.company.model;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    private static final String DB_URL = "jdbc:postgresql://tyke.db.elephantsql.com/osamaozl";
    private static final String DB_USER = "osamaozl";
    private static final String DB_PASSWD = "GG-qJ7M8CL1NUrB2jYbipMS4pYWEAsVa";

    public static ArrayList<Employee> getAllEmployees() {

        ArrayList<Employee> employees = null;

        try {

            Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            Statement statement = dbConnection.createStatement();
            String sqlSentence = "SELECT * FROM employee;";

            ResultSet rS = statement.executeQuery(sqlSentence);

            while (rS.next()){
                //String dni, String name, int age, String email
                Employee currentEmployee = new Employee(rS.getString("dni").replace("$",
                        "'"), rS.getString("name").replace("$", "'"),
                        rS.getInt("age"), rS.getString("email")
                        .replace("$", "'"));

                employees.add(currentEmployee);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;

    }
}
