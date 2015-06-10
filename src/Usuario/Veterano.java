/* 114211306 - David de Medeiros Souza: LAB 5- Turma 3 */

package Usuario;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Mananger.DadoInvalidoException;
import Mananger.EntradaException;
import Mananger.JogoNaoEncontradoException;
import Mananger.LogicaException;
import Mananger.StringInvalidaException;

public class Veterano extends Usuario {
	
	/**
	 * Construtor da classe usuario.
	 * 
	 * @param nome
	 *            Nome do usuario.
	 * @param login
	 *            Login do usuario.
	 * 
	 * @throws EntradaException
	 *             Uma excessao sera lancada caso alguma entrada seja vazia.
	 */
	
	public Veterano(String nome, String login) throws EntradaException {
		super(nome, login);

	}

	/**
	 * Metodo responsavel por recompensar o usuario dependendo das suas jogabilidades.
	 * 
	 * @param nomeDoJogo
	 *            Nome do Jogo.
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indica se o usuario zerou o jogo.
	 * @throws EntradaException Uma excessao sera lancada caso uma entrada seja vazia.
	 * @throws LogicaException  Uma excessao sera lancada caso o jogo a ser recompensado nao seja encontrado.
	 */
	
	@Override
	public void recompensar(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException, LogicaException {
		
		if (nomeDoJogo.equals("") || nomeDoJogo == null) {
			throw new StringInvalidaException();
		}
		
		if (score < 0) {
			throw new DadoInvalidoException();
		}
		
		for (Jogo jogo : listaDeJogosComprados) {
			if (jogo.getNome().equals(nomeDoJogo)){
				this.x2p += jogo.joga(score, zerouOJogo);
				if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
					this.x2p += 10;
				}
				if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
					this.x2p += 20;
				}
				return;
			}
		}
		throw new JogoNaoEncontradoException();
	}
	
	/**
	 * Metodo responsavel por punir o usuario dependendo das suas jogabilidades.
	 * 
	 * @param nomeDoJogo
	 *            Nome do Jogo.
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @throws EntradaException Uma excessão será lançada caso uma entrada seja vazia.
	 * @throws LogicaException  Uma excessão será lançada caso o jogo a ser punido não seja encontrado.
	 */
	
	@Override
	public void punir(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException, LogicaException {
		
		if (nomeDoJogo.equals("") || nomeDoJogo == null) {
			throw new StringInvalidaException();
		}
		
		if (score < 0) {
			throw new DadoInvalidoException();
		}
		
		for (Jogo jogo : listaDeJogosComprados) {
			if (jogo.getNome().equals(nomeDoJogo)){
				this.x2p += jogo.joga(score, zerouOJogo);
				if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
					this.x2p -= 20;
				}
				if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
					this.x2p -= 20;
				}
				return;				
			}
		}
		throw new JogoNaoEncontradoException();
	}
	
	/**
	 * Metodo responsavel por comprar um jogo, 20% de desconto sera ofertado para o usuario do tipo Veterano.
	 * 
	 * @param jogo
	 *            Jogo que sera comprado.
	 * @return Retorna o preco do desconto que sera dado.
	 */
	
	@Override
	public double comprarJogo(Jogo jogo) {
		this.precoComDesconto = jogo.getPreco() - (jogo.getPreco() * 0.2);
		this.x2p += (jogo.getPreco() * 10);  
		
		if (this.dinheiro >= this.precoComDesconto) {
			this.listaDeJogosComprados.add(jogo);
			this.dinheiro -= this.precoComDesconto;
			
			return precoComDesconto;
		} 
		
		return 0;
	}
	
	/**
	 * Metodo utilizado para indicar o tipo de usuario.
	 * 
	 * @return Retorna o nome da classe.
	 */	
	
	@Override
	public String toString() {
		return "Veterano";
	}

}