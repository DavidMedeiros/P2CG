
public class Rpg extends Jogo {
	
	/**
	 * Metodo Construtor da Classe Rpg.
	 * 
	 * @param nome
	 *            Nome do Jogo.
	 * @param preco
	 *            Preço do Jogo.
	 */
	
	public Rpg(String nome, double preco) {
		super(nome, preco);
	}
	
	/**
	 * Metodo responsavel por jogar o jogo do tipo RPG, atualizando o maior
	 * score e a quantidade de vezes zeradas, se necessario. Além de atualizar a
	 * quantidade de vezes jogadas.
	 * 
	 * @param score
	 *            Score da jogada atual.
	 * @param zerouOJogo
	 *            Indicação se o usuario zerou o jogo.
	 * @return Retorna o calculo da pontuação x2p para o jogo do tipo RPG.
	 */
	
	@Override
	public int joga(int score, boolean zerouOJogo) {

		if (score > this.maiorScore)
			this.maiorScore = score;
		if (zerouOJogo)
			this.vezesZeradas += 1;
		this.vezesJogadas += 1;
		
		return (this.vezesJogadas * 10);
	}
	
	/**
	 * Metodo utilizado para indicar o tipo do jogo.
	 * 
	 * @return Retorna o nome da classe.
	 */
	
	@Override
	public String toString() {
		return "RPG";
	}
}