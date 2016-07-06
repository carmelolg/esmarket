package it.carmelolagamba.esmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.sales.dao.PromotionDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;

@Service
public class PromotionService {

	private PromotionDAO promotionDAO;
	private ProductDAO productDAO;

	public PromotionService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		promotionDAO = daoFactory.getPromotionDAO();
		productDAO = daoFactory.getProductDAO();
	}

	public List<Promotion> getAllPromotions(){
		return promotionDAO.getAll();
	}
	
	public void removePromotion(Promotion promotion) {
		promotionDAO.remove(promotion.getID());
	}

	public void savePromotion(Promotion promotion) {
		promotionDAO.save(promotion);
	}

	public Promotion getPromotionByID(Long idPromo) {
		return promotionDAO.getById(idPromo);
	}

	public String getCategoryByPromotion(Promotion promotion) {
		List<Product> products = productDAO.getAll();
		for (Product p : products) {
			if (p.getPromotion() != null) {
				if (p.getPromotion().getID() == promotion.getID()) {
					return p.getCategory();
				}
			}
		}
		return "all";
	}

}
