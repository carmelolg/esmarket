package it.carmelolagamba.esmarket.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.carmelolagamba.esmarket.controller.purchase.SearchForm;
import it.carmelolagamba.esmarket.domain.sales.Order;
import it.carmelolagamba.esmarket.service.ProductsService;

@Component
public class RequestsInterceptor implements HandlerInterceptor {

	private ProductsService productsService;

	@Inject
	public RequestsInterceptor(ProductsService productsService) {
		this.productsService = productsService;
	}

	public void initializeSession(HttpSession session) {
		if (session.getAttribute("pageNum") == null) {
			session.setAttribute("pageNum", 1);
			session.setAttribute("orderOption", 0);
			session.setAttribute("searchForm", new SearchForm());
			session.setAttribute("order", new Order());
		}

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		initializeSession(request.getSession());
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("categories", productsService.getProductsCategories());
			modelAndView.addObject("brands", productsService.getAllBrands());
			modelAndView.addObject("searchForm", request.getSession().getAttribute("searchForm"));
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
