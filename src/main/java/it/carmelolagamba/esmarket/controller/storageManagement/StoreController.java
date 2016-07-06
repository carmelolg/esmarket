package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class StoreController {

	StoreService storeService;

	@Inject
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@RequestMapping(value = "list_of_storage")
	public String list_of_storage(@RequestParam("id")Long idAccount, Model model) {
		
		model.addAttribute("listOfStores", storeService.getAll(idAccount));
		
		return "store_management/list_of_storage";
	}
	
}
