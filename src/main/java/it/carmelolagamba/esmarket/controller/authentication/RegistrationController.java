package it.carmelolagamba.esmarket.controller.authentication;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class RegistrationController {

	private AuthenticationService authService;

	@Inject
	public RegistrationController(AuthenticationService service) {
		authService = service;
	}

	@RequestMapping(value = "register")
	public String showRegistrationPage(Model model) {

		Client registringClient = new Client();
		model.addAttribute("registeringClient", registringClient);

		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String processRegistrationSubmition(HttpSession session, Model model, @ModelAttribute("registeringClient") @Valid Client client,
			BindingResult result) {

		if (!result.hasErrors()) {
			boolean registrationSubmitted = authService.registerUser(client);
			if (registrationSubmitted) {
				session.setAttribute("user", client);
				return "redirect:/";
			} else {
				model.addAttribute("errorMessage", "This Email address isn't avalaible");
			}
		}

		model.addAttribute("registeringClient", client);
		return "register";

	}

}
