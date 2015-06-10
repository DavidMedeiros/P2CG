package Mananger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Jogo.Jogabilidade;
import Jogo.Jogo;
import Jogo.JogoFactory;
import Usuario.Noob;
import Usuario.Usuario;
import Usuario.Veterano;

public class Loja {
	
	private List<Usuario> listaDeUsuarios;
	private JogoFactory factory;
	//private double totalArrecadado;
	private double totalPrecoJogo;
		
	public Loja(){
		listaDeUsuarios = new ArrayList<Usuario>();
		factory = new JogoFactory();
		//totalArrecadado = 0;
		totalPrecoJogo = 0;
	}
	
	/**
	 * Metodo responsavel pelo upgrade do usuario. O Upgrade é realizado apenas
	 * quando o usuario é do tipo Noob e possue x2p maior ou igual a 1000.
	 * 
	 * @param loginDoUsuario
	 *            Login do usuario.
	 * @return um boolean é retornado para indicar se a operação foi realizada
	 *         com sucesso.
	 * @throws EntradaException, LogicaException 
	 */
	
	public boolean upgrade(String loginDoUsuario) throws EntradaException, LogicaException {
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getLogin().equals(loginDoUsuario)) {
				if (!(usuario.toString().equals("Veterano"))) {
					if (usuario.getX2p() >= 1000) {
						Usuario usuarioUp;
						usuarioUp = new Veterano(usuario.getNome(), usuario.getLogin());
						usuarioUp.setDinheiro(usuario.getDinheiro());
						usuarioUp.setListaDeJogosComprados(usuario.getListaDeJogosComprados());
						usuarioUp.setPrecoTotalArrecadado(usuario.getPrecoTotalArrecadado());
						usuarioUp.setX2p(usuario.getX2p());
						
						int indice = listaDeUsuarios.indexOf(usuario);
						
						this.listaDeUsuarios.remove(usuario);
						this.listaDeUsuarios.add(indice, usuarioUp);
						return true;
					}
					throw new X2PInsuficienteException();
				}
				throw new UpgradeVeteranoException();
			}
		} 
		throw new UsuarioNaoEncontradoException();
	}
	
	/**
	 * Metodo responsavel pelo downgrade do usuario. O Downgrade é realizado apenas
	 * quando o usuario é do tipo Veterano e possue x2p menor que 1000.
	 * 
	 * @param loginDoUsuario
	 *            Login do usuario.
	 * @return um boolean é retornado para indicar se a operação foi realizada
	 *         com sucesso.
	 * @throws EntradaException, LogicaException 
	 */
	
	public boolean downgrade(String loginDoUsuario) throws EntradaException, LogicaException  {
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getLogin().equals(loginDoUsuario)) {
				if(!(usuario.toString().equals("Noob"))){
					if (usuario.getX2p() < 1000) {
					
						Usuario usuarioDown = new Noob(usuario.getNome(), usuario.getLogin());
						
						usuarioDown.setDinheiro(usuario.getDinheiro());
						usuarioDown.setListaDeJogosComprados(usuario.getListaDeJogosComprados());
						usuarioDown.setPrecoTotalArrecadado(usuario.getPrecoTotalArrecadado());
						usuarioDown.setX2p(usuario.getX2p());
						
						int indice = listaDeUsuarios.indexOf(usuario);
						
						this.listaDeUsuarios.remove(usuario);
						this.listaDeUsuarios.add(indice, usuarioDown);
						return true;
					}
					throw new X2PInsuficienteException();
				}
				throw new DowngradeNoobException();
			}
		}
		throw new UsuarioNaoEncontradoException();
	}
	
	/**
	 * Metodo utilizado para criar um usuario e adiciona-lo a lista de usuarios.
	 * 
	 * @param nome
	 *            Nome do usuario.
	 * @param login
	 *            login do usuario.
	 */
	
	public void criaUsuario(String nome, String login){
		try{
			Usuario usuario = new Noob(nome, login);
			listaDeUsuarios.add(usuario);
		} catch (EntradaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Metodo utilizado para criar um jogo do tipo que é especificado no
	 * parametro. Toda a criação do jogo ocorre no factory.
	 * 
	 * @param nome
	 *            Nome do jogo.
	 * @param preco
	 *            Preco do jogo.
	 * @param tipo
	 *            Tipo do jogo.
	 * @param jogabilidade
	 *            Lista de jogabilidades.
	 * @return retorna um jogo caso o mesmo tenha sido criado com sucesso. Caso
	 *         contrario, retorna null.
	 */
	
	public Jogo criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade){
		try{
			return factory.criaJogo(nome, preco, tipo, jogabilidade);
		} catch (EntradaException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Metodo utilizado para vender um jogo para determinado usuario.
	 * 
	 * @param usuario
	 *            Nome do usuario ao qual será vendido o jogo.
	 * @param jogo
	 *            Jogo que será vendido ao usuário.
	 */

	public void venderJogo(Usuario usuario, Jogo jogo){
		usuario.setPrecoTotalArrecadado(usuario.comprarJogo(jogo));
	}
	
	/**
	 * Metodo utilizado para adicionar dinheiro ao usuario.
	 * 
	 * @param usuario
	 *            Usuario que terá o dinheiro adicionado.
	 * @param valor
	 *            Valor que será adicionado ao dinheiro do usuário.
	 */
	
	public void adicionaDinheiro(Usuario usuario, double valor){
		try {
			usuario.adicionaDinheiro(valor);
		} catch (EntradaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo utilizado para imprimir as informações do P2CG
	 */
	
	public void imprimeInformacoes(){
		System.out.println("=== Central P2-CG ===");
		System.out.println("");
		
		for (Usuario usuario : listaDeUsuarios) {
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getNome());
			System.out.println("Jogador " + usuario.toString() + ": " + usuario.getX2p() + " x2p");
			System.out.println("Lista de Jogos:");
			
			for (Jogo jogo : usuario.getListaDeJogosComprados()) {
				System.out.println("+ " + jogo.getNome() + " - " + jogo.toString());
				System.out.println("==> Jogou " + jogo.getVezesJogadas() + " vez(es)");
				System.out.println("==> Zerou " + jogo.getVezesZeradas() + " vez(es)");
				System.out.println("==> Maior score: " + jogo.getMaiorScore());
				System.out.println("");
				totalPrecoJogo += jogo.getPreco();
			}
			
			System.out.println("Total de preco dos jogos: R$ " + totalPrecoJogo);
			System.out.println("--------------------------------------------");
			System.out.println("Total arrecadado com vendas de jogos: R$" + usuario.getPrecoTotalArrecadado());
			System.out.println("");
			
			totalPrecoJogo = 0;
		}
	}
	
	/**
	 * Metodo para retorno da lista de usuarios.
	 * 
	 * @return List<Usuario> - Lista de usuarios.
	 */
	
	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}	
}