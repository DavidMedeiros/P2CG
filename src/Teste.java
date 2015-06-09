import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;


public class Teste {

	@Test
	public void test() {
		
		HashSet<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();
		Loja loja = new Loja();
		jogabilidades.add(Jogabilidade.OFFLINE);
		jogabilidades.add(Jogabilidade.MULTIPLAYER);
		jogabilidades.add(Jogabilidade.ONLINE);
		jogabilidades.add(Jogabilidade.COMPETITIVO);
		jogabilidades.add(Jogabilidade.COOPERATIVO);
		
		
		loja.criaUsuario("David", "david.souza");
		Assert.assertEquals(1, loja.getListaDeUsuarios().size());
		loja.criaUsuario("Neto", "neto.neto");
		
		Usuario usuario = loja.getListaDeUsuarios().get(0);
		Usuario usuario1 = loja.getListaDeUsuarios().get(1);
		
		Jogo jogo1 = loja.criaJogo("Magicka", 25, "Rpg" , jogabilidades);
		Jogo jogo2 = loja.criaJogo("Mario", 25, "Plataforma" , jogabilidades);
		Jogo jogo3 = loja.criaJogo("HP", 25, "Plataforma" , jogabilidades);
		Jogo jogo4 = loja.criaJogo("Street", 25, "Luta" , jogabilidades);
		Jogo jogo5 = loja.criaJogo("Magicka", 25, "Rpg" , jogabilidades);
		
		Assert.assertEquals("Magicka", jogo1.getNome());
		Assert.assertEquals(25, jogo1.getPreco(), 0);
		Assert.assertEquals(0, jogo1.getMaiorScore());
		Assert.assertEquals(0, jogo1.getVezesJogadas());
		Assert.assertEquals(0, jogo1.getVezesZeradas());
		Assert.assertEquals(5, jogo1.getJogabilidades().size());
		Assert.assertEquals(0, usuario.getDinheiro(), 0);
		
		loja.adicionaDinheiro(usuario, 100);
		loja.adicionaDinheiro(usuario1, 100);
		
		Assert.assertEquals(100, usuario.getDinheiro(), 0);
		
		loja.venderJogo(usuario, jogo1);
		loja.venderJogo(usuario, jogo2);
		loja.venderJogo(usuario, jogo3);
		loja.venderJogo(usuario1, jogo4);
		loja.venderJogo(usuario1, jogo5);
		
		Assert.assertEquals(jogo1, usuario.getListaDeJogosComprados().get(0));
		Assert.assertEquals(55.0, usuario1.getDinheiro(), 0);
		
		usuario.recompensar("Magicka", 8, false);
		Assert.assertEquals(800, usuario.getX2p()); //750 compras + 10 (1 jogada RPG) + 30 recompensa pois é offline + 10 MULTIPLAYER = 800
		
		usuario.recompensar("Magicka", 1000, false);
		Assert.assertEquals(860, usuario.getX2p()); //800 ja tinha + 20 ( 2 jogada RPG) + 30 recompensa pois é offline + 10 MULTIPLAYER = 860
		
		usuario.punir("Magicka", 1000, false);
		Assert.assertEquals(810, usuario.getX2p()); //860 ja tinha + 30 ( 3 jogada RPG) - 10 (ONLINE) - 20 (COMPETITIVO) - 50 ( COOPERATIVO) = 810
		
		usuario.recompensar("Magicka", 1000, false);
		Assert.assertEquals(890, usuario.getX2p()); //810 ja tinha + 40 ( 4 jogada RPG) + 30 recompensa pois é offline + 10 MULTIPLAYER = 890
		
		usuario.recompensar("Magicka", 1000, false);
		Assert.assertEquals(980, usuario.getX2p()); //890 ja tinha + 50 ( 5 jogada RPG) + 30 recompensa pois é offline + 10 MULTIPLAYER = 980
		
		usuario.recompensar("Magicka", 1000, false);
		Assert.assertEquals(1080, usuario.getX2p()); //980 ja tinha + 60 ( 6 jogada RPG) + 30 recompensa pois é offline + 10 MULTIPLAYER = 860
		
		Assert.assertTrue(loja.upgrade("david.souza"));
		Assert.assertFalse(loja.upgrade("david.souza"));
		Assert.assertFalse(loja.downgrade("david.souza"));
		Assert.assertFalse(loja.upgrade("userinvalido"));
		Assert.assertFalse(loja.downgrade("userinvalido"));
		
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		loja.getListaDeUsuarios().get(1).punir("Magicka", 1000, false);
		
		
		
		
		loja.imprimeInformacoes();
		
		
	}

}