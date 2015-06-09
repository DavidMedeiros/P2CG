import java.util.HashSet;


public abstract class Jogo {
	
	private String nome;
	private double preco;
	protected int maiorScore;
	protected int vezesJogadas;
	protected int vezesZeradas;
	private HashSet<Jogabilidade> jogabilidades;
		
	public Jogo(String nome, double preco) {

		this.nome = nome;
		this.preco = preco;
		this.maiorScore = 0;
		this.vezesJogadas = 0;
		this.vezesZeradas = 0;	
		this.jogabilidades = new HashSet<Jogabilidade>();
	}
	
	public abstract int joga(int score, boolean zerouOJogo);
	

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public int getMaiorScore() {
		return maiorScore;
	}

	public int getVezesJogadas() {
		return vezesJogadas;
	}

	public int getVezesZeradas() {
		return vezesZeradas;
	}

	public HashSet<Jogabilidade> getJogabilidades() {
		return jogabilidades;
	}

	public void setJogabilidades(HashSet<Jogabilidade> jogabilidades) {
		this.jogabilidades = jogabilidades;
	}

}