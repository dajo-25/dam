package library.main;

import java.util.Scanner;

import library.controller.LibraryCatalog;
import library.models.Book;

public class LibraryMain {

	public static void main(String[] args) {
		
		LibraryCatalog libraryCatalog = new LibraryCatalog();
		
		libraryCatalog.loadFile("library.csv");
		
		Scanner scan = new Scanner(System.in);
		int option = -1;
		
		while (option != 0) {
			
			System.out.println("\n~~~~ MENÚ DE LA BIBLIOTECA ~~~~\r\n"
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
				
				System.out.println("Introdueixi les dades del nou llibre:" 
						+ "\nTítol:");
				
				String title = scan.nextLine();
				
				System.out.println("ISBN:");
				String isbn = scan.nextLine();
				
				System.out.println("Autor:");
				String author = scan.nextLine();
				
				System.out.println("Categoria:");
				String cathegory = scan.nextLine();
				
				System.out.println("Editorial:");
				String publisher = scan.nextLine();
				
				System.out.println("Descripció:");
				String description = scan.nextLine();
				
				System.out.println("Enllaç imatge:");
				String imageLink = scan.nextLine();
				
				System.out.println("Nombre de pàgines:");
				int nPages = Integer.parseInt(scan.nextLine());
				
				Book newBook = new Book(title, isbn, author, cathegory, publisher, description, imageLink, nPages);
				
				libraryCatalog.addBook(newBook);
				
				break;
				
			case 2:
				
				System.out.println("Introdueixi l'ISBN:\n");
				
				String isbnQuery = scan.nextLine();
				
				Book bookQuery = libraryCatalog.findByISBN(isbnQuery);
				
				if (bookQuery != null) {
					
					System.out.println(bookQuery.toString());
					
				}else {
					
					System.out.println("No s'ha trobat.");
					
				}
				
				break;
				
			case 3:
				
				System.out.println("Introdueixi el títol:\n");
				
				String titleQuery = scan.nextLine();
				
				Book queryBook = libraryCatalog.findByTitle(titleQuery);
				
				if (queryBook != null) {
					
					System.out.println(queryBook.toString());
					
				}else {
					
					System.out.println("No s'ha trobat.");
					
				}
				
				break;
				
			case 4:
				
				System.out.println("Quina categoria vol consultar?");
				
				for (String string : libraryCatalog.getCathegories()) {
					
					System.out.println("  · " + string);
					
				}
				
				String cathegoryQuery = scan.nextLine();
				
				System.out.println("Els llibres de la categoria " + cathegoryQuery + " són:");
				
				for (Book book : libraryCatalog.getBooksByCathegory(cathegoryQuery)) {
					
					System.out.println("  · " + book.toString());
					
				}

				
				break;
				
			case 5:
				
				System.out.println("Quina autor vol consultar?");
				
				for (String string : libraryCatalog.getAuthors()) {
					
					System.out.println("  · " + string);
					
				}
				
				String authorQuery = scan.nextLine();
				
				System.out.println("Els llibres de " + authorQuery + " són:");
				
				for (Book book : libraryCatalog.getBooksByAuthor(authorQuery)) {
					
					System.out.println("  · " + book.toString());
					
				}
				
				break;
				
			case 6:
				
				System.out.println(libraryCatalog.toString());
				
				break;
			
			case 7:
				
				System.out.println("On vol guardar el catàleg? (ruta\\nomfitxer.csv)");
				
				String path = scan.nextLine();
				
				libraryCatalog.catalogToCSV(path);
				
				break;
			default:
				
				System.out.println("Opció invàlida!");
				
				break;
			}
			
		}
		
		
		scan.close();
	}

}
