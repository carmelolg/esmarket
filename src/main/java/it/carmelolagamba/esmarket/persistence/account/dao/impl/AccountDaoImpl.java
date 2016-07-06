package it.carmelolagamba.esmarket.persistence.account.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Administrator;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.account.dao.AccountDAO;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDAO {

	@Override
	public void remove(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Order o where o.orderOwner.id=:ID");
		query.setParameter("ID", id);
		List<Order> orders = query.list();
		for (Order order : orders) {
			order.setOrderOwner(null);
			session.update(order);
		}

		query = session.createQuery("from Order o where o.orderManager.id=:ID");
		query.setParameter("ID", id);
		orders = query.list();
		for (Order order : orders) {
			order.setOrderManager(null);
			session.update(order);
		}
		Account account = (Account) session.get(Account.class, id);
		session.delete(account);
		transaction.commit();
		session.close();
	}

	public boolean findMail(String mail) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Account.class).add(Restrictions.eq("eMail", mail));
		return findByCriteria(criteria).size() > 0;
	}

	public Account findAccountByAuthenticationINFO(String mail, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Account.class).add(Restrictions.eq("eMail", mail))
				.add(Restrictions.eq("password", password));
		List<Account> accounts = findByCriteria(criteria);
		if (accounts.size() > 0)
			return findByCriteria(criteria).get(0);
		else
			return null;
	}

	public List<Account> getManagers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Account where type_of_user = :manager").setParameter("manager", "Manager");
		List<Account> accounts = query.list();

		transaction.commit();
		session.close();

		return accounts;
	}

	public Account getAdiministrator() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Administrator.class);
		return findByCriteria(criteria).get(0);
	}

}
