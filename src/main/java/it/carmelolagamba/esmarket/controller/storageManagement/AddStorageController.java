package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class AddStorageController {

	StoreService storeService;

	@Inject
	public AddStorageController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value = "add_storage")
	public String add_storage(Model model) {
		model.addAttribute("store", new Store());
		return "store_management/add_storage";
	}

	@RequestMapping(value = "add_storage", method = RequestMethod.POST)
	public String add_storage_result(Model model, @Valid Store store,
			BindingResult result) {

		if (!result.hasErrors()) {
			storeService.saveStore(store);
//			model.addAttribute("listOfStores", storeService.getAll());
			return "store_management/list_of_storage";
		} else {
			return "store_management/add_storage";
		}
	}

}
