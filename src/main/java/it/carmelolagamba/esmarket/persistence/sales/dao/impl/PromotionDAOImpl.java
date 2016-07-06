package it.carmelolagamba.esmarket.persistence.sales.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.PromotionDAO;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public class PromotionDAOImpl extends GenericDaoImpl<Promotion> implements PromotionDAO {

	@Override
	public void remove(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Product p where p.promotion.ID=:ID");
		query.setParameter("ID", id);
		List<Product> products = query.list();
		for (Product product : products) {
			product.setPromotion(null);
			session.update(product);
		}
		Promotion promotion = (Promotion) session.get(Promotion.class, id);
		session.delete(promotion);
		//session.flush();

		transaction.commit();
		session.close();
	}

}
