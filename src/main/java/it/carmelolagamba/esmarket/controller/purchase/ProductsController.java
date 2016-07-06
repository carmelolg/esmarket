package it.carmelolagamba.esmarket.controller.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.service.ProductsService;

@Controller
public class ProductsController {

	private ProductsService productsService;

	@Inject
	public ProductsController(ProductsService ps) {
		productsService = ps;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String setSessionSearchForm(Model model, HttpSession session, @Valid SearchForm form, BindingResult bindingResult) {
		if (!bindingResult.hasErrors())
			session.setAttribute("searchForm", form);
		return showProductsByCategory(model, session);
	}

	@RequestMapping(value = "/products", params = "CATEGORY")
	public String setSessionCategory(Model model, HttpSession session, @RequestParam("CATEGORY") String category) {
		SearchForm form = new SearchForm();
		form.setCategory(category);
		session.setAttribute("searchForm", form);
		return showProductsByCategory(model, session);
	}

	@RequestMapping(value = "/products", params = { "CATEGORY", "PRODUCT_NAME" })
	public String setSessionProductName(Model model, HttpSession session, @RequestParam("CATEGORY") String category,
			@RequestParam("PRODUCT_NAME") String name) {
		SearchForm form = new SearchForm();
		form.setCategory(category);
		form.setProductName(name);
		session.setAttribute("searchForm", form);
		return showProductsByCategory(model, session);
	}

	@RequestMapping(value = "/products", params = "PAGE_NUM")
	public String setSessionPageNum(Model model, HttpSession session, @RequestParam("PAGE_NUM") int pageNum) {
		session.setAttribute("pageNum", pageNum);
		return showProductsByCategory(model, session);
	}

	@RequestMapping(value = "/products", params = "ORDER_OPTION")
	public String setSessionOrderOption(Model model, HttpSession session, @RequestParam("ORDER_OPTION") int orderOption) {
		session.setAttribute("orderOption", orderOption);
		return showProductsByCategory(model, session);
	}

	public String showProductsByCategory(Model model, HttpSession session) {

		SearchForm form = (SearchForm) session.getAttribute("searchForm");
		int orderOption = (Integer) session.getAttribute("orderOption");
		int pageNum = (Integer) session.getAttribute("pageNum");

		model.addAttribute("category", form.getCategory());

		List<Product> products = null;

		Product example = new Product();
		example.setBrand(form.getBrand());
		example.setCategory(form.getCategory());
		example.setName(form.getProductName());

		if (example.getCategory().equals("all"))
			example.setCategory(null);
		if (example.getBrand().equals("all"))
			example.setBrand(null);

		products = productsService.getProductsBySearch(example, form.getMinPrice(), form.getMaxPrice(), orderOption);

		// setting sublist of products to show
		if ((pageNum - 1) * 9 + 9 >= products.size()) {
			model.addAttribute("products", products.subList((pageNum - 1) * 9, products.size()));
			model.addAttribute("forwIndex", pageNum);
		} else {
			model.addAttribute("products", products.subList((pageNum - 1) * 9, (pageNum - 1) * 9 + 9));
			model.addAttribute("forwIndex", pageNum + 1);
		}

		model.addAttribute("orderOption", orderOption);

		// setting comboBoxOptions
		HashMap<Integer, String> comboBoxOptions = new HashMap<Integer, String>();
		comboBoxOptions.put(0, "Product name A - Z");
		comboBoxOptions.put(1, "Product name Z - A");
		comboBoxOptions.put(2, "Price Lowest First");
		comboBoxOptions.put(3, "Price Highest First");

		model.addAttribute("comboBoxOptions", comboBoxOptions);

		// setting index for products pages

		ArrayList<Integer> pagesIndex = new ArrayList<Integer>();
		if (pageNum == 1)
			model.addAttribute("backIndex", 1);
		else
			model.addAttribute("backIndex", pageNum - 1);
		if (pageNum < 3) {
			for (int i = 1; i < 5; i++) {
				pagesIndex.add(i);
				if (products.size() < i * 9)
					break;
			}
		} else {
			for (int i = pageNum - 2; i < pageNum + 3; i++) {
				pagesIndex.add(i);
				if (products.size() < i * 9)
					break;
			}
		}

		model.addAttribute("pagesIndex", pagesIndex);

		return "purchase/products";
	}

}
