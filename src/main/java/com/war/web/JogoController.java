package com.war.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Jogo;
import com.war.dados.SalaDeJogo;
import com.war.dados.Usuario;
import com.war.form.TerritorioForm;
import com.war.service.JogoService;

@Controller("JogoController")
@RequestMapping("/jogo")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JogoController {
	
	@Autowired
	private JogoService jogoService;
	
	@Autowired
	protected SalaDeJogo salaDeJogo;

	@RequestMapping(value = "/",method = RequestMethod.GET )
	public ModelAndView jogo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiro");
		return view;
	}
	
	@RequestMapping(value = "/distribuiExercito",method = RequestMethod.POST )
	public ModelAndView distribuiExercito(@ModelAttribute("territorioForm") TerritorioForm territorioForm) {
		ModelAndView view = new ModelAndView("tabuleiro");
		
		return view;
	}
	
	@RequestMapping(value = "/distribuiExercito",method = RequestMethod.GET )
	public ModelAndView distribuiExercito(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiro");
		
		Usuario usuario =  (Usuario) request.getSession().getAttribute("Usuario");
		
		if (usuario != null) {
			Jogo jogo = usuario.getJogo();
			
			if (usuario.getTerritorios() == null || usuario.getTerritorios().size() <= 0) {
				jogoService.preparaJogadores(jogo.getUsuarios());
			}
			
			view.addObject("usuarios", jogo.getUsuarios());
		}
		view.addObject("territorioForm", new TerritorioForm());
		
		return view;
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