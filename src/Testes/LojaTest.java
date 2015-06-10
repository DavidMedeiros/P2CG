/* 114211306 - David de Medeiros Souza: LAB 5- Turma 3 */

package Testes;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Mananger.Loja;
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
				
		// Verifica que foi convertido com sucesso.
		Assert.assertEquals("Veterano", davidveterano.toString());
		
		// Tenta fazer upgrade novamente
		try {
			loja.upgrade("david.souza");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("O usuario ja eh veterano.", e.getMessage());
		}
				
		// Tenta fazer upgrade com x2p insuficiente
		try {
			loja.upgrade("francisco.neto");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("A quantidade de x2p eh insuficiente para a mudanca de tipo.", e.getMessage());
		}
		
		// Tenta fazer upgrade de usuario que nao existe
		try {
			loja.upgrade("silvio.santos");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("O usuario procurado nao foi encontrado.", e.getMessage());
		}
				
		// Tenta fazer downgrade de um veterano com potuação nao compativel
		try {
			loja.downgrade("david.souza");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("A quantidade de x2p eh insuficiente para a mudanca de tipo.", e.getMessage());
		}
		
		// Tenta fazer downgrade de um usuario que já é noob
		try {
			loja.downgrade("francisco.neto");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("O usuario ja eh noob.", e.getMessage());
		}
		
		// Tenta fazer downgrade de usuario que nao existe
		try {
			loja.downgrade("silvio.santos");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("O usuario procurado nao foi encontrado.", e.getMessage());
		}
		
		// Imprime informaçoes do lojão 
		loja.imprimeInformacoes();
		
	}

}