package it.carmelolagamba.esmarket.controller.sales;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.service.PromotionService;

@Controller
public class PromoDetailsController {

	@RequestMapping(value = "/detailsPromo")
	public String detailsPromotion(Model model,
			@RequestParam("idPromo") Long idPromo)

	{
		PromotionService promotionService = new PromotionService();
		Promotion promotion = promotionService.getPromotionByID(idPromo);

		String category = promotionService.getCategoryByPromotion(promotion);
		
		model.addAttribute("promotion", promotion);
		model.addAttribute("category", category);

		return ("sales/promoDetails");

	}

}