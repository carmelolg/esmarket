package it.carmelolagamba.esmarket.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class AccountDetailsController {

	private AuthenticationService authenticationService;

	@Inject
	public AccountDetailsController(AuthenticationService service) {
		this.authenticationService = service;
	}

	@RequestMapping(value = "/accountDetails")
	public String showAccountDetils(Model model, HttpSession session) {

		model.addAttribute("currentUser", session.getAttribute("user"));

		return "accountDetails";
	}

	@RequestMapping(value = "/modifyUserInfo")
	public String modifyUserInfo(HttpSession session, Model model, @Valid @ModelAttribute("currentUser") Client user,
			BindingResult result) {

		System.out.println(result);
		if (!result.hasErrors()) {
			Account sessionUser = (Account) session.getAttribute("user");
			sessionUser.setAddress(user.getAddress());
			sessionUser.setPassword(user.getPassword());
			sessionUser.setUsername(user.getUsername());
			authenticationService.updateUserINFO(sessionUser);
			session.setAttribute("user",
					authenticationService.loginAccount(sessionUser.geteMail(), sessionUser.getPassword()));
		}

		return "accountDetails";
	}

}
