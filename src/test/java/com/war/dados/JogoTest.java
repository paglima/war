package com.war.dados;

import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:test-context.xml")
public class JogoTest extends TestCase {
	
	Jogo jogo;
	Usuario usuario = mock(Usuario.class);
	
	@Before
    public void iniciaObjetos() {
		jogo = new Jogo(TipoDeJogo.SOLO.getTipo(), new Usuario(), 0, "Teste");
		usuario.setJogo(jogo);
		jogo.getUsuarios().add(usuario);
	}

	@Test
	public void testa_turno_do_inimigo() {	
		when(usuario.getJogadorHumano()).thenReturn(Boolean.FALSE);
		when(usuario.getTurnoDaJogada()).thenReturn(Boolean.TRUE);
		
		assertEquals(jogo.turnoDoInimigo(), true);
	}
	
}