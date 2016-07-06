package it.carmelolagamba.esmarket.persistence.storageManagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.StoreDAO;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public class StoreDAOImpl extends GenericDaoImpl<Store> implements StoreDAO {

	public Store getByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Store object;
		try {
			object = (Store) session.createQuery("from Store where STORE_NAME = :name").setParameter("name", name).list().get(0);
			return object;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//session.flush();

		transaction.commit();
		session.close();
		return null;
	}

	public List<Store> getStoresContainingProuducts(List<String> productNames) {
		List<Store> allStores = getAll();
		List<Store> result = new ArrayList<Store>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (Store store : allStores) {
				Criteria criteria = session.createCriteria(Store.class).add(Restrictions.eq("ID", store.getID())).createAlias("products", "p")
						.setProjection(Projections.property("p.name"));
				List<String> pn = criteria.list();
				if (pn.containsAll(productNames))
					result.add(store);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//session.flush();
		transaction.commit();
		session.close();

		return result;

	}

	public Store getStoreByAccount(Long idAccount) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Store object;
		try {
			object = (Store) session.createQuery("select distinct s from Store s join s.account a where a.id = :idAccount")
					.setParameter("idAccount", idAccount).uniqueResult();
			return object;
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//session.flush();
		transaction.commit();
		session.close();
		return null;
	}

	public Store getLazyInitializedStore(Long storeID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Store store = null;
		try {
			transaction = session.beginTransaction();
			store = (Store) session.get(Store.class, storeID);
			Hibernate.initialize(store.getProducts());
			//session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return store;
	}

}
