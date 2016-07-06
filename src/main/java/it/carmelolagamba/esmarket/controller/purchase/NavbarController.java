package it.carmelolagamba.esmarket.controller.purchase;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NavbarController {

	@RequestMapping(value = "/navbar")
	@ResponseBody
	public void setSearchFormVisibility(@RequestParam("FORM_IS_OPEN") boolean isOpen, HttpSession session) {
		SearchForm form = (SearchForm) session.getAttribute("searchForm");
		form.setOpen(isOpen);
		session.setAttribute("searchForm", form);
	}

}
