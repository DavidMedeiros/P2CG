package Testes;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Mananger.Loja;
import Mananger.StringInvalidaException;
import Usuario.Usuario;

public class LojaTest {

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
	
		// Cria usuarios
		loja.criaUsuario("David Souza", "david.souza");
		loja.criaUsuario("Francisco Neto", "francisco.neto");
		
		Usuario david = loja.getListaDeUsuarios().get(0);
		Usuario neto = loja.getListaDeUsuarios().get(1);
		
		// Adiciona Dinheiro ao usuario
		loja.adicionaDinheiro(david, 1000);
		loja.adicionaDinheiro(neto, 1000);
		
		// Cria jogos
		Jogo superMario = loja.criaJogo("Super Mario", 50, "Plataforma", jogabilidadesMario);
		Jogo theSims = loja.criaJogo("The Sims", 100, "Plataforma", jogabilidadesTheSims);
		Jogo magicka = loja.criaJogo("Magicka", 25, "Rpg" , jogabilidadesMagicka);
		
		// Vende Jogos
		loja.venderJogo(david, superMario);
		loja.venderJogo(david, theSims);
		loja.venderJogo(neto, magicka);
		
		// Verifica se jogos foram vendidos
		Assert.assertTrue(david.getListaDeJogosComprados().contains(superMario));
		Assert.assertTrue(david.getListaDeJogosComprados().contains(theSims));
		Assert.assertTrue(neto.getListaDeJogosComprados().contains(magicka));
		
		// Verifica x2p - (Jogos vendidos * 10)
		Assert.assertEquals(1500, david.getX2p());
		Assert.assertEquals(250, neto.getX2p());
		
		// Tenta fazer upgrade
		try {
			loja.upgrade("david.souza");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
				
		Usuario davidveterano = loja.getListaDeUsuarios().get(0);
		Usuario netonoob = loja.getListaDeUsuarios().get(1);
		
		// Verifica que foi convertido com sucesso.
		Assert.assertEquals("Veterano", davidveterano.toString());
		
		// Tenta fazer upgrade novamente
		assertFalse(loja.upgrade("david.souza"));
				
		// Tenta fazer upgrade com x2p insuficiente
		assertFalse(loja.upgrade("francisco.neto"));
		
		// Tenta fazer upgrade de usuario que nao existe
		assertFalse(loja.upgrade("gustavo"));
				
		// Tenta fazer downgrade de um veterano com potuação nao compativel
		assertFalse(loja.downgrade("david.souza"));
		
		// Tenta fazer downgrade de um usuario que já é noob
		assertFalse(loja.downgrade("francisco.neto"));
		
		// Tenta fazer downgrade de usuario que nao existe
		assertFalse(loja.downgrade("gustavo"));
		
		// Imprime informaçoes do lojão 
		loja.imprimeInformacoes();
		
	}

}