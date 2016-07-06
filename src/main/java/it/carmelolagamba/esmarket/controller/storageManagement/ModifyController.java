package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.AuthenticationService;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class ModifyController {

	private StoreService storeService;

	private AuthenticationService authenticationService;

	@Inject
	public ModifyController(StoreService storeService, AuthenticationService authenticationService) {
		this.storeService = storeService;
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "modify")
	public String modify(Model model, @RequestParam("id") Long idStore, @RequestParam("idAccount") Long idAccount) {
		Store store = storeService.getById(idStore, true);
		model.addAttribute("store", store);
		model.addAttribute("idAccount", idAccount);
		return "store_management/modify";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String add_storage_result(Model model, @Valid Store store, BindingResult result, @RequestParam("idAccount") Long idAccount) {

		if (!result.hasErrors()) {
			Store store_inDB = storeService.getById(store.getID(), false);
			store.setProducts(store_inDB.getProducts());
			Account account = authenticationService.getById(idAccount);
			store.setAccount(account);
			storeService.updateStore(store);

			// store = storeService.getById(store.getID());

			model.addAttribute("id", store.getID());
			model.addAttribute("idAccount", idAccount);
			return "store_management/details";
		} else {
			model.addAttribute("store", store);
			model.addAttribute("idAccount", idAccount);
			return "store_management/modify";
		}
	}

}
