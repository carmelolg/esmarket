package it.carmelolagamba.esmarket.controller.sales;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class AddPromoController {
	@RequestMapping(value = "/addPromotion")
	public String addPromotion(Model model) {

		ProductsService productsService = new ProductsService();
		Promotion promotion = new Promotion();
		model.addAttribute("categories",
				productsService.getProductsCategories());
		model.addAttribute("Promo", promotion);

		return "sales/addPromotion";
	}

	@RequestMapping(value = "/addPromotion", method = RequestMethod.POST)
	public String SavePromotion(Model model,
			@RequestParam("CATEGORY") String category, Promotion promotion) {

		ProductsService productsService = new ProductsService();
		PromotionService promotionService = new PromotionService();

		List<Product> products = productsService
				.getProductsByCategory(category);
		promotionService.savePromotion(promotion);
		boolean thereIs=false;
		Promotion old_promo = null;
		for (Product p : products) {
			if(p.getPromotion()!=null){
				thereIs=true;
				old_promo = p.getPromotion();
			}
			p.setPromotion(promotion);
			productsService.updateProduct(p);
		}
		if(thereIs){
			promotionService.removePromotion(old_promo);
		}
		return "redirect:/listofPromotion";

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
