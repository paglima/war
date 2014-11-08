package com.war.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Jogo;
import com.war.dados.SalaDeJogo;
import com.war.dados.Usuario;
import com.war.form.TerritorioForm;
import com.war.service.JogoService;

@Controller("JogoController")
@RequestMapping("/jogo")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SessionAttributes({ "usuario" })
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
	
	@RequestMapping(value = "/partida",method = RequestMethod.POST )
	public ModelAndView partida(@RequestParam(value="turno", required=true) String turno,
								@ModelAttribute("territorioForm") TerritorioForm territorioForm,
							    HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiroJogo");
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			if (usuario != null) {
				Jogo jogo = usuario.getJogo();
				
				if (usuario.getTerritorios() != null) {
					jogoService.preparaTerritorios(jogo.getUsuarios(), territorioForm);
				}
				
				if (jogo.turnoDoInimigo()) {
					jogoService.processaJogadaInimiga(jogo);
				}
				
				view.addObject("usuarios", jogo.getUsuarios());
			}
			
			request.getSession().setAttribute("usuario", usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
			view.addObject("erro", "Erro ao distribuir terrotórios.");
		}
		
		return view;
	}
	
	@RequestMapping(value = "/distribuiExercito",method = RequestMethod.GET )
	public ModelAndView distribuiExercito(@ModelAttribute("usuario") Usuario usuario,
										  HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiro");
		
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuario");
		
		if (usuario == null || usuario.vazio() || usuario.getJogo() == null || 
				!usuario.getJogo().getPartidaComecada() || usuarioSessao != null) {
		 	usuario = usuarioSessao;
		}
			 	
	 	if (usuario != null) {
			Jogo jogo = usuario.getJogo();
			jogo.setTurno(1);
			
			if (usuario.getTerritorios() == null || (usuario.getTerritorios().isEmpty() && usuario.getAindaNoJogo())) {
				jogoService.preparaJogadores(jogo.getUsuarios());
				jogoService.distribuiExercitoInimigo(jogo.getUsuarios());
			}
			
			view.addObject("usuarios", jogo.getUsuarios());
	 	}
		
		view.addObject("territorioForm", new TerritorioForm());
		request.getSession().setAttribute("usuario", usuario);
		
		return view;
	}
	
	@RequestMapping(value = "/passar",method = RequestMethod.GET )
	public ModelAndView passarJogada() {
		return null;
	}
	
	@RequestMapping(value = "/fim",method = RequestMethod.GET )
	public ModelAndView fim() {
		return null;
	}
	
}