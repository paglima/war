package com.war.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Cor;
import com.war.dados.Jogo;
import com.war.dados.SalaDeJogo;
import com.war.dados.TipoDeJogo;
import com.war.dados.Usuario;
import com.war.dao.TerritorioDao;
import com.war.dao.UsuarioDao;
import com.war.service.JogoService;
import com.war.service.ObjetivoService;

@Controller("MenuController")
@RequestMapping("/menu")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MenuController {
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TerritorioDao territorioDao;
	
	@Autowired
	protected SalaDeJogo salaDeJogo;
	
	@Autowired
	protected JogoService jogoService;
	
	@RequestMapping(value = "/jogarSolo",method = RequestMethod.POST )
	public String jogarSolo(@RequestParam(value="nome", required=true) String nome, 
							@RequestParam(value="nomeCor", required=true) String nomeCor,
							@RequestParam(value="quantidadeInimigos", required=true) String quantidadeInimigos,
							HttpServletRequest request) {
		
		Usuario usuario = new Usuario(nome, nomeCor);
		
		String nomeIdentificador = "Sala " + UUID.randomUUID().toString();
		salaDeJogo.adicionaJogo(new Jogo(TipoDeJogo.SOLO.getTipo(), usuario, Integer.parseInt(quantidadeInimigos), nomeIdentificador));
		
		request.getSession().setAttribute("usuario", usuario);
		
		return "redirect:../jogo/distribuiExercito";
	}
	
	@RequestMapping(value = "/inicio",method = RequestMethod.GET )
	public ModelAndView modoDeJogo() {
		return new ModelAndView("opcaoDeJogo");
	}
	
	@RequestMapping(value = "/solo",method = RequestMethod.GET )
	public ModelAndView jogoSolo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("solo");
		view.addObject("cores", Cor.values());
		
		if (jogoService.jogoJaComecou((Usuario) request.getSession().getAttribute("usuario"))) {
			view.addObject("jogoComecado", "true");
		}
		
		return view;
	}
	
}