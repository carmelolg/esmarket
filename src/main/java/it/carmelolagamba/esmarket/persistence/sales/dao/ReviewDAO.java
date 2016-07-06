package it.carmelolagamba.esmarket.persistence.sales.dao;

import java.util.List;

import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.persistence.GenericDAO;

public interface ReviewDAO extends GenericDAO<Review>{
	public List<Review> getReviewByProductID(Long productID);
	public Review getReviewByProductIDAndUserID(Long productID, Long userID);
}
