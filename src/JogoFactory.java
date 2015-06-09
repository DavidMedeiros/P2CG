import java.util.HashSet;

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
	
	public Jogo criaJogoRpg(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		Rpg jogoRpg = new Rpg(nome, preco);
		jogoRpg.setJogabilidades(jogabilidade);
		return jogoRpg;
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
	
	public Jogo criaJogoLuta(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		Luta jogoLuta = new Luta(nome, preco);
		jogoLuta.setJogabilidades(jogabilidade);
		return jogoLuta;
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
	
	public Jogo criaJogoPlataforma(String nome, double preco, HashSet<Jogabilidade> jogabilidade) {
		Plataforma jogoPlataforma = new Plataforma(nome, preco);
		jogoPlataforma.setJogabilidades(jogabilidade);
		return jogoPlataforma;
	}
}