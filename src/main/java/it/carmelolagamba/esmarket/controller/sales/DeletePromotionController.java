package it.carmelolagamba.esmarket.controller.sales;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.PromotionService;

@Controller
public class DeletePromotionController {
	@RequestMapping(value = "/deletePromotion")
	public String deletePromotion(Model model,
			@RequestParam("idPromo") Long idPromo) {

		PromotionService promotionService = new PromotionService();
		promotionService.removePromotion(promotionService.getPromotionByID(idPromo));
		return "redirect:/listofPromotion";
	}
}
