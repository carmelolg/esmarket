package it.carmelolagamba.esmarket.persistence;

import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.OrderDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.PromotionDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.ReviewDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.SaleItemDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;

public abstract class DAOFactory {

	public static Class HIBERNATE = HibernateDAOFactory.class;

	public static DAOFactory instance(Class factory) {
		try {
			return (DAOFactory) factory.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create DAOFactory: " + factory);
		}
	}

	public abstract AccountDAO getAccountDAO();

	public abstract OrderDAO getOrderDAO();

	public abstract ReviewDAO getReviewDAO();

	public abstract SaleItemDAO getSaleItemDAO();

	public abstract ProductDAO getProductDAO();

	public abstract StoreDAO getStoreDAO();

	public abstract PromotionDAO getPromotionDAO();
}
