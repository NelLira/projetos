package br.com.loteria.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.collections.ArrayStack;
import org.apache.commons.lang3.ArrayUtils;

import br.com.loteria.combinacoes.Combinacoes;
import br.com.loteria.jogo.Jogo;

public class Filtro {

	private List<Jogo> listaJogosCombinados;
	private List<Jogo> listaJogosCombinadosCompleto;
	private List<Jogo> listaJogosCheia;
	private List<Jogo> listaParaAnalise;
	private List<Jogo> listaPorFiltro;
	private List<Jogo> todosSorteios;
	private List<Jogo> listaCambinacoes;
	Estatisticas estatisticas;

	public Filtro() {

	}

	public void iniciaListas() {
		this.listaJogosCombinados = new ArrayList<Jogo>();
		this.listaParaAnalise = new ArrayList<Jogo>();
		this.listaPorFiltro = new ArrayList<Jogo>();
		this.estatisticas = new Estatisticas();
		this.estatisticas.iniciarListas();
		if (this.todosSorteios == null) {
			this.todosSorteios = new ArrayList<Jogo>();
		}
	}

	public void limpaListas() {
		this.listaJogosCombinados.clear();
		this.listaJogosCombinados = null;
		this.listaParaAnalise.clear();
		this.listaParaAnalise = null;
		this.listaPorFiltro.clear();
		this.listaPorFiltro = null;
		this.estatisticas.limparListas();
		this.estatisticas = null;
	}

	public void setaJogoListaTodosSorteios(Jogo jogo) {
		this.todosSorteios.add(jogo);
	}

	public void setaListaTodosSorteios(List<Jogo> listaJogo) {
		this.todosSorteios.addAll(listaJogo);
	}

	public List<Jogo> buscaListaTodosSorteios() {
		return this.todosSorteios;
	}

	public List<Jogo> filtrar() throws FileNotFoundException, IOException {
		this.listaJogosCheia = new ArrayList<Jogo>();
		listaJogosCheia.addAll(listaJogosCombinados);
		
		
		
		repetidosJogoAnterior(Arrays.asList(9));
		pares(Arrays.asList(5, 6, 7, 8, 9)); // 5a9; // {3=3, 4=21, 5=117, 6=339, 7=516, 8=429, 9=182,  10=41, 11=5}
		primos(Arrays.asList(4, 5, 6, 7)); // 4a7 {2=5, 3=76, 4=278, 5=520, 6=451, 7=257, 8=62, 9=4}
		quadrado(Arrays.asList(8, 9, 10, 11)); // 8a11 {6=2, 7=52,8=199, 9=450, 10=483, 11=261, 12=88,13=6}
		dezMelhores(Arrays.asList(4, 5, 6, 7, 8)); // 4a8 {2=2, 3=24, 4=140, 5=387, 6=567, 7=415, 8=165, 9=24, 10=3}
		multiplosDeTres(Arrays.asList(3, 4, 5, 6, 7)); // 3a7 {1=1, 2=33, 3=193, 4=430, 5=541,  6=317,7=111,8=14}
		sequenciaDeFibonacci(Arrays.asList(2, 3, 4, 5, 6));// 2a6 {1=8, 2=85, 3=330,4=567,5=453,6=182,7=15}
		
		linha(Arrays.asList(1, 2, 3, 4, 5),
			  Arrays.asList(1, 2, 3, 4, 5),
			  Arrays.asList(1, 2, 3, 4, 5),
			  Arrays.asList(1, 2, 3, 4, 5),
			  Arrays.asList(1, 2, 3, 4, 5));
		
		coluna(Arrays.asList(1, 2, 3, 4, 5),
			   Arrays.asList(1, 2, 3, 4, 5),
			   Arrays.asList(1, 2, 3, 4, 5),
			   Arrays.asList(1, 2, 3, 4, 5),
			   Arrays.asList(1, 2, 3, 4, 5));
		
		
		//	cantos();
		/*
		 * numerosImportantes(3, 8); //{1=2,2=9,3=80,4=276,5=492,6=498,7=230,8=49,9=3}
		 * 
		 * sequencia(); 
		 
		
		 posicoes();
		 soma();
 		 * cruz(3, 8);// {2=5, 3=75, 4=243, 5=493, 6=451, 7=218, 8=53,
		 * 9=3} x(3, 8); // {2=8, 3=65, 4=269, 5=465, 6=473, 7=213, 8=47, 9=1}
		 * dentro(3, 7); // {2=6, 3=89, 4=281, 5=522, 6=484, 7=212, 8=57, 9=2}
		 * grupo20a25(2, 5); // 2=187;3=524;4=592;5=277 **** grupo8a11(1, 4); //
		 * 180=4;612=3;571=2;261=1 
		 * verificaNumerosPrioritarios(); numerosAnteriores();
		 * PrimeiroESegundoGrupos(); gruposQuintos();
		 * 
		 * revisarJogosEliminados();
		 */
		return this.listaJogosCombinados;

	}

	private void repetidosJogoAnterior(List<Integer> lista) {

		listaJogosCombinados.clear();
		Jogo ultimosSorteados = todosSorteios.get(todosSorteios.size() - 1);

		listaJogosCombinadosCompleto.forEach(j -> {
			int interseciton = 0;
			for (Integer i : j.getJogo()) {
				if (ultimosSorteados.getJogo().contains(i)) {
					interseciton++;
				}
			}
			if (lista.contains(interseciton)) {
				listaJogosCombinados.add(j);
			}
			interseciton = 0;
		});

		System.out.println("Tamaho da lista depois das combinações ->" + listaJogosCombinados.size());
		
	}

	private void limpaListasAuxiliares() {

		listaParaAnalise.clear();
		listaPorFiltro.clear();
	}

	private void atualizaListaFiltrada() {

		listaJogosCombinados.clear();
		listaJogosCombinados.addAll(listaPorFiltro);
	}

	private void cruz(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosCruz());

		System.out.println("Tamanho da lista depois de filtrar a cruz -> " + listaJogosCombinados.size());

	}

	private void x(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosX());

		System.out.println("Tamanho da lista depois de filtrar a X -> " + listaJogosCombinados.size());

	}

	private void quadrado(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosQuadrado());

		System.out.println("Tamanho da lista depois de filtrar a quadrado -> " + listaJogosCombinados.size());

	}

	private void multiplosDeTres(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosMultiplosDeTres());

		System.out.println("Tamanho da lista depois de filtrar a multiplos de três -> " + listaJogosCombinados.size());

	}

	private void sequenciaDeFibonacci(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosSequenciaDeFibonacci());

		System.out.println("Tamanho da lista depois de filtrar a SequenciaDeFibonacci -> " + listaJogosCombinados.size());

	}

	private void primos(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosPrimos());

		System.out.println("Tamanho da lista depois de filtrar primos -> " + listaJogosCombinados.size());

	}

	private void dentro(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosDentro());

		System.out.println("Tamanho da lista depois de filtrar dentro -> " + listaJogosCombinados.size());

	}

	// private void pares(int maiorQue, int menorQue) {
	private void pares(List<Integer> lista) {
		// analisaNumeros(maiorQue, menorQue,
		// estatisticas.buscarNumerosPares());
		analisaNumeros(lista, estatisticas.buscarNumerosPares());
		System.out.println("Tamanho da lista depois de filtrar pares -> " + listaJogosCombinados.size());

	}

	private void dezMelhores(List<Integer> lista) throws FileNotFoundException, IOException {

		if (todosSorteios.size() < 15)
			return;

		analisaNumeros(lista, estatisticas.buscarDezMais(todosSorteios));

		System.out.println("Tamanho da lista depois de filtrar dez melhores -> " + listaJogosCombinados.size());

	}

	private void grupo20a25(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosGrupo20a25());

		System.out.println("Tamanho da lista depois de filtrar grupo 20 a 25 -> " + listaJogosCombinados.size());

	}

	private void grupo8a11(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosGrupo8a11());

		System.out.println("Tamanho da lista depois de filtrar grupo 8 a 11 -> " + listaJogosCombinados.size());

	}

	private void cantos() {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			int cont = 0;
			if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) || jogo.getJogo().contains(6)
					|| jogo.getJogo().contains(7)) {
				cont++;
			}

			if (jogo.getJogo().contains(4) || jogo.getJogo().contains(5) || jogo.getJogo().contains(9)
					|| jogo.getJogo().contains(10)) {
				cont++;
			}

			if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) || jogo.getJogo().contains(21)
					|| jogo.getJogo().contains(22)) {
				cont++;
			}

			if (jogo.getJogo().contains(19) || jogo.getJogo().contains(20) || jogo.getJogo().contains(24)
					|| jogo.getJogo().contains(25)) {
				cont++;
			}

			if (cont > 3) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);

			}

		}
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar cantos -> " + listaJogosCombinados.size());

	}

	private void linha(List<Integer> primeiraLinha, List<Integer> segundaLinha, List<Integer> terceiraLinha, List<Integer> quartaLinha, List<Integer> quintaLinha) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		Jogo strUltimoSorteio = todosSorteios.get(todosSorteios.size() - 1);
		
		
		for (Jogo jogo : listaParaAnalise) {
			
			int contPrimeiraLinha = retornaIntersecao(jogo, estatisticas.buscarNumerosLinha1());
			int contSegundaLinha = retornaIntersecao(jogo, estatisticas.buscarNumerosLinha2());
			int contTerceiraLinha = retornaIntersecao(jogo, estatisticas.buscarNumerosLinha3());
			int contQuartaLinha = retornaIntersecao(jogo, estatisticas.buscarNumerosLinha4());
			int contQuintaLinha = retornaIntersecao(jogo, estatisticas.buscarNumerosLinha5());
			
			int contPrimeiraLinhaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosLinha1());
			int contSegundaLinhaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosLinha2());
			int contTerceiraLinhaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosLinha3());
			int contQuartaLinhaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosLinha4());
			int contQuintaLinhaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosLinha5());
			
//
//			Set<Integer> primeiraLinhaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			primeiraLinhaAnterior.retainAll(estatisticas.buscarNumerosLinha1().getJogo());
//
//			Set<Integer> segundaLinhaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			segundaLinhaAnterior.retainAll(estatisticas.buscarNumerosLinha2().getJogo());
//
//			Set<Integer> terceiraLinhaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			terceiraLinhaAnterior.retainAll(estatisticas.buscarNumerosLinha3().getJogo());
//
//			Set<Integer> quartaLinhaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			quartaLinhaAnterior.retainAll(estatisticas.buscarNumerosLinha4().getJogo());
//
//			Set<Integer> quintaLinhaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			quintaLinhaAnterior.retainAll(estatisticas.buscarNumerosLinha5().getJogo());

			///////////////////////

//			Set<Integer> primeiraLinhaProximo = new HashSet<Integer>(jogo.getJogo());
//			primeiraLinhaProximo.retainAll(estatisticas.buscarNumerosLinha1().getJogo());
//
//			Set<Integer> segundaLinhaProximo = new HashSet<Integer>(jogo.getJogo());
//			segundaLinhaProximo.retainAll(estatisticas.buscarNumerosLinha2().getJogo());
//
//			Set<Integer> terceiraLinhaProximo = new HashSet<Integer>(jogo.getJogo());
//			terceiraLinhaProximo.retainAll(estatisticas.buscarNumerosLinha3().getJogo());
//
//			Set<Integer> quartaLinhaProximo = new HashSet<Integer>(jogo.getJogo());
//			quartaLinhaProximo.retainAll(estatisticas.buscarNumerosLinha4().getJogo());
//
//			Set<Integer> quintaLinhaProximo = new HashSet<Integer>(jogo.getJogo());
//			quintaLinhaProximo.retainAll(estatisticas.buscarNumerosLinha5().getJogo());
//			
			
			if (contPrimeiraLinha == contPrimeiraLinhaJogoAnterior
					&& contSegundaLinha == contSegundaLinhaJogoAnterior
					&& contTerceiraLinha == contTerceiraLinhaJogoAnterior
					&& contQuartaLinha == contQuartaLinhaJogoAnterior
					&& contQuintaLinha == contQuintaLinhaJogoAnterior) {
				continue;
			}
//			if (primeiraLinhaAnterior.size() == primeiraLinhaProximo.size()
//					&& segundaLinhaAnterior.size() == segundaLinhaProximo.size()
//					&& terceiraLinhaAnterior.size() == terceiraLinhaProximo.size()
//					&& quartaLinhaAnterior.size() == quartaLinhaProximo.size()
//					&& quintaLinhaAnterior.size() == quintaLinhaProximo.size()) {
//				continue;
//			}
			

			if ((contPrimeiraLinha == 1 && contPrimeiraLinhaJogoAnterior == 1)
					|| (contPrimeiraLinha == 5 && contPrimeiraLinhaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contSegundaLinha == 1 && contSegundaLinhaJogoAnterior == 1)
					|| (contSegundaLinha == 5 && contSegundaLinhaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contTerceiraLinha == 1 && contTerceiraLinhaJogoAnterior == 1)
					|| (contTerceiraLinha == 5 && contTerceiraLinhaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contQuartaLinha == 1 && contQuartaLinhaJogoAnterior == 1)
					|| (contQuartaLinha == 5 && contQuartaLinhaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contQuintaLinha == 1 && contQuintaLinhaJogoAnterior == 1)
					|| (contQuintaLinha == 5 && contQuintaLinhaJogoAnterior == 5)) {
				continue;
			}

//			if ((primeiraLinhaAnterior.size() == 1 && primeiraLinhaProximo.size() == 1)
//					|| (primeiraLinhaAnterior.size() == 5 && primeiraLinhaProximo.size() == 5)) {
//				continue;
//			}

//			if ((segundaLinhaAnterior.size() == 1 && segundaLinhaProximo.size() == 1)
//					|| (segundaLinhaAnterior.size() == 5 && segundaLinhaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((terceiraLinhaAnterior.size() == 1 && terceiraLinhaProximo.size() == 1)
//					|| (terceiraLinhaAnterior.size() == 5 && terceiraLinhaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((quartaLinhaAnterior.size() == 1 && quartaLinhaProximo.size() == 1)
//					|| (quartaLinhaAnterior.size() == 5 && quartaLinhaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((quintaLinhaAnterior.size() == 1 && quintaLinhaProximo.size() == 1)
//					|| (quintaLinhaAnterior.size() == 5 && quintaLinhaProximo.size() == 5)) {
//				continue;
//			}
//
			
			
			if (!primeiraLinha.contains(contPrimeiraLinha) || !segundaLinha.contains(contSegundaLinha) || !terceiraLinha.contains(contTerceiraLinha) || !quartaLinha.contains(contQuartaLinha) || !quintaLinha.contains(contQuintaLinha) ) {
				continue;
			}
			
//			if (primeiraLinhaProximo.size() == 0 || segundaLinhaProximo.size() == 0 || terceiraLinhaProximo.size() == 0
//					|| quartaLinhaProximo.size() == 0 || quintaLinhaProximo.size() == 0) {
//				continue;
//			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

			// int cont = 0;
			// if (jogo.getJogo().contains(1) || jogo.getJogo().contains(2) ||
			// jogo.getJogo().contains(3)
			// || jogo.getJogo().contains(4) || jogo.getJogo().contains(5)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(6) || jogo.getJogo().contains(7) ||
			// jogo.getJogo().contains(8)
			// || jogo.getJogo().contains(9) || jogo.getJogo().contains(10)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(11) || jogo.getJogo().contains(12) ||
			// jogo.getJogo().contains(13)
			// || jogo.getJogo().contains(14) || jogo.getJogo().contains(15)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(16) || jogo.getJogo().contains(17) ||
			// jogo.getJogo().contains(18)
			// || jogo.getJogo().contains(19) || jogo.getJogo().contains(20)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(21) || jogo.getJogo().contains(22) ||
			// jogo.getJogo().contains(23)
			// || jogo.getJogo().contains(24) || jogo.getJogo().contains(25)) {
			// cont++;
			// }
			//
			// if (cont > 4)
			//
			// {
			// jogo.somaQuantidadeFiltros();
			// listaPorFiltro.add(jogo);
			//
			// }

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar linhas -> " + listaJogosCombinados.size());

	}

	private void coluna(List<Integer> primeiraColuna, List<Integer> segundaColuna, List<Integer> terceiraColuna, List<Integer> quartaColuna, List<Integer> quintaColuna) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		Jogo strUltimoSorteio = todosSorteios.get(todosSorteios.size() - 1);
		for (Jogo jogo : listaParaAnalise) {

			int contPrimeiraColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna1());
			int contSegundaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna2());
			int contTerceiraColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna3());
			int contQuartaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna4());
			int contQuintaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna5());
			
			int contPrimeiraColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna1());
			int contSegundaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna2());
			int contTerceiraColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna3());
			int contQuartaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna4());
			int contQuintaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna5());
			
//
//			Set<Integer> primeiraColunaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			primeiraColunaAnterior.retainAll(estatisticas.buscarNumerosColuna1().getJogo());
//
//			Set<Integer> segundaColunaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			segundaColunaAnterior.retainAll(estatisticas.buscarNumerosColuna2().getJogo());
//
//			Set<Integer> terceiraColunaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			terceiraColunaAnterior.retainAll(estatisticas.buscarNumerosColuna3().getJogo());
//
//			Set<Integer> quartaColunaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			quartaColunaAnterior.retainAll(estatisticas.buscarNumerosColuna4().getJogo());
//
//			Set<Integer> quintaColunaAnterior = new HashSet<Integer>(strUltimoSorteio.getJogo());
//			quintaColunaAnterior.retainAll(estatisticas.buscarNumerosColuna5().getJogo());

			///////////////////////

//			Set<Integer> primeiraColunaProximo = new HashSet<Integer>(jogo.getJogo());
//			primeiraColunaProximo.retainAll(estatisticas.buscarNumerosColuna1().getJogo());
//
//			Set<Integer> segundaColunaProximo = new HashSet<Integer>(jogo.getJogo());
//			segundaColunaProximo.retainAll(estatisticas.buscarNumerosColuna2().getJogo());
//
//			Set<Integer> terceiraColunaProximo = new HashSet<Integer>(jogo.getJogo());
//			terceiraColunaProximo.retainAll(estatisticas.buscarNumerosColuna3().getJogo());
//
//			Set<Integer> quartaColunaProximo = new HashSet<Integer>(jogo.getJogo());
//			quartaColunaProximo.retainAll(estatisticas.buscarNumerosColuna4().getJogo());
//
//			Set<Integer> quintaColunaProximo = new HashSet<Integer>(jogo.getJogo());
//			quintaColunaProximo.retainAll(estatisticas.buscarNumerosColuna5().getJogo());
//			
			
			if (contPrimeiraColuna == contPrimeiraColunaJogoAnterior
					&& contSegundaColuna == contSegundaColunaJogoAnterior
					&& contTerceiraColuna == contTerceiraColunaJogoAnterior
					&& contQuartaColuna == contQuartaColunaJogoAnterior
					&& contQuintaColuna == contQuintaColunaJogoAnterior) {
				continue;
			}
//			if (primeiraColunaAnterior.size() == primeiraColunaProximo.size()
//					&& segundaColunaAnterior.size() == segundaColunaProximo.size()
//					&& terceiraColunaAnterior.size() == terceiraColunaProximo.size()
//					&& quartaColunaAnterior.size() == quartaColunaProximo.size()
//					&& quintaColunaAnterior.size() == quintaColunaProximo.size()) {
//				continue;
//			}
			

			if ((contPrimeiraColuna == 1 && contPrimeiraColunaJogoAnterior == 1)
					|| (contPrimeiraColuna == 5 && contPrimeiraColunaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contSegundaColuna == 1 && contSegundaColunaJogoAnterior == 1)
					|| (contSegundaColuna == 5 && contSegundaColunaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contTerceiraColuna == 1 && contTerceiraColunaJogoAnterior == 1)
					|| (contTerceiraColuna == 5 && contTerceiraColunaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contQuartaColuna == 1 && contQuartaColunaJogoAnterior == 1)
					|| (contQuartaColuna == 5 && contQuartaColunaJogoAnterior == 5)) {
				continue;
			}
			
			if ((contQuintaColuna == 1 && contQuintaColunaJogoAnterior == 1)
					|| (contQuintaColuna == 5 && contQuintaColunaJogoAnterior == 5)) {
				continue;
			}

//			if ((primeiraColunaAnterior.size() == 1 && primeiraColunaProximo.size() == 1)
//					|| (primeiraColunaAnterior.size() == 5 && primeiraColunaProximo.size() == 5)) {
//				continue;
//			}

//			if ((segundaColunaAnterior.size() == 1 && segundaColunaProximo.size() == 1)
//					|| (segundaColunaAnterior.size() == 5 && segundaColunaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((terceiraColunaAnterior.size() == 1 && terceiraColunaProximo.size() == 1)
//					|| (terceiraColunaAnterior.size() == 5 && terceiraColunaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((quartaColunaAnterior.size() == 1 && quartaColunaProximo.size() == 1)
//					|| (quartaColunaAnterior.size() == 5 && quartaColunaProximo.size() == 5)) {
//				continue;
//			}
//
//			if ((quintaColunaAnterior.size() == 1 && quintaColunaProximo.size() == 1)
//					|| (quintaColunaAnterior.size() == 5 && quintaColunaProximo.size() == 5)) {
//				continue;
//			}
//
			
			
			if (!primeiraColuna.contains(contPrimeiraColuna) || !segundaColuna.contains(contSegundaColuna) || !terceiraColuna.contains(contTerceiraColuna) || !quartaColuna.contains(contQuartaColuna) || !quintaColuna.contains(contQuintaColuna) ) {
				continue;
			}
			
//			if (primeiraColunaProximo.size() == 0 || segundaColunaProximo.size() == 0 || terceiraColunaProximo.size() == 0
//					|| quartaColunaProximo.size() == 0 || quintaColunaProximo.size() == 0) {
//				continue;
//			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

			// int cont = 0;
			// if (jogo.getJogo().contains(1) || jogo.getJogo().contains(6) ||
			// jogo.getJogo().contains(11)
			// || jogo.getJogo().contains(16) || jogo.getJogo().contains(21)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(2) || jogo.getJogo().contains(7) ||
			// jogo.getJogo().contains(12)
			// || jogo.getJogo().contains(17) || jogo.getJogo().contains(22)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(3) || jogo.getJogo().contains(8) ||
			// jogo.getJogo().contains(13)
			// || jogo.getJogo().contains(18) || jogo.getJogo().contains(23)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(4) || jogo.getJogo().contains(9) ||
			// jogo.getJogo().contains(14)
			// || jogo.getJogo().contains(19) || jogo.getJogo().contains(24)) {
			// cont++;
			// }
			//
			// if (jogo.getJogo().contains(5) || jogo.getJogo().contains(10) ||
			// jogo.getJogo().contains(15)
			// || jogo.getJogo().contains(20) || jogo.getJogo().contains(25)) {
			// cont++;
			// }
			//
			// if (cont > 4)
			//
			// {
			// jogo.somaQuantidadeFiltros();
			// listaPorFiltro.add(jogo);
			//
			// }

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar colunas -> " + listaJogosCombinados.size());

	}

	private void lista(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosImportantes());

		System.out.println("Tamanho da lista depois de filtrar numeros importantes -> " + listaJogosCombinados.size());

	}

	private void soma() {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		Integer soma = 0;

		for (Jogo jogo : listaParaAnalise) {
			for (Integer numero : jogo.getJogo()) {
				soma = soma + numero;
			}
			// if (soma > 135 && soma < 248) {
			if (soma > 163 && soma < 223) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			soma = 0;
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar as somas -> " + listaJogosCombinados.size());

	}

	private void posicoes() {

		/*
		 * posição 1 [1,2,3] posição 2 [2 a 5] posição 3 [3 a 7] posição 4 [4 a
		 * 9] posição 5 [5 a 11] posição 6 [6 a 13] posição 7 [8 a 15] posição 8
		 * [9 a 17] posição 9 [11 a 18] posição 10 [13 a 19] posição 11 [15 a
		 * 21] posição 12 [17 a 22] posição 13 [19 a 23] posição 14 [21 a 24]
		 * posição 15 [23 a 25]
		 */

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			if ((jogo.getJogo().get(0) < 4)
					// && (jogo.getJogo().get(1) > 1 && jogo.getJogo().get(1) <
					// 6)
					// && (jogo.getJogo().get(2) > 2 && jogo.getJogo().get(2) <
					// 8)
					// && (jogo.getJogo().get(3) > 3 && jogo.getJogo().get(3) <
					// 10)
					// && (jogo.getJogo().get(4) > 4 && jogo.getJogo().get(4) <
					// 12)
					// && (jogo.getJogo().get(5) > 5 && jogo.getJogo().get(5) <
					// 14)
					// && (jogo.getJogo().get(6) > 7 && jogo.getJogo().get(6) <
					// 16)
					// && (jogo.getJogo().get(7) > 8 && jogo.getJogo().get(7) <
					// 18)
					// && (jogo.getJogo().get(8) > 10 && jogo.getJogo().get(8) <
					// 19)
					// && (jogo.getJogo().get(9) > 12 && jogo.getJogo().get(9) <
					// 20)
					// && (jogo.getJogo().get(10) > 14 && jogo.getJogo().get(10)
					// < 22)
					// && (jogo.getJogo().get(11) > 16 && jogo.getJogo().get(11)
					// < 23)
					// && (jogo.getJogo().get(12) > 18 && jogo.getJogo().get(12)
					// < 24)
					// && (jogo.getJogo().get(13) > 20 && jogo.getJogo().get(13)
					// < 25)
					&& (jogo.getJogo().get(14) > 22 && jogo.getJogo().get(14) < 26))

			{

				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);

			}

		}
		atualizaListaFiltrada();
		System.out.println(
				"Tamamho da lista depois de filtrar as posições dos números -> " + listaJogosCombinados.size());

	}

	private void verificaNumerosPrioritarios() {

		if (todosSorteios.size() < 10) {
			return;
		}

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		Estatisticas estatisticas = new Estatisticas();

		List<Integer> numerosPrioritarios = estatisticas.numerosPrioritarios(todosSorteios);

		int cont = 0;

		for (Jogo jogo : listaParaAnalise) {
			cont = 0;

			for (Integer integer : numerosPrioritarios) {
				if (jogo.getJogo().contains(integer)) {
					cont++;
				}

				if (numerosPrioritarios.size() > 3) {
					if (cont > 1) {

						jogo.somaQuantidadeFiltros();
						listaPorFiltro.add(jogo);
						break;
					}
				} else {
					if (cont > 0) {

						jogo.somaQuantidadeFiltros();
						listaPorFiltro.add(jogo);
						break;
					}
				}

			}

		}

		atualizaListaFiltrada();
		System.out.println(
				"Tamanho da lista depois de filtrar os números prioritários -> " + listaJogosCombinados.size());

	}

	private void numerosAnteriores() {
		if (todosSorteios.size() > 8) {

			limpaListasAuxiliares();
			listaParaAnalise.addAll(listaJogosCombinados);

			Jogo strUltimoSorteio = todosSorteios.get(todosSorteios.size() - 1);
			Jogo strPenUltimoSorteio = todosSorteios.get(todosSorteios.size() - 2);
			Jogo strNoveSorteiosAtras = todosSorteios.get(todosSorteios.size() - 9);

			Jogo strNumerosNaoSorteados = strUltimoSorteio.numerosNaoSorteados(strUltimoSorteio);

			Set<Integer> differenceUltimoPenUltimo = new HashSet<Integer>(strPenUltimoSorteio.getJogo());
			differenceUltimoPenUltimo.removeAll(strUltimoSorteio.getJogo());

			Set<Integer> intersectionNoveJogosAtras = new HashSet<Integer>(strNoveSorteiosAtras.getJogo());
			intersectionNoveJogosAtras.retainAll(differenceUltimoPenUltimo);

			int intContDif = 0;
			int intContInt = 0;
			for (Jogo jogo : listaParaAnalise) {

				for (Integer numero : differenceUltimoPenUltimo) {
					if (jogo.getJogo().contains(numero)) {
						intContDif++;
					}

				}

				for (Integer numero : intersectionNoveJogosAtras) {
					if (jogo.getJogo().contains(numero)) {
						intContInt++;
					}

				}

				if (intContDif > 1 && intContInt > 0) {
					jogo.somaQuantidadeFiltros();
					listaPorFiltro.add(jogo);
				}

				intContDif = 0;
				intContInt = 0;
			}

			atualizaListaFiltrada();
			System.out.println(
					"Tamanho da lista depois de filtrar os números anteriores -> " + listaJogosCombinados.size());
		}

	}

	private void gruposQuintos() {

		if (todosSorteios.size() < 8) {
			return;
		}

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		int qTDJogosAnalisados = 8;

		Integer primeiroQuinto = 0;
		Integer segundoQuinto = 0;
		Integer terceiroQuinto = 0;
		Integer quartoQuinto = 0;
		Integer quintoQuinto = 0;

		TreeMap<Integer, Integer> mapaQuintos = new TreeMap<Integer, Integer>();

		for (int posicao = todosSorteios.size() - 1; posicao > todosSorteios.size()
				- (qTDJogosAnalisados + 1); posicao--) {

			for (Integer numero : todosSorteios.get(posicao).getJogo()) {

				if (numero > 0 && numero < 6) {
					primeiroQuinto++;
				}

				if (numero > 5 && numero < 11) {
					segundoQuinto++;
				}

				if (numero > 10 && numero < 16) {
					terceiroQuinto++;
				}

				if (numero > 15 && numero < 21) {
					quartoQuinto++;
				}

				if (numero > 20 && numero < 26) {
					quintoQuinto++;
				}

			}
		}

		mapaQuintos.put(1, primeiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(2, segundoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(3, terceiroQuinto / qTDJogosAnalisados);
		mapaQuintos.put(4, quartoQuinto / qTDJogosAnalisados);
		mapaQuintos.put(5, quintoQuinto / qTDJogosAnalisados);

		for (Jogo jogo : listaParaAnalise) {

			int cont = 0;
			int contOcorreciaGrupos = 0;
			if (jogo.getJogo().contains(1)) {
				cont++;
			}
			if (jogo.getJogo().contains(2)) {
				cont++;
			}
			if (jogo.getJogo().contains(3)) {
				cont++;
			}
			if (jogo.getJogo().contains(4)) {
				cont++;
			}
			if (jogo.getJogo().contains(5)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(1).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(6)) {
				cont++;
			}
			if (jogo.getJogo().contains(7)) {
				cont++;
			}
			if (jogo.getJogo().contains(8)) {
				cont++;
			}
			if (jogo.getJogo().contains(9)) {
				cont++;
			}
			if (jogo.getJogo().contains(10)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(2).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(11)) {
				cont++;
			}
			if (jogo.getJogo().contains(12)) {
				cont++;
			}
			if (jogo.getJogo().contains(13)) {
				cont++;
			}
			if (jogo.getJogo().contains(14)) {
				cont++;
			}
			if (jogo.getJogo().contains(15)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(3).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(16)) {
				cont++;
			}
			if (jogo.getJogo().contains(17)) {
				cont++;
			}
			if (jogo.getJogo().contains(18)) {
				cont++;
			}
			if (jogo.getJogo().contains(19)) {
				cont++;
			}
			if (jogo.getJogo().contains(20)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(4).intValue()) {
				contOcorreciaGrupos++;
			}

			cont = 0;
			if (jogo.getJogo().contains(21)) {
				cont++;
			}
			if (jogo.getJogo().contains(22)) {
				cont++;
			}
			if (jogo.getJogo().contains(23)) {
				cont++;
			}
			if (jogo.getJogo().contains(24)) {
				cont++;
			}
			if (jogo.getJogo().contains(25)) {
				cont++;
			}
			if (cont >= mapaQuintos.get(5).intValue()) {
				contOcorreciaGrupos++;
			}

			if (contOcorreciaGrupos > 3) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			contOcorreciaGrupos = 0;
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar grupos quintos -> " + listaJogosCombinados.size());

	}

	private void PrimeiroESegundoGrupos() throws FileNotFoundException, IOException {

		if (todosSorteios.size() < 10) {
			return;
		}

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		Estatisticas estatisticas = new Estatisticas();

		List<Integer> numeros = new ArrayList<Integer>(estatisticas.estatisticasJogos(todosSorteios).keySet());
		Jogo primeiroGrupo = new Jogo();
		Jogo segundoGrupo = new Jogo();
		int contNumeros = 1;
		for (Integer integer : numeros) {

			if (contNumeros < 13) {
				primeiroGrupo.getJogo().add(integer);
			} else {
				segundoGrupo.getJogo().add(integer);
			}

			contNumeros++;
		}

		for (Jogo jogo : listaParaAnalise) {
			Set<Integer> intersectionPrimeiroGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimeiroGrupo.retainAll(primeiroGrupo.getJogo());
			int indexPrimeiroGrupo = intersectionPrimeiroGrupo.size();
			Set<Integer> intersectionSegundoGrupo = new HashSet<Integer>(jogo.getJogo());
			intersectionSegundoGrupo.retainAll(primeiroGrupo.getJogo());
			int indexSegundoGrupo = intersectionSegundoGrupo.size();

			if ((indexPrimeiroGrupo > 6 && indexPrimeiroGrupo < 12)
					&& (indexSegundoGrupo > 3 && indexSegundoGrupo < 9)) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
		}

		atualizaListaFiltrada();
		System.out.println(
				"Tamanho da lista depois de filtrar o primeiro e o segundo grupo ->" + listaJogosCombinados.size());

	}

	private void sequencia() {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		int cont = 0;

		for (Jogo jogo : listaParaAnalise) {

			if

			((jogo.getJogo().get(0) == 1 && jogo.getJogo().get(6) == 7)
					|| (jogo.getJogo().get(0) == 2 && jogo.getJogo().get(6) == 8)
					|| (jogo.getJogo().get(0) == 3 && jogo.getJogo().get(6) == 9)
					|| (jogo.getJogo().get(0) == 4 && jogo.getJogo().get(6) == 10)
					|| (jogo.getJogo().get(0) == 5 && jogo.getJogo().get(6) == 11)
					|| (jogo.getJogo().get(0) == 6 && jogo.getJogo().get(6) == 12)
					|| (jogo.getJogo().get(0) == 7 && jogo.getJogo().get(6) == 13)
					|| (jogo.getJogo().get(0) == 8 && jogo.getJogo().get(6) == 14)
					|| (jogo.getJogo().get(0) == 9 && jogo.getJogo().get(6) == 15)
					|| (jogo.getJogo().get(0) == 10 && jogo.getJogo().get(6) == 16)
					|| (jogo.getJogo().get(0) == 11 && jogo.getJogo().get(6) == 17)
					|| (jogo.getJogo().get(0) == 12 && jogo.getJogo().get(6) == 18)
					|| (jogo.getJogo().get(0) == 13 && jogo.getJogo().get(6) == 19)
					|| (jogo.getJogo().get(0) == 14 && jogo.getJogo().get(6) == 120)
					|| (jogo.getJogo().get(0) == 15 && jogo.getJogo().get(6) == 21)
					|| (jogo.getJogo().get(0) == 16 && jogo.getJogo().get(6) == 22)
					|| (jogo.getJogo().get(0) == 17 && jogo.getJogo().get(6) == 23)
					|| (jogo.getJogo().get(0) == 18 && jogo.getJogo().get(6) == 24)
					|| (jogo.getJogo().get(0) == 19 && jogo.getJogo().get(6) == 25)) {
				cont++;
			}

			if

			((jogo.getJogo().get(1) == 2 && jogo.getJogo().get(7) == 8)
					|| (jogo.getJogo().get(1) == 3 && jogo.getJogo().get(7) == 9)
					|| (jogo.getJogo().get(1) == 4 && jogo.getJogo().get(7) == 10)
					|| (jogo.getJogo().get(1) == 5 && jogo.getJogo().get(7) == 11)
					|| (jogo.getJogo().get(1) == 6 && jogo.getJogo().get(7) == 12)
					|| (jogo.getJogo().get(1) == 7 && jogo.getJogo().get(7) == 13)
					|| (jogo.getJogo().get(1) == 8 && jogo.getJogo().get(7) == 14)
					|| (jogo.getJogo().get(1) == 9 && jogo.getJogo().get(7) == 15)
					|| (jogo.getJogo().get(1) == 10 && jogo.getJogo().get(7) == 16)
					|| (jogo.getJogo().get(1) == 11 && jogo.getJogo().get(7) == 17)
					|| (jogo.getJogo().get(1) == 12 && jogo.getJogo().get(7) == 18)
					|| (jogo.getJogo().get(1) == 13 && jogo.getJogo().get(7) == 19)
					|| (jogo.getJogo().get(1) == 14 && jogo.getJogo().get(7) == 20)
					|| (jogo.getJogo().get(1) == 15 && jogo.getJogo().get(7) == 21)
					|| (jogo.getJogo().get(1) == 16 && jogo.getJogo().get(7) == 22)
					|| (jogo.getJogo().get(1) == 17 && jogo.getJogo().get(7) == 23)
					|| (jogo.getJogo().get(1) == 18 && jogo.getJogo().get(7) == 24)
					|| (jogo.getJogo().get(1) == 19 && jogo.getJogo().get(7) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(2) == 3 && jogo.getJogo().get(8) == 9)
					|| (jogo.getJogo().get(2) == 4 && jogo.getJogo().get(8) == 10)
					|| (jogo.getJogo().get(2) == 5 && jogo.getJogo().get(8) == 11)
					|| (jogo.getJogo().get(2) == 6 && jogo.getJogo().get(8) == 12)
					|| (jogo.getJogo().get(2) == 7 && jogo.getJogo().get(8) == 13)
					|| (jogo.getJogo().get(2) == 8 && jogo.getJogo().get(8) == 14)
					|| (jogo.getJogo().get(2) == 9 && jogo.getJogo().get(8) == 15)
					|| (jogo.getJogo().get(2) == 10 && jogo.getJogo().get(8) == 16)
					|| (jogo.getJogo().get(2) == 11 && jogo.getJogo().get(8) == 17)
					|| (jogo.getJogo().get(2) == 12 && jogo.getJogo().get(8) == 18)
					|| (jogo.getJogo().get(2) == 13 && jogo.getJogo().get(8) == 19)
					|| (jogo.getJogo().get(2) == 14 && jogo.getJogo().get(8) == 20)
					|| (jogo.getJogo().get(2) == 15 && jogo.getJogo().get(8) == 21)
					|| (jogo.getJogo().get(2) == 16 && jogo.getJogo().get(8) == 22)
					|| (jogo.getJogo().get(2) == 17 && jogo.getJogo().get(8) == 23)
					|| (jogo.getJogo().get(2) == 18 && jogo.getJogo().get(8) == 24)
					|| (jogo.getJogo().get(2) == 19 && jogo.getJogo().get(8) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(3) == 4 && jogo.getJogo().get(9) == 10)
					|| (jogo.getJogo().get(3) == 5 && jogo.getJogo().get(9) == 11)
					|| (jogo.getJogo().get(3) == 6 && jogo.getJogo().get(9) == 12)
					|| (jogo.getJogo().get(3) == 7 && jogo.getJogo().get(9) == 13)
					|| (jogo.getJogo().get(3) == 8 && jogo.getJogo().get(9) == 14)
					|| (jogo.getJogo().get(3) == 9 && jogo.getJogo().get(9) == 15)
					|| (jogo.getJogo().get(3) == 10 && jogo.getJogo().get(9) == 16)
					|| (jogo.getJogo().get(3) == 11 && jogo.getJogo().get(9) == 17)
					|| (jogo.getJogo().get(3) == 12 && jogo.getJogo().get(9) == 18)
					|| (jogo.getJogo().get(3) == 13 && jogo.getJogo().get(9) == 19)
					|| (jogo.getJogo().get(3) == 14 && jogo.getJogo().get(9) == 20)
					|| (jogo.getJogo().get(3) == 15 && jogo.getJogo().get(9) == 21)
					|| (jogo.getJogo().get(3) == 16 && jogo.getJogo().get(9) == 22)
					|| (jogo.getJogo().get(3) == 17 && jogo.getJogo().get(9) == 23)
					|| (jogo.getJogo().get(3) == 18 && jogo.getJogo().get(9) == 24)
					|| (jogo.getJogo().get(3) == 19 && jogo.getJogo().get(9) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(4) == 5 && jogo.getJogo().get(10) == 11)
					|| (jogo.getJogo().get(4) == 6 && jogo.getJogo().get(10) == 12)
					|| (jogo.getJogo().get(4) == 7 && jogo.getJogo().get(10) == 13)
					|| (jogo.getJogo().get(4) == 8 && jogo.getJogo().get(10) == 14)
					|| (jogo.getJogo().get(4) == 9 && jogo.getJogo().get(10) == 15)
					|| (jogo.getJogo().get(4) == 10 && jogo.getJogo().get(10) == 16)
					|| (jogo.getJogo().get(4) == 11 && jogo.getJogo().get(10) == 17)
					|| (jogo.getJogo().get(4) == 12 && jogo.getJogo().get(10) == 18)
					|| (jogo.getJogo().get(4) == 13 && jogo.getJogo().get(10) == 19)
					|| (jogo.getJogo().get(4) == 14 && jogo.getJogo().get(10) == 20)
					|| (jogo.getJogo().get(4) == 15 && jogo.getJogo().get(10) == 21)
					|| (jogo.getJogo().get(4) == 16 && jogo.getJogo().get(10) == 22)
					|| (jogo.getJogo().get(4) == 17 && jogo.getJogo().get(10) == 23)
					|| (jogo.getJogo().get(4) == 18 && jogo.getJogo().get(10) == 24)
					|| (jogo.getJogo().get(4) == 19 && jogo.getJogo().get(10) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(5) == 6 && jogo.getJogo().get(11) == 12)
					|| (jogo.getJogo().get(5) == 7 && jogo.getJogo().get(11) == 13)
					|| (jogo.getJogo().get(5) == 8 && jogo.getJogo().get(11) == 14)
					|| (jogo.getJogo().get(5) == 9 && jogo.getJogo().get(11) == 15)
					|| (jogo.getJogo().get(5) == 10 && jogo.getJogo().get(11) == 16)
					|| (jogo.getJogo().get(5) == 11 && jogo.getJogo().get(11) == 17)
					|| (jogo.getJogo().get(5) == 12 && jogo.getJogo().get(11) == 18)
					|| (jogo.getJogo().get(5) == 13 && jogo.getJogo().get(11) == 19)
					|| (jogo.getJogo().get(5) == 14 && jogo.getJogo().get(11) == 20)
					|| (jogo.getJogo().get(5) == 15 && jogo.getJogo().get(11) == 21)
					|| (jogo.getJogo().get(5) == 16 && jogo.getJogo().get(11) == 22)
					|| (jogo.getJogo().get(5) == 17 && jogo.getJogo().get(11) == 23)
					|| (jogo.getJogo().get(5) == 18 && jogo.getJogo().get(11) == 24)
					|| (jogo.getJogo().get(5) == 19 && jogo.getJogo().get(11) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(6) == 7 && jogo.getJogo().get(12) == 13)
					|| (jogo.getJogo().get(6) == 8 && jogo.getJogo().get(12) == 14)
					|| (jogo.getJogo().get(6) == 9 && jogo.getJogo().get(12) == 15)
					|| (jogo.getJogo().get(6) == 10 && jogo.getJogo().get(12) == 16)
					|| (jogo.getJogo().get(6) == 11 && jogo.getJogo().get(12) == 17)
					|| (jogo.getJogo().get(6) == 12 && jogo.getJogo().get(12) == 18)
					|| (jogo.getJogo().get(6) == 13 && jogo.getJogo().get(12) == 19)
					|| (jogo.getJogo().get(6) == 14 && jogo.getJogo().get(12) == 20)
					|| (jogo.getJogo().get(6) == 15 && jogo.getJogo().get(12) == 21)
					|| (jogo.getJogo().get(6) == 16 && jogo.getJogo().get(12) == 22)
					|| (jogo.getJogo().get(6) == 17 && jogo.getJogo().get(12) == 23)
					|| (jogo.getJogo().get(6) == 18 && jogo.getJogo().get(12) == 24)
					|| (jogo.getJogo().get(6) == 19 && jogo.getJogo().get(12) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(7) == 8 && jogo.getJogo().get(13) == 14)
					|| (jogo.getJogo().get(7) == 9 && jogo.getJogo().get(13) == 15)
					|| (jogo.getJogo().get(7) == 10 && jogo.getJogo().get(13) == 16)
					|| (jogo.getJogo().get(7) == 11 && jogo.getJogo().get(13) == 17)
					|| (jogo.getJogo().get(7) == 12 && jogo.getJogo().get(13) == 18)
					|| (jogo.getJogo().get(7) == 13 && jogo.getJogo().get(13) == 19)
					|| (jogo.getJogo().get(7) == 14 && jogo.getJogo().get(13) == 20)
					|| (jogo.getJogo().get(7) == 15 && jogo.getJogo().get(13) == 21)
					|| (jogo.getJogo().get(7) == 16 && jogo.getJogo().get(13) == 22)
					|| (jogo.getJogo().get(7) == 17 && jogo.getJogo().get(13) == 23)
					|| (jogo.getJogo().get(7) == 18 && jogo.getJogo().get(13) == 24)
					|| (jogo.getJogo().get(7) == 19 && jogo.getJogo().get(13) == 25))

			{
				cont++;
			}

			if

			((jogo.getJogo().get(8) == 9 && jogo.getJogo().get(14) == 15)
					|| (jogo.getJogo().get(8) == 10 && jogo.getJogo().get(14) == 16)
					|| (jogo.getJogo().get(8) == 11 && jogo.getJogo().get(14) == 17)
					|| (jogo.getJogo().get(8) == 12 && jogo.getJogo().get(14) == 18)
					|| (jogo.getJogo().get(8) == 13 && jogo.getJogo().get(14) == 19)
					|| (jogo.getJogo().get(8) == 14 && jogo.getJogo().get(14) == 20)
					|| (jogo.getJogo().get(8) == 15 && jogo.getJogo().get(14) == 21)
					|| (jogo.getJogo().get(8) == 16 && jogo.getJogo().get(14) == 22)
					|| (jogo.getJogo().get(8) == 17 && jogo.getJogo().get(14) == 23)
					|| (jogo.getJogo().get(8) == 18 && jogo.getJogo().get(14) == 24)
					|| (jogo.getJogo().get(8) == 19 && jogo.getJogo().get(14) == 25))

			{
				cont++;
			}

			if (cont < 1) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			cont = 0;
		}

		atualizaListaFiltrada();
		System.out.println("Tamamho da lista depois de filtrar as sequências... " + listaJogosCombinados.size());

	}

	private void revisarJogosEliminados() {

		int limite = 3;
		int quantidadeDeFiltros = 6;
		int cont = 0;

		if (todosSorteios.size() < 10) {
			limite--;
			quantidadeDeFiltros--;
			limite--;
			quantidadeDeFiltros--;
		}
		if (todosSorteios.size() < 9) {
			limite--;
			quantidadeDeFiltros--;
		}
		if (todosSorteios.size() < 8) {
			limite--;
			quantidadeDeFiltros--;
		}

		for (Jogo jogo : listaJogosCheia) {
			int intQtdFiltros = jogo.buscaQuantidadeFiltros();
			if (intQtdFiltros > limite && intQtdFiltros < quantidadeDeFiltros) {
				cont++;
				listaJogosCombinados.add(jogo);
			}

		}
		System.out.println("Tamaho da lista depois de revisor os jogos eliminados -> " + listaJogosCombinados.size()
				+ " somados: " + cont);

	}

	public List<Jogo> bucaListaJogosFiltrados() throws FileNotFoundException, IOException {
//		listaJogosCombinados.clear();
//
//		Jogo ultimosSorteados = todosSorteios.get(todosSorteios.size() - 1);
//
//		Jogo ultimosNaoSorteados = ultimosSorteados.numerosNaoSorteados(ultimosSorteados);
//		// Jogo todosNumeros = new Jogo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
//		// 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20, 21, 22, 23, 24, 25));
//		// Combinacoes combinacoes = new Combinacoes(todosNumeros.getJogo(),
//		// 15);
//		// List<Jogo> listAux = new ArrayList<Jogo>();
//		// listAux.addAll(combinacoes.geraListaCombinacoes());
//		/*
//		 * listaJogosCombinados .addAll(combinacoes.combinacoes( new
//		 * Combinacoes(ultimosSorteados.getJogo(), 9).geraListaCombinacoes(),
//		 * new Combinacoes(ultimosNaoSorteados.getJogo(),
//		 * 6).geraListaCombinacoes()));
//		 */
//
//		listaJogosCombinadosCompleto.forEach(j -> {
//			int interseciton = 0;
//			for (Integer i : j.getJogo()) {
//				if (ultimosSorteados.getJogo().contains(i)) {
//					interseciton++;
//				}
//			}
//			if (interseciton > 8 && interseciton < 10) {
//				listaJogosCombinados.add(j);
//			}
//			interseciton = 0;
//		});
//
//		System.out.println("Tamaho da lista depois das combinações ->" + listaJogosCombinados.size());

		return filtrar();

	}

	public List<Jogo> getListaJogosCombinadosCompleto() {
		return listaJogosCombinadosCompleto;
	}

	public void setListaJogosCombinadosCompleto(List<Jogo> listaJogosCombinadosCompleto) {
		this.listaJogosCombinadosCompleto = listaJogosCombinadosCompleto;
	}

	public static String bitprint(int u) {
		String s = "";
		for (int n = 0; u > 0; ++n, u >>= 1)
			if ((u & 1) > 0)
				s += n + " ";
		return s;
	}

	public static int bitcount(int u) {
		int n;
		for (n = 0; u > 0; ++n, u &= (u - 1))
			;// Turn the last set bit to a 0
		return n;
	}

	public static LinkedList<String> comb(int c, int n) {
		LinkedList<String> s = new LinkedList<String>();
		for (int u = 0; u < 1 << n; u++)
			if (bitcount(u) == c)
				s.push(bitprint(u));
		Collections.sort(s);
		return s;
	}

	public int retornaIntersecao_(Jogo a, Jogo b) {
		return a.getJogo().stream().filter(p1 -> b.getJogo().stream().anyMatch(p2 -> p2.equals(p1)))
				.collect(Collectors.toList()).size();
	}

	public int retornaIntersecao(Jogo a, Jogo b) {

		int cont = 0;
		for (Integer numero : b.getJogo()) {
			if (a.getJogo().contains(numero)) {
				cont++;
			}
		}
		return cont;
	}

	// private void analisaNumeros(int maiorQue, int menorQue, Jogo jogoFiltro)
	// {
	private void analisaNumeros(List<Integer> lista, Jogo jogoFiltro) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		listaParaAnalise.forEach(j -> {
			int cont = retornaIntersecao(j, jogoFiltro);
			if (lista.contains(cont)) {
				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);
			}
		}

		);

		//
		//
		// for (Jogo jogo : listaParaAnalise) {
		// int cont = retornaIntersecao(jogo, jogoFiltro);
		// //if (cont >= maiorQue && cont <= menorQue) {
		// if(lista.contains(cont)) {
		// jogo.somaQuantidadeFiltros();
		// listaPorFiltro.add(jogo);
		// }
		//
		// }
		atualizaListaFiltrada();
	}

	public List<Jogo> buscarListaCambinacao() {
		return listaCambinacoes;
	}

	public void setarListaCambinacao(List<Jogo> listaCambinacao) {
		this.listaCambinacoes = listaCambinacao;
	}

	public boolean repetido(Jogo jogo) throws FileNotFoundException, IOException {

		for (Jogo tj : todosSorteios) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(tj.getJogo());

			if (intersection.size() > 14) {
				return true;
			}
		}
		return false;
	}

}
