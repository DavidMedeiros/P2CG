package Testes;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Jogo.JogoFactory;
import Mananger.EntradaException;


public class JogoTest {

	@Test
	public void test() {
		JogoFactory factory = new JogoFactory();
		
		// Cria e Adiciona jogabilidades
		HashSet<Jogabilidade> jogabilidadesMario = new HashSet<Jogabilidade>();
		jogabilidadesMario.add(Jogabilidade.OFFLINE);

		
		HashSet<Jogabilidade> jogabilidadesTheStreets = new HashSet<Jogabilidade>();
		jogabilidadesTheStreets.add(Jogabilidade.MULTIPLAYER);
		
		HashSet<Jogabilidade> jogabilidadesMagicka = new HashSet<Jogabilidade>();
		jogabilidadesMagicka.add(Jogabilidade.OFFLINE);
		jogabilidadesMagicka.add(Jogabilidade.MULTIPLAYER);
			
		// Tenta criar jogo com tipo invalido
		try{
			factory.criaJogo("Mario", 50, "", jogabilidadesMario);
		} catch (EntradaException e) {
			Assert.assertEquals("A string eh invalida.", e.getMessage());
		}
		
		// Cria jogos
		
		// tenta criar jogo do tipo plataforma
		try{
			Jogo superMario = factory.criaJogo("Super Mario", 50, "Plataforma", jogabilidadesMario);
			
			// Verifica jogo criado
			Assert.assertEquals("Super Mario", superMario.getNome());
			Assert.assertEquals(50, superMario.getPreco(), 0);
			Assert.assertEquals("Plataforma", superMario.toString());
			Assert.assertEquals(jogabilidadesMario, superMario.getJogabilidades());
			Assert.assertEquals(0, superMario.getMaiorScore());
			Assert.assertEquals(0, superMario.getVezesZeradas());
			Assert.assertEquals(0, superMario.getVezesJogadas());
			
			// Verifica metodo joga
			Assert.assertEquals(0, superMario.joga(1000, false)); // Jogo Plataforma retorna quantidade vezes zeradas * 20
			Assert.assertEquals(1000, superMario.getMaiorScore());
			Assert.assertEquals(0, superMario.getVezesZeradas());
			Assert.assertEquals(1, superMario.getVezesJogadas());

			Assert.assertEquals(20, superMario.joga(100, true)); // Jogo Plataforma retorna quantidade vezes zeradas * 20
			Assert.assertEquals(1000, superMario.getMaiorScore());
			Assert.assertEquals(1, superMario.getVezesZeradas());
			Assert.assertEquals(2, superMario.getVezesJogadas());
			
			// Score abaixo de 0
			try{
				Assert.assertEquals(1, superMario.joga(-100, true));
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado invalido.", e.getMessage());
			}
			
		} catch (EntradaException e) {
			Assert.fail();
		}

		//tenta criar jogo do tipo luta
		try{
			Jogo theStreets = factory.criaJogo("The Streets", 100, "Luta", jogabilidadesTheStreets);
			
			// Verifica metodo joga
			Assert.assertEquals(1, theStreets.joga(1090, false)); // Jogo Luta retorna 1 x2p para cada 1000 no score max
			Assert.assertEquals(1090, theStreets.getMaiorScore());
			Assert.assertEquals(0, theStreets.getVezesZeradas());
			Assert.assertEquals(1, theStreets.getVezesJogadas());

			Assert.assertEquals(1, theStreets.joga(100, true)); // Jogo Luta retorna 1 x2p para cada 1000 no score max
			Assert.assertEquals(1090, theStreets.getMaiorScore());
			Assert.assertEquals(1, theStreets.getVezesZeradas());
			Assert.assertEquals(2, theStreets.getVezesJogadas());
			
			// Jogos de luta não podem ter score maximo acima de 100.000
			try{
				Assert.assertEquals(1, theStreets.joga(1000000, true));
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado invalido.", e.getMessage());
			}
			
			// Score abaixo de 0
			try{
				Assert.assertEquals(1, theStreets.joga(-100, true));
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado invalido.", e.getMessage());
			}
				
		} catch (EntradaException e) {
			Assert.fail();
		}
		
		//tenta criar jogo do tipo Rpg
		try{
			Jogo magicka = factory.criaJogo("Magicka", 25, "Rpg" , jogabilidadesMagicka);
			
			// Verifica metodo joga
			Assert.assertEquals(10, magicka.joga(199, true)); // Jogo Rpg retorna 10 x2p para cada vez jogada
			Assert.assertEquals(199, magicka.getMaiorScore());
			Assert.assertEquals(1, magicka.getVezesZeradas());
			Assert.assertEquals(1, magicka.getVezesJogadas());
			
			Assert.assertEquals(20, magicka.joga(299, true)); // Jogo Rpg retorna 10 x2p para cada vez jogada
			Assert.assertEquals(299, magicka.getMaiorScore());
			Assert.assertEquals(2, magicka.getVezesZeradas());
			Assert.assertEquals(2, magicka.getVezesJogadas());
			
			// Score abaixo de 0
			try{
				Assert.assertEquals(1, magicka.joga(-100, true));
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado invalido.", e.getMessage());
			}
			
		} catch (EntradaException e) {
			Assert.fail();
		}
	}

}