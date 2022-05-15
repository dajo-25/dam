package library.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import library.models.Book;

public class LibraryCatalog {

	private HashMap<String, TreeSet<Book>> catalog = new HashMap<>();
	
	public LibraryCatalog() {
		
		this.catalog = new HashMap<>();
		
	}
	
	public boolean loadFile(String path) {
		
		boolean check;
		
		try {
			
			File catalogFile = new File(path);
			
			FileReader fileReader = new FileReader(catalogFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String currentLine = bufferedReader.readLine();

			while (currentLine != null) {
				String [] bookInfo = currentLine.split(";");
				
				Book currentBook = new Book(bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3], bookInfo[4], bookInfo[5], bookInfo[7], Integer.parseInt(bookInfo[6]));

				this.addBook(currentBook);
				
				currentLine = bufferedReader.readLine();
			}
			
			bufferedReader.close();
			fileReader.close();
			
			check = true;
			
		} catch (Exception e) {
			
			check = false;
			
		}
		
		return check;
	}

	public boolean catalogToCSV(String path) {
		
		File outputFile = new File(path);
		
		if (path.lastIndexOf(File.separator) != -1) {
			File outputDirectory = new File(path.substring(0, path.lastIndexOf(File.separator)));
			if (!outputDirectory.exists()) {
				
				outputDirectory.mkdirs();
				
			}
		}
		
		try {
			
			FileWriter fileWriter = new FileWriter(outputFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (Entry<String, TreeSet<Book>> entries : catalog.entrySet()) {
				
				Iterator<Book> it = entries.getValue().iterator();
				
				while (it.hasNext()) {
					Book book = (Book) it.next();
					
					String currentLine = "" + book.getTitle() +";"+ book.getIsbn() +";"+ book.getAuthor() +";"+ book.getCategory() +";"+ book.getPublisher() +";"+ book.getDescription() +";"+ book.getnPages() +";"+ book.getImageLink();
					bufferedWriter.write(currentLine + "\n");
				}
				
			}
			
			bufferedWriter.close();
			fileWriter.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
			
	}
	
	public boolean addBook(Book currentBook) {
		
		
		if (catalog.containsKey(currentBook.getCategory())) {
			
			catalog.get(currentBook.getCategory()).add(currentBook);
			
		}else {
			
			catalog.put(currentBook.getCategory(), new TreeSet<Book>());
			if (catalog.get(currentBook.getCategory()).contains(currentBook)) {
				return false;
			}else {
				catalog.get(currentBook.getCategory()).add(currentBook);
				
			}
			
			
		}
		
		return true;
	}
	
	public Book findByISBN(String isbnQuery) {
				
		for (Entry<String, TreeSet<Book>> entries : catalog.entrySet()) {
			
			Iterator<Book> it = entries.getValue().iterator();
			
			while (it.hasNext()) {
				Book book = (Book) it.next();
				
				if (book.getIsbn().equals(isbnQuery)) {
					return book;
				}
			}
			
		}
		
		return null;
	}
	
	public Book findByTitle(String titleQuery) {
		
		for (Entry<String, TreeSet<Book>> entries : catalog.entrySet()) {
			
			Iterator<Book> it = entries.getValue().iterator();
			
			while (it.hasNext()) {
				Book book = (Book) it.next();
				
				if (book.getTitle().equals(titleQuery)) {
					return book;
				}
			}
			
		}
		
		return null;
	}
	
	public TreeSet<String> getCathegories(){
		
		if (this.catalog.isEmpty()) {
			return new TreeSet<>();
			
		}else {
			
			return new TreeSet<>(this.catalog.keySet());
			
		}
		
	}
	
	public TreeSet<String> getAuthors(){
		
		if (this.catalog.isEmpty()) {
			return new TreeSet<>();
			
		}else {
			
			TreeSet<String> authors = new TreeSet<>();
			
			for (Entry<String, TreeSet<Book>> entries : catalog.entrySet()) {
				
				Iterator<Book> it = entries.getValue().iterator();
				
				while (it.hasNext()) {
					Book book = (Book) it.next();
					
					authors.add(book.getAuthor());
					
				}
				
			}
			
			return authors;
		}
		
	}
	
	public ArrayList<Book> getBooksByCathegory(String cathegory){
		
		if (!this.catalog.containsKey(cathegory)) {
			return new ArrayList<>();
		}else {
			
			return new ArrayList<Book>(catalog.get(cathegory));
			
		}
		
	}
	
	public ArrayList<Book> getBooksByAuthor(String author){
		
		ArrayList<Book> booksByAuthor = new ArrayList<>();
		
		for (Entry<String, TreeSet<Book>> entries : catalog.entrySet()) {
			
			Iterator<Book> it = entries.getValue().iterator();
			
			while (it.hasNext()) {
				Book book = (Book) it.next();
				
				if (book.getAuthor().equals(author)) {
					
					booksByAuthor.add(book);
					
				}
				
			}
			
		}
		
		return booksByAuthor;
		
	}
	
	@Override
	public String toString() {
		
		String str = "----------------------------------------" + 
				"\nCATALOG ID: " + this.hashCode() + "\n";
		
		for (String cathegory : this.catalog.keySet()) {
			
			str += "\n  Cathegory \"" + cathegory + "\"\n";
			
			for (Book book : this.catalog.get(cathegory)) {
				
				str += "    · " + book.toString();
				
			}
			str += "\n";
		}
		
		str += "\n----------------------------------------";
		
		return str;
	}
	
}
