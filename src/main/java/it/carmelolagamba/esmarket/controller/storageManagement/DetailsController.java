package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class DetailsController {

	private StoreService storeService;

	@Inject
	public DetailsController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value = "details")
	public String details(@RequestParam("id")Long idStore, Model model) {
		Store store = storeService.getById(idStore,false);
		model.addAttribute("store", store);
		
		return "store_management/details";
	}
}
