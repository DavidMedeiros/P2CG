/* 114211306 - David de Medeiros Souza: LAB 5- Turma 3 */

package Mananger;

public class UsuarioNaoEncontradoException extends LogicaException {
	public UsuarioNaoEncontradoException(){
		super("O usuario procurado nao foi encontrado.");
	}
}