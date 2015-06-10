package Jogo;
import java.util.HashSet;

import Mananger.DadoInvalidoException;
import Mananger.EntradaException;
import Mananger.StringInvalidaException;


public abstract class Jogo {
	
	private String nome;
	private double preco;
	protected int maiorScore;
	protected int vezesJogadas;
	protected int vezesZeradas;
	private HashSet<Jogabilidade> jogabilidades;
	
	/**
	 * Construtor da classe Jogo.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preço do jogo.
	 * @throws EntradaException
	 *             Para nomes de usuarios, vazios ou nulos é lançada um
	 *             excessão. Assim como para preços com valores inválidos.
	 */
	
	public Jogo(String nome, double preco) throws EntradaException {

		if (nome == null || nome.equals("")) {
			throw new StringInvalidaException();
		}
		
		if (preco < 0) {
			throw new DadoInvalidoException();
		}
		
		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.vezesJogadas = 0;
		this.vezesZeradas = 0;	
		this.jogabilidades = new HashSet<Jogabilidade>();
	}
	
	/**
	 * Metodo definido pelas classes filhas, responsavel por jogar o jogo,
	 * atualizando se necessario, o maior score e a quantidade de vezes zeradas.
	 * Além de atualizar a quantidade de vezes jogadas.
	 * 
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @return Retorna o calculo da pontuação x2p para o determinado tipo de
	 *         jogo que sobreescreve o método.
	 * @throws DadoInvalidoException
	 *             Uma excessão é lançada caso o score seja inválido, negativo,
	 *             ou acima de 100000 para a classe Luta.
	 */
	
	public abstract int joga(int score, boolean zerouOJogo) throws DadoInvalidoException;
	
	/**
	 * Metodo para retorno do nome do jogo.
	 * 
	 * @return String - Nome do jogo. 
	 */
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo para retorno do preço do jogo.
	 * 
	 * @return double - Preço do jogo. 
	 */
	
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Metodo para retorno do maior score do jogo.
	 * 
	 * @return int - Maior score do jogo. 
	 */
	
	public int getMaiorScore() {
		return maiorScore;
	}

	/**
	 * Metodo para retorno da quantidade de vezes jogadas.
	 * 
	 * @return int - Quantidade de vezes jogadas. 
	 */
	
	public int getVezesJogadas() {
		return vezesJogadas;
	}

	/**
	 * Metodo para retorno da quantidade de vezes zeradas.
	 * 
	 * @return int - Quantidade de vezes zeradas. 
	 */
	
	public int getVezesZeradas() {
		return vezesZeradas;
	}

	/**
	 * Metodo para retorno do conjunto de jogabilidades.
	 * 
	 * @return HashSet<Jogabilidade> - Cojunto de jogabilidades. 
	 */
	
	public HashSet<Jogabilidade> getJogabilidades() {
		return jogabilidades;
	}
	
	/**
	 * Metodo para alteração do conjunto de jogabilidades.
	 * 
	 * @return HashSet<Jogabilidade> - Cojunto de jogabilidades. 
	 */
	
	public void setJogabilidades(HashSet<Jogabilidade> jogabilidades) {
		this.jogabilidades = jogabilidades;
	}

}