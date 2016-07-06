package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class ManagerListController {

	private AuthenticationService authenticationService;

	@Inject
	public ManagerListController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "list_of_manager")
	public String list_of_manager(Model model) {
		model.addAttribute("listOfAccount", authenticationService.getManagers());
		return "store_management/list_of_manager";
	}

}
