package it.carmelolagamba.esmarket.persistence.storageManagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.util.HibernateUtil;

public class ProductDAOImpl extends GenericDaoImpl<Product> implements ProductDAO {

	public List<Product> findProductsByCategory(String category) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class).add(Restrictions.eq("category", category));
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<Product> findProductsByPrice(int minPrice, int maxPrice) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class).add(Restrictions.between("unitPrice", minPrice, maxPrice));
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<Product> findProductsByBrand(String brand) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class).add(Restrictions.eq("brand", brand));
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<String> getAllBrand() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("select p.brand from Product p");
		List<String> brands = query.list();
		//session.flush();

		transaction.commit();
		session.close();
		return brands;
	}

	public List<Product> findProductsByDiscount(float discount) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class).createCriteria("promotion").add(Restrictions.eq("discount", discount));
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<Product> findAllProductsForSale() {
		//subquery to remove products with duplicate name and take the one with the greater ID
		DetachedCriteria subquery = DetachedCriteria.forClass(Product.class, "p1").add(Property.forName("p.name").eqProperty("p1.name")).setProjection(Projections.max("ID"));

		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class, "p").add(Restrictions.eq("forSale", true))
				.addOrder(Order.desc("productDate")).add(Subqueries.propertyIn("p.ID", subquery));

		
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<Product> findPFSByExample(Product example, Float minPrice, Float maxPrice, OrderBy orderByField, boolean asc) {

		//subquery to remove products with duplicate name and take the one with the greater ID
		DetachedCriteria subquery = DetachedCriteria.forClass(Product.class, "p1").add(Property.forName("p.name").eqProperty("p1.name")).setProjection(Projections.max("ID"));
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class,"p").add(Restrictions.eq("forSale", true));
		if (example.getBrand() != null)
			criteria.add(Restrictions.eq("brand", example.getBrand()));
		if (example.getCategory() != null)
			criteria.add(Restrictions.eq("category", example.getCategory()));
		if (example.getName() != null)
			criteria.add(Restrictions.like("name", example.getName(), MatchMode.ANYWHERE));
		if (example.getProductDate() != null)
			criteria.add(Restrictions.eq("productDate", example.getProductDate()));
		if (minPrice != null || maxPrice != null)
			criteria.add(Restrictions.between("unitPrice", minPrice, maxPrice));
		if (asc)
			criteria.addOrder(Order.asc(orderByField.name()));
		else
			criteria.addOrder(Order.desc(orderByField.name()));
		
		criteria.add(Subqueries.propertyIn("p.ID", subquery));
		
		List<Product> products = findByCriteria(criteria);
		return products;
	}

	public List<Product> getByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<Product> object;
		try {
			object = (List<Product>) session.createQuery("from Product where PRODUCT_NAME = :name").setParameter("name", name).list();
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

}
