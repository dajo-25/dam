package library.models;

public class Book implements Comparable<Book>{

	private String title, isbn, author, category, publisher, description, imageLink;
	private int nPages;
	
	public Book() {
		this.title = "";
		this.isbn = "";
		this.author = "";
		this.category = "";
		this.publisher = "";
		this.description = "";
		this.imageLink = "";
		this.nPages = 0;
	}
	
	public Book(String title, String isbn, String author, String category, String publisher, String description,
			String imageLink, int nPages) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
		this.description = description;
		this.imageLink = imageLink;
		this.nPages = nPages;
	}

	public Book(Book otherBook) {
		this.title = otherBook.title;
		this.isbn = otherBook.isbn;
		this.author = otherBook.author;
		this.category = otherBook.category;
		this.publisher = otherBook.publisher;
		this.description = otherBook.description;
		this.imageLink = otherBook.imageLink;
		this.nPages = otherBook.nPages;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getnPages() {
		return nPages;
	}

	public void setnPages(int nPages) {
		this.nPages = nPages;
	}

	
	@Override
	public int hashCode() {
		return this.isbn.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		Book other = (Book) obj;
		
		if (other.isbn.equals(this.isbn)) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int compareTo(Book o) {

		if (this.equals(o)) {
			
			return 0;
			
		}else {
			
			if(this.category.compareTo(o.category) != 0) {
				
				return this.category.compareTo(o.category);
			}
			
			if(this.author.compareTo(o.author) != 0) {
				
				return this.author.compareTo(o.author);
			}
			
			if(this.title.compareTo(o.title) != 0) {
				
				return this.title.compareTo(o.title);
			}
				
			return this.publisher.compareTo(o.publisher);
			
		}
		
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", isbn=" + isbn + ", author=" + author + ", category=" + category
				+ ", publisher=" + publisher + ", description=" + description + ", imageLink=" + imageLink + ", nPages="
				+ nPages + "]";
	}
	
	
	
}
