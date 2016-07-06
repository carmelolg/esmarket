package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class AccountListController {

	private AuthenticationService authenticationService;
	@Inject
	public AccountListController(AuthenticationService authenticationService) {
			this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "list_of_account")
	public String list_of_account(Model model) {
		
		model.addAttribute("listOfAccount", authenticationService.getAll());
		
		return "store_management/list_of_account";
	}
	
}
