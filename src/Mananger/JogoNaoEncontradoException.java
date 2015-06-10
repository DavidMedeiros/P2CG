/* 114211306 - David de Medeiros Souza: LAB 5- Turma 3 */

package Mananger;

public class JogoNaoEncontradoException extends LogicaException {
	public JogoNaoEncontradoException(){
		super("O jogo procurado nao foi encontrado.");
	}
}