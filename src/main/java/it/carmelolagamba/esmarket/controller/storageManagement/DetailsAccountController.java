package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class DetailsAccountController {

	private AuthenticationService authenticationService;

	@Inject
	public DetailsAccountController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "details_account")
	public String details(@RequestParam("id") Long id, Model model) {
		Account account = authenticationService.getById(id);
		model.addAttribute("account", account);
		return "store_management/details_account";
	}
}
