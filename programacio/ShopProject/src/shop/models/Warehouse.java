package shop.models;

import java.util.ArrayList;

public class Warehouse {

	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();
	
	
	//CONSTRUCTORS
	public Warehouse() {
		
		this.products.clear();
		this.quantities.clear();
		
	}
	
	public Warehouse(Warehouse otherWarehouse) {
		
		this.products.clear();
		
		for (int i = 0; i < otherWarehouse.products.size(); i++) {
									
			this.products.add(new Product(otherWarehouse.products.get(i)));
			
		}
		
		this.quantities.clear();
		
		for (int i = 0; i < otherWarehouse.quantities.size(); i++) {
			
			this.quantities.add(otherWarehouse.quantities.get(i));
			
		}
		
	}

	public Warehouse(ArrayList<Product> products, ArrayList<Integer> quantities) {
		
		for (int i = 0; i < products.size(); i++) {
			this.products.add(new Product(products.get(i)));
		}
		
		this.quantities = new ArrayList<>(quantities);
		
	}

	
	////GETTERS AND SETTERS
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(ArrayList<Integer> quantities) {
		this.quantities = quantities;
	}

	
	//EQUALS AND TOSTRING
	public boolean equals(Warehouse otherWarehouse) {
		
		boolean output = false;
		
		if (this.products.equals(otherWarehouse.products) &&
				this.quantities.equals(otherWarehouse.quantities)) {
			
			output = true;
			
		}
		
		return output;
	}

	@Override
	public String toString() {
		return "\nWarehouse \n[products=" + products + ", \nquantities=" + quantities + "]";
	}
	
	
	//METHODS
	public boolean addProduct(Product newProduct) {
		
		boolean output = true;
		
		for (int i = 0; i < this.products.size(); i++) {
			
			if (this.products.get(i).equals(newProduct)) {
				
				output = false;
				
			}
			
		}
		
		if (output) {
			
			this.products.add(newProduct);
			this.quantities.add(0);
			
		}
		
		return output;
	}
	
	public void addProduct(Product newProduct, int quantity) {
		
		boolean productExists = false;
		
		for (int i = 0; i < this.products.size(); i++) {
			
			if (this.products.get(i).equals(newProduct)) {
				
				this.quantities.set(i, this.quantities.get(i) + quantity);
				productExists = true;
				
			}
			
		}
		
		if (!productExists) {
			
			this.products.add(newProduct);
			this.quantities.add(quantity);
			
		}
		
	}
	
	public boolean deleteProduct(String barcode) {
		
		boolean output = false;
		
		for (int i = 0; i < this.products.size(); i++) {
			
			if (this.products.get(i).getBarCode().equals(barcode)) {
				
				this.products.remove(i);
				this.quantities.remove(i);
				
				output = true;
			}
			
		}
		
		return output;
	}

	public boolean reduceProduct(String barcode, int quantity) {
		
		boolean output = false;
		
		for (int i = 0; i < this.products.size(); i++) {
			
			if (this.products.get(i).getBarCode().equals(barcode) &&
					this.quantities.get(i) >= quantity) {
				
				this.quantities.set(i, this.quantities.get(i) - quantity);
				
				output = true;
			}
			
		}
		
		return output;
	}

	public ArrayList<Product> getProductsForReestock() {
		
		ArrayList<Product> productsForReestock = new ArrayList<>();
		
		for (int i = 0; i < this.quantities.size(); i++) {
			
			if (this.quantities.get(i) <= 5) {
				
				productsForReestock.add(new Product(this.products.get(i)));
				
			}
			
		}
		
		return productsForReestock;
	}

	public ArrayList<Product> getAvailableProducts() {
		
		ArrayList<Product> productsAvailable = new ArrayList<>();
		
		for (int i = 0; i < this.quantities.size(); i++) {
			
			if (this.quantities.get(i) >= 1) {
				
				productsAvailable.add(new Product(this.products.get(i)));
				
			}
			
		}
		
		return productsAvailable;
		
	}
		
	public Product getProduct(String barcode) {
		
		Product targetProduct = null;
		
		for (int i = 0; i < this.products.size(); i++) {
			
			if (this.products.get(i).getBarCode().equals(barcode)) {
				
				targetProduct = new Product(this.products.get(i));
				
				
			}
			
		}
		
		return targetProduct;
	}

	
}
