package com.war.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Usuario;
import com.war.dao.ObjetivoDao;
import com.war.dao.TerritorioDao;
import com.war.dao.UsuarioDao;
import com.war.form.UsuarioForm;
import com.war.service.ObjetivoService;

@Controller("MenuController")
@RequestMapping("/menu")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MenuController {
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TerritorioDao territorioDao;
	
	@RequestMapping(value = "/jogar",method = RequestMethod.GET )
	public String jogar(){
		return "../jogo/";
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.GET )
	public ModelAndView cadastraUsuario(){
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setUsuarios(new ArrayList<Usuario>());
		return new ModelAndView("cadastraUsuario","usuarioForm",usuarioForm);
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.POST )
	public ModelAndView cadastraUsuario(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm) {
		List<Usuario> usuariosCadastrados = usuarioForm.getUsuarios();
		
		objetivoService.sorteiaObjetivos(usuariosCadastrados);
		usuarioDao.saveAll(usuariosCadastrados);
		
		return new ModelAndView("listaObjetivos","jogadores",usuariosCadastrados);
	}

}