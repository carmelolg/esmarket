package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.service.AuthenticationService;
import it.carmelolagamba.esmarket.service.OrderService;

@Controller
public class OrderListController {

	OrderService orderService;
	AuthenticationService accountService;

	@Inject
	public OrderListController(OrderService orderService, AuthenticationService accountService) {
		this.orderService = orderService;
		this.accountService = accountService;
	}

	@RequestMapping(value = "list_of_orders")
	public String list_of_orders(@RequestParam("idUser") Long id, Model model) {

		model.addAttribute("manager", accountService.getById(id));
		model.addAttribute("listOfOrders", orderService.getOrdersByManager(id));

		return "store_management/list_of_orders";
	}

	@RequestMapping(value = "listOfClientOrders")
	public String listOfClientOrders(Model model, HttpSession session) {

		Client client = (Client) session.getAttribute("user");
		model.addAttribute("manager", client);
		model.addAttribute("listOfOrders", orderService.getOrdersByClient(client.getID()));

		return "store_management/listOfClientOrders";
	}

}
