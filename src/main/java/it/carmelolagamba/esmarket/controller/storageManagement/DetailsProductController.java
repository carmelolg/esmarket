package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.ProductsService;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class DetailsProductController {

	private StoreService storeService;
	private ProductsService productService;

	@Inject
	public DetailsProductController(StoreService storeService, ProductsService productsService) {
		this.storeService = storeService;
		this.productService = productsService;
	}

	@RequestMapping(value = "details_prod")
	public String details_prod(@RequestParam("idProd") Long idProd, @RequestParam("storeID") Long storeID, Model model) {

		Product prod = productService.getProductByID(idProd);
		Store store = storeService.getById(storeID,true);
		model.addAttribute("store", store);
		model.addAttribute("product", prod);
		model.addAttribute("storeID", storeID);

		return "store_management/details_prod";
	}
}
