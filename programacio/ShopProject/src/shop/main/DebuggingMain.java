package shop.main;

import shop.models.Product;
import shop.models.Warehouse;

public class DebuggingMain {

	public static void main(String[] args) {
		
		Product product1 = new Product("3asdf", "Poma verda", 3.95f);
		Product product2 = new Product(product1);
		product1.setDescription("Poma vermella");

		System.out.println(product1.toString());
		System.out.println(product2.toString());
		
		Warehouse warehouseChicago = new Warehouse();
		
		
		
		
	}

}