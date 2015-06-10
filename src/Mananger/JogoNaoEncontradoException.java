package Mananger;

public class JogoNaoEncontradoException extends LogicaException {
	public JogoNaoEncontradoException(){
		super("O jogo procurado nao foi encontrado.");
	}
}