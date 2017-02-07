package com.sorcererpaws.eSmartLabs.web.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalExceptionHandler {

	@RequestMapping(value = "/pageNotFound" , method = RequestMethod.GET)
	public ModelAndView pageNotFoundException() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/pageNotFound");
		return modelAndView;
	}
	
	@RequestMapping(value = "/error500" , method = RequestMethod.GET)
	public ModelAndView error500(HttpServletRequest request, HttpServletResponse response) {
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error500", throwable.getLocalizedMessage());
		modelAndView.setViewName("error/error500");
		return modelAndView;
	}
	
	@RequestMapping(value = "/error400" , method = RequestMethod.GET)
	public ModelAndView error400() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/error400");
		return modelAndView;
	}
	
	@RequestMapping(value = "/accessDenied" , method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/accessDenied");
		return modelAndView;
	}
}