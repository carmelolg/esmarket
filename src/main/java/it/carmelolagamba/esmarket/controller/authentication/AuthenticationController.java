package it.carmelolagamba.esmarket.controller.authentication;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.service.AuthenticationService;

@Controller
public class AuthenticationController {

	private AuthenticationService authService;

	@Inject
	public AuthenticationController(AuthenticationService service) {
		authService = service;
	}

	@RequestMapping("/loginPage")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String login(HttpSession session, @RequestParam("mail") String mail, @RequestParam("password") String password, Model model) {
		Account account = authService.loginAccount(mail, password);
		if (account == null)
			return "false";
		else
			session.setAttribute("user", account);

		return "true";
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect: /";
	}

}
