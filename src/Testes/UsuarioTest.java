package Testes;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Mananger.EntradaException;
import Mananger.LogicaException;
import Mananger.Loja;
import Usuario.Noob;
import Usuario.Usuario;
import Usuario.Veterano;

public class UsuarioTest {

	@Test
	public void test() {
		Loja loja = new Loja();
		
		// Cria e Adiciona jogabilidades
		HashSet<Jogabilidade> jogabilidadesMario = new HashSet<Jogabilidade>();
		
		jogabilidadesMario.add(Jogabilidade.OFFLINE);
		jogabilidadesMario.add(Jogabilidade.MULTIPLAYER);
		jogabilidadesMario.add(Jogabilidade.COMPETITIVO);
		
		HashSet<Jogabilidade> jogabilidadesTheSims = new HashSet<Jogabilidade>();
		
		jogabilidadesTheSims.add(Jogabilidade.ONLINE);
		jogabilidadesTheSims.add(Jogabilidade.MULTIPLAYER);
		jogabilidadesTheSims.add(Jogabilidade.COMPETITIVO);
		jogabilidadesTheSims.add(Jogabilidade.COOPERATIVO);
		
		HashSet<Jogabilidade> jogabilidadesMagicka = new HashSet<Jogabilidade>();
		
		jogabilidadesMagicka.add(Jogabilidade.OFFLINE);
		jogabilidadesMagicka.add(Jogabilidade.MULTIPLAYER);
		
		// Cria jogos
		
		Jogo superMario = loja.criaJogo("Super Mario", 50, "Plataforma", jogabilidadesMario);
		Jogo theSims = loja.criaJogo("The Sims", 100, "Plataforma", jogabilidadesTheSims);
		Jogo magicka = loja.criaJogo("Magicka", 25, "Rpg" , jogabilidadesMagicka);
		
		Jogo superMarioPlus = loja.criaJogo("Super Mario Plus", 150, "Plataforma", jogabilidadesMario);
		Jogo theSimsPlus = loja.criaJogo("The Sims Plus", 200, "Plataforma", jogabilidadesTheSims);
		Jogo magickaPlus = loja.criaJogo("Magicka Plus", 125, "Rpg" , jogabilidadesMagicka);
		

		// Cria usuario invalido
		try {
			Usuario usuarioInvalido = new Noob(null, "");
			Assert.fail();
		} catch (EntradaException e) {
			Assert.assertEquals("A string eh invalida.", e.getMessage());
		}
		
		// Cria usuario do tipo noob
		try {
			Usuario usuarioNoob = new Noob("David Souza", "david.souza");
						
			// Testa Metodo adiciona dinheiro ao usuario com Valor negativo
			try{
				usuarioNoob.adicionaDinheiro(-10);
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado inválido", e.getMessage());
			}
			
			// Testa Metodo adiciona dinheiro ao usuario com Valor valido
			try{
				usuarioNoob.adicionaDinheiro(1000);
				Assert.assertEquals(1000, usuarioNoob.getDinheiro(), 0);
			} catch (EntradaException e) {
				Assert.fail();
			}
			
			// Testa metodo comprar jogo
			Assert.assertEquals(45, usuarioNoob.comprarJogo(superMario), 0);
			Assert.assertEquals(1, usuarioNoob.getListaDeJogosComprados().size());
			
			Assert.assertEquals(90, usuarioNoob.comprarJogo(theSims), 0);
			Assert.assertEquals(2, usuarioNoob.getListaDeJogosComprados().size());
			
			Assert.assertEquals(22.5, usuarioNoob.comprarJogo(magicka), 0);
			Assert.assertEquals(3, usuarioNoob.getListaDeJogosComprados().size());
			
			Assert.assertEquals(842.5, usuarioNoob.getDinheiro(), 0);
			
			// verifica variaveis de usuario
			Assert.assertEquals(0, usuarioNoob.getPrecoTotalArrecadado(), 0);
			Assert.assertEquals("david.souza", usuarioNoob.getLogin());
			Assert.assertEquals(1750, usuarioNoob.getX2p());
			
			// Verifica metetodo punir com nome do jogo invalido
			try {
				usuarioNoob.punir("", 100, false);
				Assert.fail();
			} catch (EntradaException | LogicaException e){
				Assert.assertEquals("A string eh invalida.", e.getMessage());
			}
			// verifica metodo punir
			usuarioNoob.punir("Super Mario", 100, false);
			Assert.assertEquals(1730, usuarioNoob.getX2p());
			
			usuarioNoob.punir("The Sims", 100, false);
			Assert.assertEquals(1650, usuarioNoob.getX2p());
			
			// Verifica metodo recompensar com nome do jogo invalido
			try {
				usuarioNoob.recompensar("", 100, false);
				Assert.fail();
			} catch (EntradaException e){
				Assert.assertEquals("A string eh invalida.", e.getMessage());
			}
			
			// verifica metodo recompensar
			usuarioNoob.recompensar("Super Mario", 100, true); 
			Assert.assertEquals(1710, usuarioNoob.getX2p()); // x2p ~ + 20 extras por ter zerado e + 40 pelas jogabilidades
			
			usuarioNoob.recompensar("Magicka", 100, false);
			Assert.assertEquals(1760, usuarioNoob.getX2p()); // x2p ~ + 10 extras pela jogada + 40 pelas jogabilidades
			
			
		} catch (EntradaException | LogicaException e) {
			Assert.fail();
		}
		
		// Cria usuario do tipo veterano
		try {
			Usuario usuarioVeterano = new Veterano("David Souza", "david.souza");
						
			// Testa Metodo adiciona dinheiro ao usuario com Valor negativo
			try{
				usuarioVeterano.adicionaDinheiro(-10);
				Assert.fail();
			} catch (EntradaException e) {
				Assert.assertEquals("Dado inválido", e.getMessage());
			}
			
			// Testa Metodo adiciona dinheiro ao usuario com Valor valido
			try{
				usuarioVeterano.adicionaDinheiro(1000);
				Assert.assertEquals(1000, usuarioVeterano.getDinheiro(), 0);
			} catch (EntradaException e) {
				Assert.fail();
			}
			
			// Testa metodo comprar jogo
			Assert.assertEquals(120, usuarioVeterano.comprarJogo(superMarioPlus), 0); // 20 % de desconto para o usuario veterano
			Assert.assertEquals(1, usuarioVeterano.getListaDeJogosComprados().size());
			
			Assert.assertEquals(160, usuarioVeterano.comprarJogo(theSimsPlus), 0);
			Assert.assertEquals(2, usuarioVeterano.getListaDeJogosComprados().size());
			
			Assert.assertEquals(100, usuarioVeterano.comprarJogo(magickaPlus), 0);
			Assert.assertEquals(3, usuarioVeterano.getListaDeJogosComprados().size());
			
			Assert.assertEquals(620, usuarioVeterano.getDinheiro(), 0);
			
			// verifica variaveis de usuario
			Assert.assertEquals(0, usuarioVeterano.getPrecoTotalArrecadado(), 0);
			Assert.assertEquals("david.souza", usuarioVeterano.getLogin());
			Assert.assertEquals(4750, usuarioVeterano.getX2p());
			
			// Verifica metetodo punir com nome do jogo invalido
			try {
				usuarioVeterano.punir("", 100, false);
				Assert.fail();
			} catch (EntradaException | LogicaException e){
				Assert.assertEquals("A string eh invalida.", e.getMessage());
			}
			// verifica metodo punir
			usuarioVeterano.punir("Super Mario Plus", 100, false);
			Assert.assertEquals(4730, usuarioVeterano.getX2p()); // -20 de puniçao pela jogabilidade
			
			usuarioVeterano.punir("The Sims Plus", 100, false);
			Assert.assertEquals(4690, usuarioVeterano.getX2p()); // - 40 de puniçao pela jogabilidade
			
			// Verifica metodo recompensar com nome do jogo invalido
			try {
				usuarioVeterano.recompensar("", 100, false);
				Assert.fail();
			} catch (EntradaException e){
				Assert.assertEquals("A string eh invalida.", e.getMessage());
			}
			
			// verifica metodo recompensar
			usuarioVeterano.recompensar("Super Mario Plus", 100, true); 
			Assert.assertEquals(4710, usuarioVeterano.getX2p()); // x2p ~ + 20 extras por ter zerado e + 0 pelas jogabilidades
			
			usuarioVeterano.recompensar("Magicka Plus", 100, false);
			Assert.assertEquals(4720, usuarioVeterano.getX2p()); // x2p ~ + 10 extras pela jogada + 0 pelas jogabilidades
			
			
		} catch (EntradaException | LogicaException e) {
			Assert.fail();
		}
	}

}