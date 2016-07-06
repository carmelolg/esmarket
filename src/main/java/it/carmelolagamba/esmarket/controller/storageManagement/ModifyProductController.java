package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.service.ProductsService;

@Controller
public class ModifyProductController {

	ProductsService productService;
	
	@Inject
	public ModifyProductController(ProductsService productsService) {
		this.productService = productsService;
	}
	
	@RequestMapping(value = "modify_prod")
	public String modify(Model model, @RequestParam("idProd") Long idProd,
			@RequestParam("storeID") Long storeID) {

		Product prod = productService.getProductByID(idProd);
		model.addAttribute("product", prod);
		model.addAttribute("storeID", storeID);
		return "store_management/modify_prod";
	}

	@RequestMapping(value = "modify_prod", method = RequestMethod.POST)
	public String modify_product_result(Model model, @Valid Product product, BindingResult result,
			@RequestParam("idProd") Long idProd,
			@RequestParam("storeID") Long storeID) {

		if (!result.hasErrors()) {
			productService.updateProduct(product);
			product = productService.getProductByID(idProd);

			model.addAttribute("product", product);
			model.addAttribute("storeID", storeID);

			return "store_management/details_prod";
		} else {
			model.addAttribute("storeID", storeID);
			return "store_management/modify_prod";
		}
	}
}
