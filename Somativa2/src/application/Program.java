package application;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import data.ActionMovie;
import data.Movie;

public class Program {
	public static void main(String[] args) {
		List<Movie> list = new ArrayList<>();
		System.out.println("Ola");

		String escolha;
		do {
			// Exibe o menu de opções para o usuário
			escolha = JOptionPane.showInputDialog(
					"CineMovies\n1.Adicionar Filme\n2.Exibir filmes\n3.Remover filme\n4.Gravar dados \n5.Recuperar dados \n0.SAIR");
			if (escolha != null) {
				switch (escolha) {
				case "1":
					// Opção para adicionar um filme
					String name = JOptionPane.showInputDialog("Nome do filme:");
					String nameDirector = JOptionPane.showInputDialog("Nome do diretor:");
					String genero = JOptionPane.showInputDialog("Gênero do filme:");
					String actionType = JOptionPane.showInputDialog("Tipo de ação:");
					ActionMovie actionMovie = new ActionMovie(name, nameDirector, genero, actionType);
					list.add(actionMovie);
					break;
				case "2":
					// Opção para exibir os filmes adicionados
					if (list.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Lista vazia");
					} else {
						StringBuilder filmes = new StringBuilder();
						for (Movie movie : list) {
							filmes.append(movie.toString()).append("\n");
						}
						JOptionPane.showMessageDialog(null, filmes.toString());
					}
					break;
				case "3":
					// Opção para remover um filme da lista
					if (list.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Lista vazia");
					} else {
						String nomeFilme = JOptionPane.showInputDialog("Digite o nome do filme a ser removido:");
						boolean removido = false;
						for (Movie movie : list) {
							if (movie.getName().equals(nomeFilme)) {
								list.remove(movie);
								JOptionPane.showMessageDialog(null, "Filme removido: " + movie.getName());
								removido = true;
								break;
							}
						}
						if (!removido) {
							JOptionPane.showMessageDialog(null, "Filme não encontrado na lista");
						}
					}
					break;
				case "4":
					// Opção para gravar os dados em um arquivo
					if (list.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Lista vazia");
					} else {
						persistirDados(list);
						JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
					}
					break;
				case "5":
					// Opção para recuperar os dados do arquivo
					List<Movie> filmesRecuperados = recuperarDados();
					if (filmesRecuperados.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhum dado recuperado");
					} else {
						StringBuilder filmes = new StringBuilder();
						for (Movie movie : filmesRecuperados) {
							filmes.append(movie.toString()).append("\n");
						}
						JOptionPane.showMessageDialog(null, filmes.toString());
					}
					break;
				case "0":
					JOptionPane.showMessageDialog(null, "Saindo do programa...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida");
					break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Saindo do programa...");
				break;
			}
		} while (!"0".equals(escolha));
	}

	private static void persistirDados(List<Movie> list) {
		String projectDir = System.getProperty("user.dir");
		String folderPath = Paths.get(projectDir, "arquivosbin").toString();
		String filePath = Paths.get(folderPath, "filmes.bin").toString();

		// Cria a pasta arquivosbin se não existir
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		try (FileOutputStream fos = new FileOutputStream(filePath);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			// Grava a lista de filmes em um arquivo binário
			oos.writeObject(list);
			System.out.println("Arquivo salvo com sucesso em: " + filePath);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro ao gravar dados: " + e.getMessage());
		}
	}

	private static List<Movie> recuperarDados() {
		String projectDir = System.getProperty("user.dir");
		String filePath = Paths.get(projectDir, "arquivosbin", "filmes.bin").toString();

		try (FileInputStream fis = new FileInputStream(filePath); ObjectInputStream ois = new ObjectInputStream(fis)) {
			// Recupera a lista de filmes do arquivo binário
			List<Movie> filmes = (List<Movie>) ois.readObject();
			System.out.println("Dados recuperados com sucesso de: " + filePath);
			return filmes;
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro ao ler dados: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Classe não encontrada: " + e.getMessage());
		}

		return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
	}
}
