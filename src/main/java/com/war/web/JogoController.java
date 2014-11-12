package com.war.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.war.dados.Jogada;
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
					Jogada jogada = jogoService.processaJogadaInimiga(jogo);
					view.addObject("jogada", jogada);
				} else {
					view.addObject("turnoJogadorHumano", true);
				}
				
				view.addObject("usuarios", jogo.getUsuarios());
			}
			
			view.addObject("turno", usuario.getJogo().getTurno());
			view.addObject("territorioForm", new TerritorioForm());
			
			boolean sessaoExiste = verificaSessao(request);
			
			if (sessaoExiste) {
				colocaUsuarioNaSessao(request, usuario, sessaoExiste);  
				return view;
			}
			
			view.addObject("erro", "A sessão de jogo expirou.");
		} catch (Exception e) {
			e.printStackTrace();
			view.addObject("erro", "Erro ao exibir terrotórios.");
		}
		
		return view;
	}

	private void colocaUsuarioNaSessao(HttpServletRequest request,
			Usuario usuario, boolean sessaoExiste) {
		if (sessaoExiste) {
			request.getSession().setMaxInactiveInterval(60 * 60 * 6);
			request.getSession().setAttribute("usuario", usuario);
		}
	}

	private boolean verificaSessao(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		
		return true;
	}
	
	@RequestMapping(value = "/remanejamento",method = RequestMethod.POST )
	public ModelAndView remanejaExercito(@RequestParam(value="turno", required=true) String turno,
									     @ModelAttribute("territorioForm") TerritorioForm territorioForm,
									     HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiroRemanejamento");
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
		 	if (usuario != null) {
		 		Jogo jogo = usuario.getJogo();
		 		
				if (usuario.getTerritorios() != null) {
					jogoService.preparaTerritorios(jogo.getUsuarios(), territorioForm);
				}
		 		
				if (jogo != null) {
					view.addObject("usuarios", jogo.getUsuarios());
				}
		 	}
		 	
		 	view.addObject("turno", usuario.getJogo().getTurno());
			view.addObject("territorioForm", new TerritorioForm());
			
			boolean sessaoExiste = verificaSessao(request);
			
			if (sessaoExiste) {
				colocaUsuarioNaSessao(request, usuario, sessaoExiste);  
				return view;
			}
			
			view.addObject("erro", "A sessão de jogo expirou.");
		} catch (Exception e) {
			e.printStackTrace();
			view.addObject("erro", "Erro ao exibir terrotórios.");
		}
		
		return view;
	}
	
	@RequestMapping(value = "/preDistribuicao",method = RequestMethod.POST )
	public String preDistribuicao(@RequestParam(value="turno", required=true) String turno,
									     @ModelAttribute("territorioForm") TerritorioForm territorioForm,
									     HttpServletRequest request) {
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
		 	if (usuario != null) {
		 		Jogo jogo = usuario.getJogo();
		 		
				if (usuario.getTerritorios() != null) {
					jogoService.preparaTerritorios(jogo.getUsuarios(), territorioForm);
				}
		 		
				if (jogo != null) {
					jogoService.passaTurno(jogo);
					
					if (jogo.getTurno() > 1) {
						jogoService.coletaExercitoParaUsuarioDaVez(jogo.getUsuarioDaVez());
						
						if (jogo.getUsuarioDaVez() != null && !jogo.getUsuarioDaVez().getJogadorHumano()) {
							jogoService.distribuiExercitoParaInimigoDaVez(jogo.getUsuarioDaVez());
						}
					}
				}
		 	}
		 	
		 	boolean sessaoExiste = verificaSessao(request);
			
			if (sessaoExiste) {
				colocaUsuarioNaSessao(request, usuario, sessaoExiste);  
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:distribuiExercito";
	}
	
	@RequestMapping(value = "/distribuiExercito",method = RequestMethod.GET )
	public ModelAndView distribuiExercito(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("tabuleiro");
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
	 	if (usuario != null) {
	 		Jogo jogo = usuario.getJogo();
	 		
			if (jogo != null) {
				view.addObject("usuarios", jogo.getUsuarios());
			}
	 	}
	 	
	 	view.addObject("turno", usuario.getJogo().getTurno());
		view.addObject("territorioForm", new TerritorioForm());
		
		boolean sessaoExiste = verificaSessao(request);
		
		if (sessaoExiste) {
			colocaUsuarioNaSessao(request, usuario, sessaoExiste);  
			return view;
		}
		
		view.addObject("erro", "A sessão de jogo expirou.");
		
		return view;
	}
	
	@RequestMapping(value = "/preparaJogo",method = RequestMethod.GET )
	public String preparaJogo(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			 	
	 	if (usuario != null) {
			Jogo jogo = usuario.getJogo();
			
			if (jogo != null) {
				jogo.setTurno(1);
				
				if (usuario.getTerritorios() == null || (usuario.getTerritorios().isEmpty() && usuario.getAindaNoJogo())) {
					jogoService.preparaJogadores(jogo.getUsuarios());
					jogoService.distribuiExercitoInimigo(jogo.getUsuarios());
				}
	 		}
	 	}
		
	 	request.getSession().setMaxInactiveInterval(60 * 60 * 6);
		request.getSession().setAttribute("usuario", usuario);
		
		return "redirect:distribuiExercito";
	}
	
}