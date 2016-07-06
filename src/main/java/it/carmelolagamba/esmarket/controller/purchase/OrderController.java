package it.carmelolagamba.esmarket.controller.purchase;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.service.OrderService;
import it.carmelolagamba.esmarket.service.ProductsService;

@Controller
public class OrderController {

	private OrderService orderService;
	private ProductsService productsService;

	@Inject
	public OrderController(OrderService orderServive, ProductsService productsService) {
		this.orderService = orderServive;
		this.productsService = productsService;
	}

	@RequestMapping(value = "/product_details", method = RequestMethod.POST)
	public String addOrderToCart(@RequestParam("QUANTITY") String quantity, @RequestParam("PRODUCT_ID") Long ID, HttpSession session, Model model) {
		Product product = productsService.getProductByID(ID);
		model.addAttribute("product", product);
		Product example = new Product();
		example.setCategory(product.getCategory());
		List<Product> products = productsService.getProductsBySearch(example, 0, Float.MAX_VALUE, 0);
		if (products.size() > 9)
			model.addAttribute("relatedProducts", products.subList(0, 9));
		else
			model.addAttribute("relatedProducts", products.subList(0, products.size()));

		if (quantity.matches("^[1-9][0-9]*"))
			orderService.addSaleItemToOrder((Order) session.getAttribute("order"), product, Integer.valueOf(quantity));
		else
			model.addAttribute("errorMessage", "Products quantity must be a positive integer number");
		return "purchase/product_details";
	}

}
