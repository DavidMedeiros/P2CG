package Usuario;
import Jogo.Jogabilidade;
import Jogo.Jogo;
import Mananger.DadoInvalidoException;
import Mananger.EntradaException;
import Mananger.StringInvalidaException;


public class Veterano extends Usuario {

	public Veterano(String nome, String login) throws EntradaException {
		super(nome, login);

	}

	@Override
	public void recompensar(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException {
		
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
			}
		}
		//exception o jogo nao faz parte
	}
	
	@Override
	public void punir(String nomeDoJogo, int score, boolean zerouOJogo) throws EntradaException {
		
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
				
			}
		}
		//exception o jogo nao faz parte
	}
	
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
	
	@Override
	public String toString() {
		return "Veterano";
	}

}