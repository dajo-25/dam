package shop.models;

import java.util.ArrayList;

public class Warehouse {

	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Integer> quantities = new ArrayList<Integer>();
	
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
	
}
