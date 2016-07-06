package it.carmelolagamba.esmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.sales.dao.ReviewDAO;

@Service
public class ReviewService {

	private ReviewDAO reviewDAO;

	public ReviewService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		reviewDAO = daoFactory.getReviewDAO();
	}

	public List<Review> getProductReviews(Long productID) {
		return reviewDAO.getReviewByProductID(productID);
	}

	public Review getCurrentUserReview(Long productID, Long userID) {

		Review review = reviewDAO.getReviewByProductIDAndUserID(productID, userID);

		if (review == null)
			review = new Review();

		return review;
	}

	public void saveReview(Review review) {
		reviewDAO.save(review);
	}

	public void updateReview(Review review) {
		reviewDAO.update(review);
	}

	public void deleteReview(Long ID) {
		reviewDAO.remove(ID);
	}

}
