package com.ideais.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ideais.dados.Objetivo;
import com.ideais.dados.Usuario;
import com.ideais.dao.ObjetivoDao;
import com.ideais.dao.TerritorioDao;
import com.ideais.dao.UsuarioDao;

@Controller
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericController {
	
	@Autowired
	ObjetivoDao objetivoDao;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	TerritorioDao territorioDao;
	
	@RequestMapping(value = "/tabuleiro",method = RequestMethod.GET )
	public String tabuleiro(){
		return "tabuleiro";
	}
	
	@RequestMapping(value = "/cadastro",method = RequestMethod.GET )
	public ModelAndView newVeiculo(){
		return new ModelAndView("cadastro","objetivo",new Objetivo());
	}
	
	@RequestMapping(value = "/cadastro",method = RequestMethod.POST )
	public String newVeiculo( Objetivo objetivo ){
		objetivoDao.saveOrUpdate(objetivo);
		return "teste";
	}
	
	@RequestMapping(value = "/ajax",method = RequestMethod.GET )
	public @ResponseBody String ajax(@RequestParam String teste, @ModelAttribute("veiculo") Usuario veiculo){
		System.out.println();
		return "oi";
	}

	@RequestMapping(value= "/testes",method = RequestMethod.GET)
	public String testes(){
		Usuario usuario = usuarioDao.findById(1L);
		
		territorioDao.findByName("Japão");
		
		usuario.addTerritorio(territorioDao.findById(1L));
		
		usuarioDao.saveOrUpdate(usuario);
		
		return "teste";
	}
	
	@RequestMapping(value= "/lista",method = RequestMethod.GET)
	public ModelAndView list(){
		return new ModelAndView("description", "objetivos", objetivoDao.findAll());
	}

}