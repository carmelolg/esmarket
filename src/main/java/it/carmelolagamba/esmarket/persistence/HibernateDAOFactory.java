package it.carmelolagamba.esmarket.persistence;

import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.persistence.account.dao.impl.AccountDaoImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.OrderDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.PromotionDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.ReviewDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.SaleItemDAO;
import it.carmelolagamba.esmarket.persistence.sales.dao.impl.OrderDAOImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.impl.PromotionDAOImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.impl.ReviewDAOImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.impl.SaleItemDAOImpl;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.impl.ProductDAOImpl;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.impl.StoreDAOImpl;

public class HibernateDAOFactory extends DAOFactory {

	private GenericDAO instantiateDAO(Class daoClass) {
		try {
			GenericDAO dao = (GenericDAO) daoClass.newInstance();
			return dao;
		} catch (Exception e) {
			throw new RuntimeException("Cannot create dao:", e);
		}
	}

	@Override
	public AccountDAO getAccountDAO() {
		return (AccountDaoImpl) instantiateDAO(AccountDaoImpl.class);
	}

	@Override
	public OrderDAO getOrderDAO() {
		return (OrderDAOImpl) instantiateDAO(OrderDAOImpl.class);
	}

	@Override
	public ProductDAO getProductDAO() {
		return (ProductDAOImpl) instantiateDAO(ProductDAOImpl.class);
	}

	@Override
	public ReviewDAO getReviewDAO() {
		return (ReviewDAOImpl) instantiateDAO(ReviewDAOImpl.class);
	}

	@Override
	public SaleItemDAO getSaleItemDAO() {
		return (SaleItemDAOImpl) instantiateDAO(SaleItemDAOImpl.class);
	}

	@Override
	public StoreDAO getStoreDAO() {
		return (StoreDAOImpl) instantiateDAO(StoreDAOImpl.class);
	}

	@Override
	public PromotionDAO getPromotionDAO() {
		return (PromotionDAOImpl) instantiateDAO(PromotionDAOImpl.class);
	}
}
