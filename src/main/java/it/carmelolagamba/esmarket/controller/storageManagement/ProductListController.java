package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class ProductListController {

	private StoreService storeService;

	@Inject
	public ProductListController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@RequestMapping(value = "list_of_product")
	public String list_of_product(Model model, @RequestParam("storeID")Long id) {
				
		Store store = storeService.getById(id,false);
		
		model.addAttribute("storeID", id);
		model.addAttribute("store", store);
		model.addAttribute("listOfProducts", store.getProducts());
		
		return "store_management/list_of_product";
	}
	
}
