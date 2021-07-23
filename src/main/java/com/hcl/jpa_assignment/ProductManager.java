package com.hcl.jpa_assignment;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductManager {

	// ii) Retreive the list of products
	public List<Product> getAllProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Product> prods = session.createQuery("from Product").list();
		session.close();
		return prods;
	}

	// iii) Search by productID
	public Product getProductById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Product prod = (Product) session.get(Product.class, id);
		session.close();
		return prod;
	}

	// i) Insert the list of products
	public boolean insertProducts(List<Product> products) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean succeeded = true;
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			int i = 0;
			for (Product p : products) {
				session.save(p);
				i++;
				if (i % 10 == 0) { // batch size in hibernate.cfg.xml
					session.flush();
					session.clear();
				}
			}
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			e.printStackTrace();
			succeeded = false;
		} finally {
			session.close();
		}

		return succeeded;

	}

	// iv) Update the existing product by product ID
	public boolean updateProduct(Product p, long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean succeeded = true;
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			session.save(p);
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			e.printStackTrace();
			succeeded = false;
		} finally {
			session.close();
		}
		return succeeded;

	}

	// v) Delete the product by ID
	public boolean deleteProductById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean succeeded = true;
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Query deleteQuery = session.createQuery("delete from Product where product_id = :id");
			deleteQuery.setParameter("id", id);
			int rows = deleteQuery.executeUpdate();
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			e.printStackTrace();
			succeeded = false;
		} finally {
			session.close();
		}
		return succeeded;

	}

}
