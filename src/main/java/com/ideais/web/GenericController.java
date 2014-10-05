package com.ideais.web;

import java.util.ArrayList;
import java.util.List;

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
import com.ideais.form.UsuarioForm;
import com.ideais.service.ObjetivoService;

@Controller
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericController {
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TerritorioDao territorioDao;
	
	@RequestMapping(value = "/tabuleiro",method = RequestMethod.GET )
	public String tabuleiro(){
		return "tabuleiro";
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.GET )
	public ModelAndView cadastraUsuario(){
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setUsuarios(new ArrayList<Usuario>());
		return new ModelAndView("cadastraUsuario","usuarioForm",usuarioForm);
	}
	
	@RequestMapping(value = "/cadastraUsuario",method = RequestMethod.POST )
	public ModelAndView cadastraUsuario(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm){
		List<Usuario> usuariosCadastrados = usuarioForm.getUsuarios();
		
		List<Long> listaObjetivoId = new ArrayList<Long>();
		
		for (Usuario usuario : usuariosCadastrados) {
			Objetivo objetivo = objetivoService.sorteiaObjetivo();
			
			if(listaObjetivoId.contains(objetivo.getIdObjetivo())){
				continue;
			}
			
			usuario.setObjetivo(objetivo);
			listaObjetivoId.add(objetivo.getIdObjetivo());
			
		}
		
		usuarioDao.saveAll(usuariosCadastrados);
		
		return new ModelAndView("listaObjetivos","jogadores",usuariosCadastrados);
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
		
		objetivoService.sorteiaObjetivo();
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