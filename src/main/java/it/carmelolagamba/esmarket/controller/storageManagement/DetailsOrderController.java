package it.carmelolagamba.esmarket.controller.storageManagement;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.OrderService;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class DetailsOrderController {

	OrderService orderService;
	StoreService storeService;
	
	@Inject
	public DetailsOrderController(OrderService os, StoreService ss) {
		this.orderService = os;
		this.storeService = ss;
	}
	
	@RequestMapping(value = "details_order")
	public String details_prod(@RequestParam("idOrd")Long idOrder, Model model) {
		
		Order order = orderService.getOrderByID(idOrder,false);
		model.addAttribute("order", order);
		Store store = storeService.getStoreByAccount(order.getOrderManager().getID());
		model.addAttribute("storeID", store.getID());
		return "store_management/details_order";
	}
}
