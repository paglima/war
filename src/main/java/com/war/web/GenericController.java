package com.war.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.SalaDeJogo;

@Component
public class GenericController {
	
	@Autowired
	protected SalaDeJogo salaDeJogo;
	
	public ModelAndView getBaseView(String nomeDaPagina) {
		return new ModelAndView(nomeDaPagina);
	}
	
}