package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class RemoveController {

	private StoreService storeService;

	@Inject
	public RemoveController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value = "remove")
	public String remove(@RequestParam("idAccount") Long idAccount, @RequestParam("id") Long id, Model model) {
		
		storeService.removeStore(id);
		model.addAttribute("listOfStores", storeService.getAll(idAccount));

		return "store_management/list_of_storage";
	}
}
