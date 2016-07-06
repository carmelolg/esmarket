package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.service.OrderService;

@Controller
public class ModifyOrderController {

	OrderService orderService;

	@Inject
	public ModifyOrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "modify_order")
	public String modify(Model model, @RequestParam("idOrd") Long idOrd) {

		Order ord = orderService.getOrderByID(idOrd, false);
		model.addAttribute("order", ord);
		return "store_management/modify_order";
	}

	@RequestMapping(value = "modify_order", method = RequestMethod.POST)
	public String modify_order_result(Model model, @Valid Order order, BindingResult result, @RequestParam("idOrd") Long idOrd) {

		System.out.println(result);

		if (!result.hasErrors()) {
//			System.out.println("Hello 1");
			Order ord = orderService.getOrderByID(idOrd, false);
			order.setItems(ord.getItems());
			order.setOrderManager(ord.getOrderManager());
			order.setOrderOwner(ord.getOrderOwner());
			order.setPosition(ord.getPosition());
			order.setRequestDate(ord.getRequestDate());
			orderService.updateOrder(order);
			model.addAttribute("idOrd", idOrd);

			return "store_management/details_order";
		} else {
//			System.out.println("Hello 2");
			model.addAttribute("idOrd", idOrd);
			return "store_management/modify_order";
		}
	}
}
