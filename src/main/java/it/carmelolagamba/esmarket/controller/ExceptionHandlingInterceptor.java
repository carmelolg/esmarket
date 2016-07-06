package it.carmelolagamba.esmarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingInterceptor{

	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {

		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMessage", "internal server error");
		mav.setViewName("errorPage");
		return mav;

	}

}

