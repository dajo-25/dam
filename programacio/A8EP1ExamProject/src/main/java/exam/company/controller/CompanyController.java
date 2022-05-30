package exam.company.controller;

import exam.company.model.Employee;
import exam.company.model.EmployeeDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyController {

    private static final int CURRENT_MONTH = LocalDate.now().getMonthValue();
    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    public static int getCurrentMonth() {
        return CURRENT_MONTH;
    }

    public static int getCurrentYear() {
        return CURRENT_YEAR;
    }

    public static ArrayList<Employee> getAllEmployees() {

        return EmployeeDAO.getAllEmployees();

    }
}
