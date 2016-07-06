package it.carmelolagamba.esmarket.controller.sales;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Promotion;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.service.ProductsService;
import it.carmelolagamba.esmarket.service.PromotionService;

@Controller
public class ModifyPromoController {

	@RequestMapping(value = "/modifyPromotion")
	public String modifyPromotion(Model model, @RequestParam("idPromo") Long idPromo) {

		PromotionService promotionService = new PromotionService();
		Promotion promotion = promotionService.getPromotionByID(idPromo);
		String category = promotionService.getCategoryByPromotion(promotion);
		model.addAttribute("promotion", promotion);
		model.addAttribute("categ", category);
		return "sales/modifyPromotion";
	}

	@RequestMapping(value = "/modifyPromotion", method = RequestMethod.POST)
	public String modifyPromo(Model model, @RequestParam("CATEGORY")String category, @Valid Promotion promotion) {
		PromotionService promotionService = new PromotionService();
		promotionService.removePromotion(promotion);
		
		promotionService.savePromotion(promotion);
		ProductsService productsService = new ProductsService();
		List<Product> products = productsService
				.getProductsByCategory(category);
		for(Product p : products){
			p.setPromotion(promotion);
			productsService.updateProduct(p);
		}
		
		
		return "redirect:/listofPromotion";

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
