package com.hcl.jpa_assignment;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.password", System.getenv("mysqlpwd"));
			sessionFactory = cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Error initializing sessionFactory");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
