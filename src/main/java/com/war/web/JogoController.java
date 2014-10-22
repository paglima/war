package com.war.web;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("JogoController")
@RequestMapping("/jogo")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JogoController {

	@RequestMapping(value = "/",method = RequestMethod.GET )
	public ModelAndView jogo() {
		return new ModelAndView("tabuleiro");
	}
	
	public void salvarJogo() {		
	}
	
	@RequestMapping(value = "/atacar",method = RequestMethod.GET )
	public ModelAndView atacar() {
		return null;
	}
	
	@RequestMapping(value = "/passar",method = RequestMethod.GET )
	public ModelAndView passarJogada() {
		return null;
	}
	
	@RequestMapping(value = "/fim",method = RequestMethod.GET )
	public ModelAndView fim() {
		return null;
	}

	@RequestMapping(value = "/redistribuir",method = RequestMethod.GET )
	public ModelAndView redistribuir() {
		return null;
	}
	
}