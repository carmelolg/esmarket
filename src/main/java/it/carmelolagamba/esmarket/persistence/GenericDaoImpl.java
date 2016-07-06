package it.carmelolagamba.esmarket.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T save(T object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(object);
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		return object;
	}

	public void update(T object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(object);
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void remove(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			T object = (T) session.get(type, id);
			session.delete(object);
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<T> allObjects = null;
		try {
			transaction = session.beginTransaction();
			allObjects = session.createQuery("from " + type.getName()).list();
			for (T t : allObjects)
				Hibernate.initialize(t);
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		return allObjects;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		T object = null;
		try {
			transaction = session.beginTransaction();
			object = (T) session.get(type, id);
			Hibernate.initialize(object);
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<T> objects = null;
		try {
			transaction = session.beginTransaction();
			objects = criteria.getExecutableCriteria(session).list();
//			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return objects;
	}

}
