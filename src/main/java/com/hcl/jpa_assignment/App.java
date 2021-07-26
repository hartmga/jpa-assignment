package com.hcl.jpa_assignment;

import java.util.LinkedList;
import java.util.List;

/**
 * JPA Assignment:
 * 
 * Create a standalone maven project and add the jpa dependencies and do the
 * following:
 * 
 * 1. Using JPA Annotaton, make the Java objects as perssistence and do the crud
 * operation by using MySQL Database.
 * 
 * Here are the requirements: i) Insert the list of products ii) Retreive the
 * list of products iii) Search by productID iv) Update the existing product by
 * product ID v) Delete the product by ID
 * 
 */
public class App {
	public static void main(String[] args) {

		ProductManager manager = new ProductManager();

		// i) Insert the list of products
		List<Product> products = new LinkedList<>();
		for (int i = 0; i < 1000; i++) {
			Product next = new Product("P" + i + 1, Math.random() * 100);
			products.add(next);
		}
		manager.insertProducts(products);

		// ii) Retreive the list of products
		List<Product> allProducts = manager.getAllProducts();
		System.out.printf("Retrieved %d products%n", allProducts.size());
		for (Product p : allProducts.subList(0, Math.min(10, allProducts.size()))) {
			System.out.println(p);
		}
		System.out.println("...");
		System.out.println();

		// iii) Search by productID
		Product p500 = manager.getProductById(500l);
		System.out.println("The 500th product is " + p500);
		System.out.println();

		// iv) Update the existing product by product ID
		p500.setPrice(p500.getPrice() * 2);
		manager.updateProduct(p500, p500.getProduct_id());
		p500 = manager.getProductById(500l);
		System.out.println("The 500th product's price has been doubled: " + p500);
		System.out.println();

		// v) Delete the product by ID
		manager.deleteProductById(5l);
		System.out.println("Deleted 5th product");
		for (Product p : manager.getAllProducts().subList(0, 10)) { // it would be better to make a method to retreive
																	// only a sublist
			System.out.println(p);
		}
		System.out.println("...");

		HibernateUtil.getSessionFactory().close(); // close the sessionFactory so that program terminates
	}
}
