package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class SetManagerAccountController {

	private AuthenticationService authenticationService;

	@Inject
	public SetManagerAccountController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "set_manager_account")
	public String setManager(@RequestParam("id") Long id, Model model) {

		boolean tellMe = authenticationService.setManager(id);

		if (tellMe) {
			model.addAttribute("listOfAccount", authenticationService.getManagers());
			return "store_management/list_of_manager";
		}else{
			model.addAttribute("listOfAccount", authenticationService.getAll());
			return "store_management/list_of_account";
		}
	}
}
