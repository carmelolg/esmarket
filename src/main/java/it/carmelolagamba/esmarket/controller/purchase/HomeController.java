package it.carmelolagamba.esmarket.controller.purchase;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.carmelolagamba.esmarket.service.ProductsService;

@Controller
public class HomeController {

	private ProductsService productsService;

	@Inject
	public HomeController(ProductsService productService) {
		this.productsService = productService;
	}

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("featuredProducts", productsService.getFeaturedProducts());
		model.addAttribute("latestProducts", productsService.getLatestProducts());
		return "home";
	}

}
