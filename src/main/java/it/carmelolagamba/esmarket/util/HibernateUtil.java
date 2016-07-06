package it.carmelolagamba.esmarket.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Administrator;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.account.ManagerShop;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure("resource/hibernate.cfg.xml").addPackage("it.carmelolagamba.esmarket.domain.account")
					.addPackage("it.carmelolagamba.esmarket.domain.sales").addPackage("it.carmelolagamba.esmarket.domain.storageManagement")
					.addPackage("it.carmelolagamba.esmarket.service").addAnnotatedClass(Account.class).addAnnotatedClass(Client.class)
					.addAnnotatedClass(ManagerShop.class).addAnnotatedClass(Order.class).addAnnotatedClass(Product.class)
					.addAnnotatedClass(Store.class).addAnnotatedClass(Promotion.class).addAnnotatedClass(SaleItem.class)
					.addAnnotatedClass(Administrator.class).addAnnotatedClass(Review.class).buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
