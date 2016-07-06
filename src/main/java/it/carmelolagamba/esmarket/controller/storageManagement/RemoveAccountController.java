package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class RemoveAccountController {

	private AuthenticationService authenticationService;

	@Inject
	public RemoveAccountController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "remove_account")
	public String remove(@RequestParam("id") Long id, Model model, HttpSession session) {
//		session.invalidate();
		authenticationService.removeAccount(id);
		model.addAttribute("listOfAccount", authenticationService.getAll());

		return "store_management/list_of_account";
	}
}
