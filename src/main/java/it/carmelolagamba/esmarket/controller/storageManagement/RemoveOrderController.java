package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.service.OrderService;

@Controller
public class RemoveOrderController {

	OrderService orderService;

	@Inject
	public RemoveOrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "remove_order")
	public String remove(@RequestParam("id") Long id, Model model) {
		
		orderService.removeOrder(id);
		model.addAttribute("listOfOrders", orderService.getOrdersByManager(id));

		return "store_management/list_of_orders";
	}
}
