package shop.main;

import shop.models.Product;
import shop.models.Warehouse;

public class DebuggingMain {

	public static void main(String[] args) {
		
		Product product1 = new Product("34109", "Poma verda", 3.95f);
		Product product2 = new Product("34110", "Poma vermella", 2.45f);

		System.out.println(product1.toString());
		System.out.println(product2.toString());
		
		Warehouse warehouseChicago = new Warehouse();
		
		warehouseChicago.addProduct(product1);
		
		System.out.println(warehouseChicago.toString());
		
		warehouseChicago.addProduct(product2);
		
		System.out.println(warehouseChicago.toString());

		warehouseChicago.addProduct(product2);
		warehouseChicago.addProduct(product2, 4);
		
		System.out.println(warehouseChicago.toString());

		warehouseChicago.addProduct(product2, 2);
		
		warehouseChicago.addProduct(new Product("34111", "Poma blava", 10.30f), 10);
		
		System.out.println(warehouseChicago.toString());

		
	}

}
