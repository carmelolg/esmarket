package it.carmelolagamba.esmarket.controller.purchase;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.account.Account;
import it.carmelolagamba.esmarket.domain.account.Client;
import it.carmelolagamba.esmarket.domain.sales.Review;
import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.service.OrderService;
import it.carmelolagamba.esmarket.service.ProductsService;
import it.carmelolagamba.esmarket.service.ReviewService;

@Controller
public class ProductController {
	
	private ProductsService productsService;
	private ReviewService reviewService;
	private OrderService orderService;

	@Inject
	public ProductController(ProductsService productService, ReviewService reviewService, OrderService orderService) {
		this.productsService = productService;
		this.reviewService = reviewService;
		this.orderService = orderService;
	}

	@RequestMapping(value = "/product_details")
	public String showProductDetails(@RequestParam("PRODUCT_ID") Long ID, Model model, HttpSession session) {

		Product product = productsService.getProductByID(ID);
		model.addAttribute("product", product);

		Product example = new Product();
		example.setCategory(product.getCategory());

		List<Product> products = productsService.getProductsBySearch(example, 0, Float.MAX_VALUE, 0);
		if (products.size() > 9)
			model.addAttribute("relatedProducts", products.subList(0, 9));
		else
			model.addAttribute("relatedProducts", products.subList(0, products.size()));

		model.addAttribute("productReviews", reviewService.getProductReviews(ID));

		Account currentUser = (Account) session.getAttribute("user");

		if (currentUser != null) {
			if (currentUser instanceof Client) {
				if (orderService.currentUserOrdersContainsProduct(currentUser.getID(), ID)) {
					Review currentUserReview = reviewService.getCurrentUserReview(ID, currentUser.getID());
					model.addAttribute("currentUserReview", currentUserReview);
				}
			} else {
				model.addAttribute("isAdmin", "isAdmin");
			}
		}
		return "purchase/product_details";

	}

	@RequestMapping(value = "/product_details", params = { "REVIEW_TEXT", "PRODUCT_ID" })
	public String addAReview(@RequestParam("REVIEW_TEXT") String text, @RequestParam("PRODUCT_ID") Long ID, HttpSession session) {

		Review currentUserReview = reviewService.getCurrentUserReview(ID, ((Client) session.getAttribute("user")).getID());

		if (text.equals("")) {
			if (currentUserReview.getAuthor() != null)
				reviewService.deleteReview(currentUserReview.getID());
			return "redirect: /product_details?PRODUCT_ID=" + ID;
		}

		currentUserReview.setReviewText(text);

		if (currentUserReview.getAuthor() == null) {
			currentUserReview.setAuthor(((Client) session.getAttribute("user")));
			currentUserReview.setReviewedProduct(productsService.getProductByID(ID));
			reviewService.saveReview(currentUserReview);
		} else {
			reviewService.updateReview(currentUserReview);
		}

		return "redirect: /product_details?PRODUCT_ID=" + ID;
	}

	@RequestMapping(value = "/deleteReview")
	public String delteReview(@RequestParam("REVIEW_ID") Long reviewID, @RequestParam("PRODUCT_ID") Long ID) {
		reviewService.deleteReview(reviewID);
		return "redirect: /product_details?PRODUCT_ID=" + ID;
	}

}
