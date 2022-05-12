package library.main;

import java.util.Scanner;

public class LibraryMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int option = -1;
		
		while (option != 0) {
			
			System.out.println("~~~~ MENÚ DE LA BIBLIOTECA ~~~~\r\n"
					+ "0. Sortir\r\n"
					+ "1. Afegir un nou llibre\r\n"
					+ "2. Buscar un llibre per ISBN\r\n"
					+ "3. Buscar un llibre per títol\r\n"
					+ "4. Llistar els llibres per categoria\r\n"
					+ "5. Llistar els llibres per autor\r\n"
					+ "6. Mostrar tot el catàleg\r\n"
					+ "7. Guardar el nou catàleg");
			option = Integer.parseInt(scan.nextLine());
			
			switch (option) {
			
			case 0:
				
				System.out.println("A reveure!");
				
				break;

			case 1:
				
				
				break;
				
			case 2:
				
				
				break;
				
			case 3:
				
				
				break;
				
			case 4:
				
				
				break;
				
			case 5:
				
				
				break;
				
			case 6:
				
				
				break;
			
			case 7:
				
				
				break;
			default:
				
				System.out.println("Opció invàlida!");
				
				break;
			}
			
		}
		
		
		scan.close();
	}

}
