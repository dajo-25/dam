package library.debugging;

import java.io.File;

import library.controller.LibraryCatalog;
import library.models.Book;

public class DebuggingMain {

	public static void main(String[] args) {
		
		LibraryCatalog catalog = new LibraryCatalog();
		
		if (catalog.loadFile("library.csv")) {
			
			System.out.println("true");
			
		}
		catalog.addBook(new Book("AA", "AA", "AA", "AA", "AA", "AA", "AA", 0));

		catalog.catalogToCSV("catalegtesting.csv");
		
	}

}
