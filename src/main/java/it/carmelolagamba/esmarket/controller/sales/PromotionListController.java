package it.carmelolagamba.esmarket.controller.sales;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.service.PromotionService;

@Controller
public class PromotionListController {

	private PromotionService promotionService;

	@Inject
	public PromotionListController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@RequestMapping(value = "/listofPromotion")
	public String promotionList(Model model) {

		List<Promotion> promotion = promotionService.getAllPromotions();
		model.addAttribute("promo", promotion);

		return "sales/list_of_Promotion";

	}

}