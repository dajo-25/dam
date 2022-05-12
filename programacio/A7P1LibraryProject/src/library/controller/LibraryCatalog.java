package library.controller;

import java.util.HashMap;
import java.util.TreeSet;

import library.models.Book;

public class LibraryCatalog {

	private HashMap<String, TreeSet<Book>> catalog = new HashMap<>();
	
	
	public LibraryCatalog() {
		
		this.catalog = new HashMap<>();
		
	}
	
}
