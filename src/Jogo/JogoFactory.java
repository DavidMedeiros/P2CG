package Jogo;
import java.util.HashSet;

import Mananger.EntradaException;
import Mananger.StringInvalidaException;

public class JogoFactory {
	
	/**
	 * Metodo responsavel por criar um jogo do tipo RPG.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preço do jogo.
	 * @param jogabilidade
	 *            Lista de jogabilidades.
	 * @return Retorna um Jogo do tipo RPG.
	 */
	
	private Jogo criaJogoRpg(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		try{
			Rpg jogoRpg = new Rpg(nome, preco);
			jogoRpg.setJogabilidades(jogabilidade);
			return jogoRpg;
		} catch (EntradaException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Metodo responsavel por criar um jogo do tipo Luta.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preço do jogo.
	 * @param jogabilidade
	 *            Lista de jogabilidades.
	 * @return Retorna um Jogo do tipo Luta.
	 */
	
	private Jogo criaJogoLuta(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		try{
			Luta jogoLuta = new Luta(nome, preco);
			jogoLuta.setJogabilidades(jogabilidade);
			return jogoLuta;
		} catch (EntradaException e){
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Metodo responsavel por criar um jogo do tipo Plataforma.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preço do jogo.
	 * @param jogabilidade
	 *            Lista de jogabilidades.
	 * @return Retorna um Jogo do tipo Plataforma.
	 */
	
	private Jogo criaJogoPlataforma(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		try {
			Plataforma jogoPlataforma = new Plataforma(nome, preco);
			jogoPlataforma.setJogabilidades(jogabilidade);
			return jogoPlataforma;
		} catch (EntradaException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}

	/**
	 * Metodo responsavel por criar um jogo de um tipo passado como parametro.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preço do jogo.
	 * @param tipo
	 *            Tipo do jogo.
	 * @param jogabilidade
	 *            Lista de jogabilidades.
	 * @return Retorna um jogo do tipo que foi passado como parametro.
	 * @throws EntradaException
	 *             Uma excessão será lançada caso o tipo passado seja vazio.
	 */
	
	public Jogo criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade) throws EntradaException {
		
		if (tipo == null || tipo.equals("")){
			throw new StringInvalidaException();
		}
		
		if (tipo.equals("Rpg")){
			return criaJogoRpg(nome, preco, jogabilidade);
		}
		if (tipo.equals("Luta")){
			return criaJogoLuta(nome, preco, jogabilidade);
		}
		if (tipo.equals("Plataforma")){
			return criaJogoPlataforma(nome, preco, jogabilidade);
		}
		
		return null;
	}
}