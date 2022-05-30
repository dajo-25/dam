package exam.company.main;

import exam.company.controller.CompanyController;
import exam.company.model.Category;
import exam.company.model.Employee;
import exam.company.model.Payroll;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CompanyMain {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int option = 0, cat = 0, worker = 0, irpf = 0, salary = 0, deletionCode;
        List<Category> categories = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();

        System.out.print(ANSI_RED);
        System.out.println("  ********************************************************************* ");
        System.out.println("| Recorda que, abans d'executar qualsevol opció has de recarregar la BD |");
        System.out.println("| (executar l'SQL a ElephantSQL)                                        |");
        System.out.println("  ********************************************************************* ");
        System.out.print(ANSI_RESET + "\n");

        do {
            System.out.println("~~~~ GESTIÓ DE NÒMINES DELS TREBALLADORS ~~~~");
            System.out.println("\t0. Sortir");
            System.out.println("\t1. Visualitzar tots els treballadors de l'empresa, ordenats alfabèticament per nom (sense nòmines)");
            System.out.println("\t2. Introdueix la nòmina del mes actual d'un treballador");
            System.out.println("\t3. Veure tots els treballadors d'una determinada categoria, ordenats alfabèticament per nom (sense repeticions)");
            System.out.println("\t4. Modificar el sou base d'una categoria");
            System.out.println("\t5. Eliminar un treballador i totes les seves nòmines");
            System.out.print("Opció: ");
            option = keyboard.nextInt();

            switch(option) {
                case 0:
                    System.out.println("Tancant...");
                    break;
                case 1:
                    /******************************************************************/
                    /* TODO: completar per mostrar tots els treballadors de l'empresa */
                    /******************************************************************/

                    System.out.println(CompanyMain.employeesToString(CompanyController.getAllEmployees()));

                    break;
                case 2:
                    System.out.println("A quina categoria pertany el treballador?");
                    /**************************************************************************/
                    /* TODO: completar mostrar per pantalla totes les categories de l'empresa */
                    /* (només el nom amb un número al davant, començant per l'1)              */
                    /*                                                                        */
                    /* Aquestes categories s'han de guardar a la llista 'categories' que      */
                    /* s'ha declarat al capdamunt de la funció main                           */
                    /**************************************************************************/

                    categories = CompanyController.getCategories();
                    System.out.println(CompanyMain.categoriesToString(CompanyController.getCategories()));

                    System.out.print("Categoria (número): ");
                    cat = keyboard.nextInt();

                    if(cat<1 || cat>categories.size()) System.out.println("Categoria incorrecta");
                    else {
                        System.out.println("A quin treballador pertany la nòmina?");
                        /***************************************************************************/
                        /* TODO: completar mostrar per pantalla tots els treballadors de l'empresa */
                        /* (només el nom i el DNI, amb un número al davant, començant per l'1)     */
                        /*                                                                         */
                        /* Aquests treballadors s'han de guardar a la llista 'employees' que       */
                        /* s'ha declarat al capdamunt de la funció main                            */
                        /***************************************************************************/

                        employees = CompanyController.getAllEmployees();
                        System.out.println(CompanyMain.employeesToString(employees));
                        System.out.println();

                        System.out.print("Treballador (número): ");
                        worker = keyboard.nextInt();
                        if(worker<1 || worker>employees.size()) System.out.println("Treballador incorrecte");
                        else {
                            System.out.print("IRPF de la nòmina (entre 2 i 18): ");
                            irpf = keyboard.nextInt();
                            if(irpf<2 || irpf>18) System.out.println("El valor de l'IRPF no és correcte");
                            /************************************************************************/
                            /* TODO: completar per gestionar l'inserció de la nòmina del mes actual */
                            /* Cal avisar a l'usuari dels següents casos                            */
                            /*  1) La nòmina s'ha introduït correctament (en mostrarà les dades)    */
                            /*  2) La nòmina no s'ha pogut introduir                                */
                            /************************************************************************/


                            Payroll currentPayroll = new Payroll();
                            currentPayroll.setIrpf(irpf);
                            currentPayroll.setMonth(CompanyController.getCurrentMonth());
                            currentPayroll.setYear(CompanyController.getCurrentYear());


                            if (CompanyController.addPayroll(currentPayroll, worker, cat)){

                                System.out.println("La nòmina s'ha introduït correctament");
                                System.out.println(CompanyMain.payrollToString(currentPayroll, worker, cat));

                            }else {

                                System.out.println("La nòmina no s'ha pogut introduir.");

                            }

                        }
                    }

                    break;
                case 3:
                    System.out.println("Quina categoria vols consultar?");
                    /**************************************************************************/
                    /* TODO: completar mostrar per pantalla totes les categories de l'empresa */
                    /* (només el nom amb un número al davant, començant per l'1)              */
                    /*                                                                        */
                    /* Aquestes categories s'han de guardar a la llista 'categories' que      */
                    /* s'ha declarat al capdamunt de la funció main                           */
                    /**************************************************************************/



                    System.out.print("Categoria (número): ");
                    cat = keyboard.nextInt();
                    if(cat<1 || cat>categories.size()) System.out.println("Categoria incorrecta");
                    else {
                        /***************************************************************************/
                        /* TODO: completar mostrar per pantalla tots els treballadors de l'empresa */
                        /* que tinguin la categoria laboral indicada per l'usuari                  */
                        /* (només el nom i el DNI, amb un número al davant, començant per l'1)     */
                        /*                                                                         */
                        /* Aquests treballadors s'han de guardar a la llista 'employees' que       */
                        /* s'ha declarat al capdamunt de la funció main                            */
                        /***************************************************************************/
                    }
                    break;
                case 4:
                    System.out.println("Quina categoria vols modificar?");
                    /**************************************************************************/
                    /* TODO: completar mostrar per pantalla totes les categories de l'empresa */
                    /* (només el nom amb un número al davant, començant per l'1)              */
                    /*                                                                        */
                    /* Aquestes categories s'han de guardar a la llista 'categories' que      */
                    /* s'ha declarat al capdamunt de la funció main                           */
                    /**************************************************************************/
                    System.out.print("Categoria (número): ");
                    cat = keyboard.nextInt();
                    if(cat<1 || cat>categories.size()) System.out.println("Categoria incorrecta");
                    else {
                        System.out.print("Introdueix el nou sou base (>= 1166): ");
                        salary = keyboard.nextInt();
                        if(salary < 1166) System.out.println("L'SMI està establert a 1166€");
                        else {
                            /*************************************************************************************************/
                            /* TODO: completar per gestionar la modificació del salari base de la categoria indicada         */
                            /* Cal avisar a l'usuari dels següents casos                                                     */
                            /*  1) El sou base de la categoria s'ha pogut actualitzar correctament (mostrar les noves dades) */
                            /*  2) El sou base de la categoria no s'ha pogut actualitzar                                     */
                            /*************************************************************************************************/
                        }
                    }
                    break;
                case 5:
                    System.out.println("Quin treballador vols esborrar?");
                    /***************************************************************************/
                    /* TODO: completar mostrar per pantalla tots els treballadors de l'empresa */
                    /* (només el nom i el DNI, amb un número al davant, començant per l'1)     */
                    /*                                                                         */
                    /* Aquests treballadors s'han de guardar a la llista 'employees' que       */
                    /* s'ha declarat al capdamunt de la funció main                            */
                    /***************************************************************************/
                    System.out.print("Treballador (número): ");
                    worker = keyboard.nextInt();
                    if(worker<1 || worker>employees.size()) System.out.println("Treballador incorrecte");
                    else {
                        /*******************************************************************************************/
                        /* TODO: completar per gestionar l'eliminació del treballador i de totes les seves nòmines */
                        /* Cal avisar a l'usuari dels següents casos                                               */
                        /*  1) El treballador s'ha pogut esborrar completament                                     */
                        /*  2) Només s'han pogut esborrar les nòmines del treballador, però no les seves dades     */
                        /*  3) Només s'han pogut esborrar les dades del treballador, però no les seves nòmines     */
                        /*     (el treballador encara no tenia nòmines associades)                                 */
                        /*******************************************************************************************/
                    }
                    break;
                default:
                    System.out.println("Opció incorrecta");
            }
        } while(option != 0);
    }

    private static String employeesToString(ArrayList<Employee> employees){

        String output = "";

        for (Employee employee :
                employees) {
            output += employee.toString();
        }

        return output;
    }

}
