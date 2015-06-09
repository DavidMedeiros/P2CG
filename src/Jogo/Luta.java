package Jogo;

import Mananger.EntradaException;


public class Luta extends Jogo {

	/**
	 * Metodo Construtor da Classe Luta.
	 * 
	 * @param nome
	 *            Nome do Jogo.
	 * @param preco
	 *            Preço do Jogo.
	 * @throws EntradaException 
	 */
	
	public Luta(String nome, double preco) throws EntradaException {
		super(nome, preco);
	}

	/**
	 * Metodo responsavel por jogar o jogo do tipo Luta, atualizando se necessario, o maior
	 * score e a quantidade de vezes zeradas. Além de atualizar a
	 * quantidade de vezes jogadas.
	 * 
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @return Retorna o calculo da pontuação x2p para o jogo do tipo Luta.
	 */
	
	@Override
	public int joga(int score, boolean zerouOJogo) {

		if (score > this.maiorScore)
			this.maiorScore = score;
		if (zerouOJogo)
			this.vezesZeradas += 1;
		this.vezesJogadas += 1;
		
		return (this.maiorScore / 1000); //testr com valores quebrados
	}
	
	/**
	 * Metodo utilizado para indicar o tipo do jogo.
	 * 
	 * @return Retorna o nome da classe.
	 */	

	@Override
	public String toString() {
		return "Luta";
	}
}