package br.com.loteria.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.loteria.jogo.Gerador;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.simulacao.Simulacao;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException, IOException {

	simulacao();
	//	 gerarJogos(1,true);
	//	 estisticasUltimoSorteio();
	//	estatisticasUltimosSorteios(50, true);

//	List<Jogo> jogos = new ArrayList<Jogo>();
//	jogos.add(new Jogo(Arrays.asList(1, 4, 5, 9, 10, 11, 12, 13, 14, 16, 18, 19, 21, 23, 25)));	
//	jogos.add(new Jogo(Arrays.asList(2, 3, 5, 8, 9, 10, 13, 16, 17, 18, 19, 20, 22, 24, 25)));	
//	jogos.add(new Jogo(Arrays.asList(1, 3, 4, 6, 8, 11, 12, 13, 14, 15, 17, 18, 21, 23, 25)));	
//	jogos.add(new Jogo(Arrays.asList(1, 2, 5, 7, 9, 10, 11, 12, 13, 15, 18, 19, 22, 23, 25)));	
//	jogos.add(new Jogo(Arrays.asList(1, 3, 6, 7, 8, 9, 10, 11, 14, 16, 17, 18, 22, 23, 25)));	
//	jogos.add(new Jogo(Arrays.asList(1, 2, 3, 4, 7, 9, 10, 11, 13, 14, 17, 19, 21, 22, 24)));	
//	consultaSorteio(jogos);

	}

	private static void consultaSorteio(List<Jogo> jogos) throws FileNotFoundException, IOException {

		Estatisticas estatisticas = new Estatisticas();
		List<Jogo> listaTodosJogos = estatisticas.lerTodosOsJogos();

		Jogo strUltimoSorteio = listaTodosJogos.get(listaTodosJogos.size() - 1);

		System.err.println("�ltimo sorteio:" + strUltimoSorteio.getJogo() + "\n");
		for (Jogo jogo : jogos) {

			Set<Integer> intersection = new HashSet<Integer>(strUltimoSorteio.getJogo());
			intersection.retainAll(jogo.getJogo());
			if (intersection.size() == 15) {
				System.err.println(jogo.getJogo() + ": " + intersection.size());
			} else {
				System.out.println(jogo.getJogo() + ": " + intersection.size());
			}

		}

		System.out.println("FIM");

	}

	private static void estatisticasUltimosSorteios(int i, Boolean geraArq) throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();

		estatisticas.estatisticasUltimosSorteioCSV(i, geraArq);

	}

	private static void estisticasUltimoSorteio() throws FileNotFoundException, IOException {
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.estatSorteio();

	}

	private static void gerarJogos(int i, Boolean geraArq) throws FileNotFoundException, IOException {
		Gerador gerador = new Gerador();

		gerador.gerarJogosCSV(i, geraArq);

	}

	public static void simulacao() throws FileNotFoundException, IOException {
		Simulacao simulacao = new Simulacao();
		simulacao.simularFiltrosTodosSorteios();
	}

}
