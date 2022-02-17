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
			
			Product tmpProduct = otherWarehouse.products.get(i);
			
			this.products.add(new Product(tmpProduct.getBarCode(), tmpProduct.getDescription(), tmpProduct.getPrice()));
			this.products.add(new Product(tmpProduct));
			
		}
		
		this.quantities.clear();
		
		for (int i = 0; i < otherWarehouse.quantities.size(); i++) {
			
			this.quantities.add(otherWarehouse.quantities.get(i));
			
		}
		
	}

	public Warehouse(ArrayList<Product> products, ArrayList<Integer> quantities) {
		
		this.products = new ArrayList<>(products);
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
	
}
