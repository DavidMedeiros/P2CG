package Usuario;
import java.util.ArrayList;
import java.util.List;

import Jogo.Jogo;
import Mananger.DadoInvalidoException;
import Mananger.EntradaException;
import Mananger.StringInvalidaException;

public abstract class Usuario {
	
	private String nome;
	private String login;
	private double precoTotalArrecadado;
	
	protected List<Jogo> listaDeJogosComprados;
	protected int x2p;
	protected double dinheiro;
	protected double precoComDesconto;
	
	/**
	 * Construtor da classe Usuario.
	 * 
	 * @param nome
	 *            Nome do usuario.
	 * @param login
	 *            Login do usuario.
	 */

	public Usuario(String nome, String login) throws EntradaException {
		
		if (nome.equals("") || nome == null){
			throw new StringInvalidaException();
		}
		
		if (login.equals("") || login == null){
			throw new StringInvalidaException();
		}
		
		this.nome = nome;
		this.login = login;
		this.listaDeJogosComprados = new ArrayList<Jogo>();
		this.x2p = 0;
		this.dinheiro = 0;	
		this.precoTotalArrecadado = 0;
	}
	
	/**
	 * Metodo responsavel por comprar um jogo, dependendo do tipo de usuario, o
	 * jogo terah desconto.
	 * 
	 * @param jogo
	 *            Jogo que serah comprado.
	 * @return Retorna o preço do desconto que serah dado.
	 */
	
	public abstract double comprarJogo(Jogo jogo);
	
	/**
	 * Metodo responsavel por recompensar um determinado de tipo de usuario,
	 * dependendo das suas jogabilidades.
	 * 
	 * @param nomeDoJogo
	 *            Nome do Jogo.
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @throws EntradaException 
	 */
	
	public abstract void recompensar(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException;
	
	/**
	 * Metodo responsavel por punir um determinado de tipo de usuario,
	 * dependendo das suas jogabilidades.
	 * 
	 * @param nomeDoJogo
	 *            Nome do Jogo.
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @throws EntradaException 
	 */
	
	public abstract void punir(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException ;
	
	/**
	 * Metodo responsavel por adicionar um valor em dinheiro para o usuario.
	 * 
	 * @param valor
	 *            Valor do dinheiro que será adicionado.
	 */
	
	public void adicionaDinheiro(double valor) throws EntradaException {
		if (valor < 0) {
			throw new DadoInvalidoException();
		}
		
		this.dinheiro += valor;
	}
	
	/**
	 * Metodo para retorno do nome de usuario.
	 * 
	 * @return String - Nome do usuario. 
	 */
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo para retorno do login de usuario.
	 * 
	 * @return String - Login do usuario.
	 */
	
	public String getLogin() {
		return login;
	}
	
	/**
	 * Metodo para retorno do dinheiro de usuario.
	 * 
	 * @return double - Dinheiro do usuario.
	 */	

	public double getDinheiro() {
		return dinheiro;
	}
	
	/**
	 * Metodo para retorno do x2p de usuario.
	 * 
	 * @return int - x2p do usuario.
	 */
	
	public int getX2p() {
		return x2p;
	}
	
	/**
	 * Metodo para retorno da lista de jogos comprados pelo usuario.
	 * 
	 * @return List<Jogo> - Jogos comprados pelo usuario.
	 */
	
	public List<Jogo> getListaDeJogosComprados() {
		return listaDeJogosComprados;
	}
	
	/**
	 * Metodo para retorno do total arrecadado pelo usuario.
	 * 
	 * @return double - Total arrecadado pelo usuario.
	 */
	
	public double getPrecoTotalArrecadado() {
		return precoTotalArrecadado;
	}

	/**
	 * Metodo para atribuir o total arrecadado pelo usuario.
	 */
	
	public void setPrecoTotalArrecadado(double precoArrecadado) {
		this.precoTotalArrecadado += precoArrecadado;
	}

	/**
	 * Metodo para atribuir a lista de jogos comprados de usuario.
	 */
	
	public void setListaDeJogosComprados(List<Jogo> listaDeJogosComprados) {

		this.listaDeJogosComprados = listaDeJogosComprados;
	}

	/**
	 * Metodo para atribuir a quantidade de x2p do usuario.
	 */
	
	public void setX2p(int x2p) {
		this.x2p = x2p;
	}
	
	/**
	 * Metodo para atribuir o dinheiro do usuario.
	 */
	
	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}
}