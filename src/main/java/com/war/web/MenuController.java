package com.war.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Cor;
import com.war.dados.Jogo;
import com.war.dados.TipoDeJogo;
import com.war.dados.Usuario;
import com.war.dao.ObjetivoDao;
import com.war.dao.TerritorioDao;
import com.war.dao.UsuarioDao;
import com.war.form.UsuarioForm;
import com.war.service.ObjetivoService;

@Controller("MenuController")
@RequestMapping("/menu")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MenuController extends GenericController {
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TerritorioDao territorioDao;
	
	@RequestMapping(value = "/jogarSolo",method = RequestMethod.POST )
	public String jogarSolo(@RequestParam(value="nome", required=true) String nome, 
							@RequestParam(value="codigoCor", required=true) String codigoCor,
							@RequestParam(value="quantidadeInimigos", required=true) String quantidadeInimigos,
							HttpServletRequest request) {
		
		Usuario usuario = new Usuario(nome);
		usuario.setJogadorHumano(Boolean.TRUE);
		//Usuario.setCor(codigoCor);
		
		String nomeIdentificador = "Sala " + UUID.randomUUID().toString();
		salaDeJogo.adicionaJogo(new Jogo(TipoDeJogo.SOLO.getTipo(), usuario, Integer.parseInt(quantidadeInimigos), nomeIdentificador));
		
		request.getSession().setAttribute(nomeIdentificador, salaDeJogo.getJogoPorNome(nomeIdentificador));
		
		return "redirect:../jogo/";
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.GET )
	public ModelAndView cadastraUsuario() {
		ModelAndView view = getBaseView("cadastraUsuario");
		
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setUsuarios(new ArrayList<Usuario>());
		
		view.addObject("usuarioForm", usuarioForm);
		
		return view;
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.POST )
	public ModelAndView cadastraUsuario(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm) {
		ModelAndView view = getBaseView("listaObjetivos");
		
		List<Usuario> usuariosCadastrados = usuarioForm.getUsuarios();
		
		objetivoService.sorteiaObjetivos(usuariosCadastrados);
		usuarioDao.saveAll(usuariosCadastrados);
		
		view.addObject("jogadores", usuariosCadastrados);
		
		return view;
	}
	
	@RequestMapping(value = "/inicio",method = RequestMethod.GET )
	public ModelAndView modoDeJogo() {
		return getBaseView("opcaoDeJogo");
	}
	
	@RequestMapping(value = "/solo",method = RequestMethod.GET )
	public ModelAndView jogoSolo() {
		ModelAndView view = getBaseView("solo");
		view.addObject("cores", Cor.values());
		
		return view;
	}
	
}