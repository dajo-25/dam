package shop.models;

public class Product {

	private String barCode;
	private String description;
	private float price;
	
	//DEFAULT CONSTRUCTOR
	public Product() {
		
		this.barCode = new String();
		this.description = new String();
		this.price = 0;
		
	}
	
	//COPY CONSTRUCTOR
	public Product(Product otherProduct) {
		
		this.barCode = otherProduct.barCode;
		this.description = otherProduct.description;
		this.price = otherProduct.price;
		
	}
	
	//PARAMETERS CONSTRUCTOR
	public Product(String barCode, String description, float price) {
		
		this.barCode = barCode;
		this.description = description;
		this.price = price;
		
	}

	
	//GETTERS AND SETTERS
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	//EQUALS
	public boolean equals(Product otherProduct) {
		
		boolean output = false;
		
		if(this.description.equals(otherProduct.description) &&
				this.barCode.equals(otherProduct.barCode) &&
				(this.price == otherProduct.price)) {
			
			output = true;
			
		}
		
		return output;
	}

	
	//TO STRING
	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", description=" + description + ", price=" + price + "]";
	}
	
	
	
}
