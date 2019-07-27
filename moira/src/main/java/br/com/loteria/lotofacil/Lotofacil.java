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

public class Lotofacil {

	public static void main(String[] args) throws FileNotFoundException, IOException { 
		
	//  simulacao(); //simula��o com 9 -> 427 de 613 [19/06/2019]
	//	gerarJogos(200,true);
	//	estisticasUltimoSorteio();
	 	estatisticasUltimosSorteios(1, true);
	 	

//		List<Jogo> jogos = new ArrayList<Jogo>();
//		jogos.add(new Jogo(Arrays.asList(1, 3, 6, 7, 8, 10, 11, 13, 14, 18, 19, 20, 23, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 5, 7, 8, 10, 11, 15, 18, 19, 20, 21, 23, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 3, 5, 6, 8, 9, 10, 11, 12, 14, 18, 19, 20, 23, 24)));11
//		jogos.add(new Jogo(Arrays.asList(1, 3, 4, 8, 9, 10, 11, 14, 16, 17, 18, 19, 20, 23, 25)));11
//		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 10, 13, 14, 16, 17, 18, 21, 22, 23, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 3, 4, 6, 7, 9, 10, 13, 15, 16, 17, 18, 21, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 3, 4, 8, 10, 11, 14, 15, 16, 18, 19, 20, 23, 25)));11
//		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 6, 8, 9, 14, 15, 16, 17, 19, 20, 21, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 5, 8, 9, 11, 12, 13, 14, 15, 17, 19, 20, 22, 23, 24)));
//		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 5, 6, 8, 9, 12, 14, 15, 17, 19, 22, 23, 24)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 5, 6, 9, 13, 14, 15, 16, 17, 18, 20, 21, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 3, 6, 7, 8, 9, 14, 16, 17, 19, 20, 22, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(2, 3, 4, 6, 8, 9, 11, 12, 15, 17, 19, 21, 22, 24, 25)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 3, 5, 7, 9, 14, 15, 16, 17, 18, 20, 21, 22, 24)));
//		jogos.add(new Jogo(Arrays.asList(1, 2, 3, 4, 6, 8, 9, 11, 12, 15, 19, 21, 22, 24, 25)));
//		consultaSorteio(jogos);



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
