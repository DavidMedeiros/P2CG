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
	
	public boolean upgrade(String loginDoUsuario) {
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getLogin().equals(loginDoUsuario)) {
				if (!(usuario.toString().equals("Veterano"))) {
					if (usuario.getX2p() >= 1000) {
						Usuario usuarioUp;
						try {
							usuarioUp = new Veterano(usuario.getNome(), usuario.getLogin());
							usuarioUp.setDinheiro(usuario.getDinheiro());
							usuarioUp.setListaDeJogosComprados(usuario.getListaDeJogosComprados());
							usuarioUp.setPrecoTotalArrecadado(usuario.getPrecoTotalArrecadado());
							usuarioUp.setX2p(usuario.getX2p());
							this.listaDeUsuarios.remove(usuario);
							this.listaDeUsuarios.add(usuarioUp);
							return true;
							
						} catch (EntradaException e) {
							System.out.println(e.getMessage());
						}
		
					}
					System.out.println("Usuario nao possue xp suficiente");
					return false;
					// usuario nao possue xp suficiente
				}
				System.out.println("Usuario ja é veterano");
				return false;
				// usuario ja é veterano
			}
		}
		System.out.println("usuario nao encontrado na lista");
		return false;
		//usuario com login nao encontrado na lista
	}
	
	public boolean downgrade(String loginDoUsuario) {
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getLogin().equals(loginDoUsuario)) {
				if(!(usuario.toString().equals("Noob"))){
					if (usuario.getX2p() < 1000) {
						Usuario usuarioDown = new Noob(usuario.getNome(), usuario.getLogin());
						
						usuarioDown.setDinheiro(usuario.getDinheiro());
						usuarioDown.setListaDeJogosComprados(usuario.getListaDeJogosComprados());
						usuarioDown.setPrecoTotalArrecadado(usuario.getPrecoTotalArrecadado());
						usuarioDown.setX2p(usuario.getX2p());
						
						this.listaDeUsuarios.remove(usuario);
						this.listaDeUsuarios.add(usuarioDown);
						return true;
					}
					System.out.println("USuario possue xp aima de 1000");
					return false;
					// usuario possue acima de 1000x2p
				}
				System.out.println("usuario ja eh noob");
				return false;
				// usuario já é noob
			}
		}
		System.out.println("usuario nao encontrado na lista");
		return false;
		// usuario na encontrado na lista
	}
	
	public void criaUsuario(String nome, String login){
		try{
			Usuario usuario = new Noob(nome, login);
			listaDeUsuarios.add(usuario);
		} catch (EntradaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public Jogo criaJogo(String nome, double preco, String tipo, HashSet<Jogabilidade> jogabilidade){
		// passar para a factory
		if (tipo.equals("Rpg")){
			return factory.criaJogoRpg(nome, preco, jogabilidade);
		}
		if (tipo.equals("Luta")){
			return factory.criaJogoLuta(nome, preco, jogabilidade);
		}
		if (tipo.equals("Plataforma")){
			return factory.criaJogoPlataforma(nome, preco, jogabilidade);
		}
		
		return null;
		
	}
	
	//Como que copia?
	public void venderJogo(Usuario usuario, Jogo jogo){
		usuario.setPrecoTotalArrecadado(usuario.comprarJogo(jogo));
		
	}
	
	public void adicionaDinheiro(Usuario usuario, double valor){
		try {
			usuario.adicionaDinheiro(valor);
		} catch (EntradaException e) {
			System.out.println(e.getMessage());

		}
	}
	
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

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}	
}