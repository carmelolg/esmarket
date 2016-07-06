package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.ProductsService;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class AddProductController {

	private StoreService storeService;
	private ProductsService productService;

	@Inject
	public AddProductController(StoreService storeService, ProductsService productsService) {
		this.storeService = storeService;
		this.productService = productsService;
	}

	@RequestMapping(value = "add_product")
	public String add_product(Model model, @RequestParam("storeID") Long id) {

		Store store = storeService.getById(id,true);
		model.addAttribute("product", new Product());
		model.addAttribute("storeID", id);
		model.addAttribute("store", store);
		return "store_management/add_product";
	}

	@RequestMapping(value = "add_product", method = RequestMethod.POST)
	public String add_product_result(Model model, @ModelAttribute @Valid Product product, BindingResult result, @RequestParam("storeID") Long id) {

		if (!result.hasErrors()) {
			product = productService.saveProduct(product);

			Store store = storeService.getById(id,false);
			store.addProductToTheStore(product);
			storeService.updateStore(store);
			// store = storeService.getById(id);

			model.addAttribute("listOfProducts", store.getProducts());
			model.addAttribute("storeID", id);
			return "store_management/list_of_product";
		} else {
			model.addAttribute("storeID", id);
			return "store_management/add_product";
		}
	}

}
