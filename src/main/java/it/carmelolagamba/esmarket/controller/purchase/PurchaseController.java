package it.carmelolagamba.esmarket.controller.purchase;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.storageManagement.Store;
import it.carmelolagamba.esmarket.service.OrderService;
import it.carmelolagamba.esmarket.service.StoreService;

@Controller
public class PurchaseController {

	private StoreService storeService;
	private OrderService orderService;

	@Inject
	public PurchaseController(StoreService storeService, OrderService orderService) {
		this.storeService = storeService;
		this.orderService = orderService;
	}

	@RequestMapping(value = "purchase")
	public String showPurchasePage(Model model, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		model.addAttribute("stores", storeService.getStoresContainingOrderProducts(order));
		model.addAttribute("submitForm", new SubmitForm());

		return "purchase/purchase";
	}

	@RequestMapping(value = "purchase", method = RequestMethod.POST)
	public String submitOrder(Model model, @ModelAttribute SubmitForm form, HttpSession session) {

		Order order = (Order) session.getAttribute("order");
		Client client = (Client) session.getAttribute("user");
		order.setOrderOwner(client);
		order.setRequestDate(new Date(System.currentTimeMillis()));

		Store store = new Store();

		if (form.getDeliveryMode().equals("delivery")) {

			if (form.getAddressSelected().equals("registrationAddress"))
				order.setDestinationAddress(client.getAddress());
			else
				order.setDestinationAddress(form.getAddress());

			store = storeService.getStoresContainingOrderProducts(order).get(0);
		} else {
			store = storeService.getById(form.getStoreID(), true);
		}

		order.setPosition(store.getPosition());
		order.setOrderManager(store.getAccount());

		boolean submitted = orderService.saveOrder(order);

		if (submitted) {
			session.setAttribute("order", new Order());
			return "redirect:/";
		} else {
			model.addAttribute("submitErrorMessage", "Some products in the cart are no more available");
			model.addAttribute("stores", storeService.getStoresContainingOrderProducts(order));
			model.addAttribute("submitForm", form);
			return "purchase/purchase";
		}
	}

}
