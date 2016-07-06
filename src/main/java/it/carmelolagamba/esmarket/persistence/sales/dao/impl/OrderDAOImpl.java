package it.carmelolagamba.esmarket.persistence.sales.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.Order.OrderStatus;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.OrderDAO;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public class OrderDAOImpl extends GenericDaoImpl<Order> implements OrderDAO {

	public List<Order> getOrdersByAccount(Long idAccount) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Order where ID_ACCOUNT= :idAccount");
			query.setParameter("idAccount", idAccount);
			orders = query.list();
			session.flush();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return orders;
	}

	public List<Order> getOrdersByDate(Date startDate, Date endDate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Order where ORDER_DATE <= :endDate && ORDER_DATE >= :startDate");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			orders = query.list();
			session.flush();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return orders;
	}

	public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Order where ORDER_STATUS = :orderStatus");
			query.setParameter("orderStatus", orderStatus);
			orders = query.list();
			session.flush();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return orders;
	}

	public boolean validAndSaveOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			// get store associated to the order
			Criteria criteria = session.createCriteria(Store.class).add(Restrictions.eq("account.id", order.getOrderManager().getID()));
			Store store = (Store) criteria.uniqueResult();

			// check products availability
			for (SaleItem item : order.getItems()) {
				Product tmp = store.getStoreProduct(item.getProduct().getID());
				if (tmp.getQuantity() < item.getQuantity()) {
					transaction.rollback();
					return false;
				}
				tmp.setQuantity(tmp.getQuantity() - item.getQuantity());
				session.update(tmp);
				session.save(item);
			}

			session.update(store);
			session.save(order);
//			session.flush();

			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return true;
	}

	public List<Order> getOrdersByManager(Long idAccount) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("select distinct o from Order o join o.orderManager m where m.id = :idAccount");

			query.setParameter("idAccount", idAccount);
			orders = query.list();
			session.flush();

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return orders;
	}

	public Order getLazyInitializedOrder(Long orderID) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Order order = null;
		try {
			transaction = session.beginTransaction();
			order = (Order) session.get(Order.class, orderID);
			Hibernate.initialize(order.getItems());
			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return order;
	}

	public boolean checkProductOrder(Long userID, Long productID) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class).add(Restrictions.eq("orderOwner.id", userID))
				.add(Restrictions.eq("status", OrderStatus.COMPLETED))
				.createAlias("items", "item")
				.add(Restrictions.eq("item.ID", productID));

		if (findByCriteria(criteria).size() > 0)
			return true;

		return false;
	}

}
