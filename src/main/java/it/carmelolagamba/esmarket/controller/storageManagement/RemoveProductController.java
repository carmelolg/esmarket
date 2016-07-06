package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class RemoveProductController {

	
	StoreService storeService;

	@Inject
	public RemoveProductController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@RequestMapping(value = "remove_prod")
	public String remove(@RequestParam("idProd")Long idProd,@RequestParam("storeID")Long storeID, Model model) {
		
		Store store = storeService.removeProductFromStore(idProd, storeID);
		
		model.addAttribute("listOfProducts", store.getProducts());
		model.addAttribute("storeID", storeID);
		return "store_management/list_of_product";
	}
}
