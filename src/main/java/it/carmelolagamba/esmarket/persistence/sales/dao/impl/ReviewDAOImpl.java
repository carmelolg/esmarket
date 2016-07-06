package it.carmelolagamba.esmarket.persistence.sales.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.persistence.GenericDaoImpl;
import it.carmelolagamba.esmarket.persistence.sales.dao.ReviewDAO;

public class ReviewDAOImpl extends GenericDaoImpl<Review> implements ReviewDAO {

	public List<Review> getReviewByProductID(Long productID) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Review.class).add(Restrictions.eq("reviewedProduct.ID", productID));
		return findByCriteria(criteria);
	}

	public Review getReviewByProductIDAndUserID(Long productID, Long userID) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Review.class).add(Restrictions.eq("reviewedProduct.ID", productID))
				.add(Restrictions.eq("author.id", userID));

		List<Review> reviews = findByCriteria(criteria);

		if (reviews.size() > 0)
			return reviews.get(0);

		return null;
	}

}
