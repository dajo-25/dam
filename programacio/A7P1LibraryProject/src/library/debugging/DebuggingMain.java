package library.debugging;

import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map.Entry;


import library.controller.LibraryCatalog;
import library.models.Book;

public class DebuggingMain {

	public static void main(String[] args) {
		
		LibraryCatalog catalog = new LibraryCatalog();
		
		catalog.addBook(new Book("AA", "AA", "AA", "AA", "AA", "AA", "AA", 0));
		
		if (catalog.loadFile("library.csv")) {
			
			System.out.println("true");
			
		}
		
		System.out.println(catalog.toString());
	}

}
