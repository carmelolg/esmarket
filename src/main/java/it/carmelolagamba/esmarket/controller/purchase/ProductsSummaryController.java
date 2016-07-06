package it.carmelolagamba.esmarket.controller.purchase;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.sales.SaleItem;

@Controller
public class ProductsSummaryController {

	@RequestMapping(value = "/product_summary")
	public String showProductSummary(Model model, HttpSession session) {
		return "purchase/product_summary";
	}

	@RequestMapping(value = "/product_summary", params = { "QUANTITY", "ITEM_POS" })
	public String changeItemQuantity(Model model, HttpSession session, @RequestParam("QUANTITY") int quantity, @RequestParam("ITEM_POS") int pos) {

		Order order = (Order) session.getAttribute("order");

		SaleItem item = order.getItems().get(pos);
		if (quantity <= 0)
			order.removeItem(item);
		else {
			item.setQuantity(quantity);
			order.setTotalPrice();
		}
		session.setAttribute("order", order);

		return "purchase/product_summary";
	}

}
