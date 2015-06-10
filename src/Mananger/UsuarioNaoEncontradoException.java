package Mananger;

public class UsuarioNaoEncontradoException extends LogicaException {
	public UsuarioNaoEncontradoException(){
		super("O usuario procurado nao foi encontrado.");
	}
}