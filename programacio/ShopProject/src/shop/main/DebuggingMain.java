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
		
		warehouseChicago.addProduct(product1, 4);
		warehouseChicago.addProduct(product2, 5);
		
		Warehouse warehouseLosAngeles = new Warehouse(warehouseChicago);
		Warehouse warehouseBoston = new Warehouse(warehouseChicago.getProducts(), warehouseChicago.getQuantities());
		
		warehouseChicago.addProduct(product1, 9);
		warehouseChicago.getProducts().get(0).setDescription("Poma blava");
		
		System.out.println(warehouseChicago + "" + warehouseLosAngeles + "" + warehouseBoston);
		
		

		
	}

}
