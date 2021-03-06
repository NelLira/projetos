package br.com.loteria.lotofacil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.loteria.jogo.Jogo;

public class Filtro {

	private List<Integer> listaParametrosRepetidos;

	private List<Jogo> listaJogosCombinados;
	private List<Jogo> listaJogosCombinadosCompleto;
	private List<Jogo> listaJogosCheia;
	private List<Jogo> listaParaAnalise;
	private List<Jogo> listaPorFiltro;
	private List<Jogo> todosSorteios;
	private List<Jogo> listaCambinacoes;
	Estatisticas estatisticas;
	private int jogoAtual;

	public Filtro() {

	}

	public void setParametrosRepetidos(List<Integer> listaParametrosRepetidos) {
		this.listaParametrosRepetidos = listaParametrosRepetidos;
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
		
		//[9-> 1 em cada 2; 8-> 1 em cada 4 ;10-> 1 em cada 5]
				repetidosJogoAnterior(Arrays.asList(9));

				// [7 -> 1 em cada 3; 8 -> 1 em cada 4; 6-> 1 em cada 5]
				// 5a9; {3=3, 4=21, 5=117, 6=339, 7=516, 8=429, 9=182,  10=41, 11=5} 
				pares(Arrays.asList(6,7,8)); 

				// [5-> 1 em cada 3; 6-> 1 em cada 4 ;4-> 1 em cada 6; 7-> 1 em cada 7]
				// 4a7 {2=5, 3=76, 4=278, 5=520, 6=451, 7=257, 8=62, 9=4} 
				primos(Arrays.asList(5,6,7)); 

				//[4-> 1 em cada 3; 5-> 1 em cada 4; 3-> 1 em cada 5]
				// 2a6 {1=8, 2=94, 3=351, 4=610, 5=479, 6=200, 7=19}
				sequenciaDeFibonacci(Arrays.asList(2,3,4,5));

				//[9-> 1 em cada 3; 10-> 1 em cada 4 ;11-> 1 em cada 6]
				// 8a11 {6=2, 7=52,8=199, 9=450, 10=483, 11=261, 12=88,13=6}
				quadrado(Arrays.asList(9,10,11));  

				// [5-> 1 em cada 3; 4-> 1 em cada 4; 6-> 1 em cada 5]
				// 3a7 {1=1, 2=33, 3=193, 4=430, 5=541,  6=317,7=111,8=14}
				multiplosDeTres(Arrays.asList(4,5,6)); 
				
				//1 a cada => [5-3; 6-3; 4-6; 7-7; 3-21; 8-35]
				// 3a8 {1=2, 2=12, 3=84, 4=303, 5=538, 6=549, 7=254, 8=50, 9=3} 
				numerosImportantes(Arrays.asList(4,5,6,7,8));
				
				QtdFiltros(Arrays.asList(2,3,4)); //1a4// {1=169; 2=1190; 3=366; 4=53; 5=1}
				maiorSequenciaDoisEmDois(Arrays.asList(1,2,3,4,5)); //1a5 {1=29, 2=847, 3=686, 4=205, 5=41, 6=9, 7=2}
				maiorSequenciaUmEmUm(Arrays.asList(3,4,5,6,7,8,9,10)); //3a10 {2=8, 3=231, 4=572, 5=499, 6=287, 7=123,  8=54, 9=20, 10=19, 11=3, 12=2, 13=0, 14=1 }
				
				linha(Arrays.asList(1, 2, 3, 4, 5),
					  Arrays.asList(1, 2, 3, 4, 5),  
					  Arrays.asList(1, 2, 3, 4), 
					  Arrays.asList(1, 2, 3, 4, 5),
					  Arrays.asList(2, 3, 4, 5));
				
				coluna(Arrays.asList(1, 2, 4, 5),
					   Arrays.asList(1, 2, 3, 4, 5),
					   Arrays.asList(1, 2, 3, 4, 5),
					   Arrays.asList(2, 3, 4, 5), 
					   Arrays.asList(1, 2, 3, 4));
				
				/*1 a cada => [ 43332-3; 44322-4; 44331-8; 53322-11; 54321-12; 44421-25; 33333-32; 54222-35; 53331-37; 54411-121; 55221-194; 55311-277]*/
				/*43332=626 ; 44322=452; 44331=242; 53322=185; 54321=159; 44421=78; 33333=60; 54222=56; 53331=52
				54411=16; 44430=15; 54330=14; 54420=10; 55221=10; 55311=7; 55320=2 */
				linhas (Arrays.asList(
						"43332"
						,"44322"
						,"44331"
						,"53322"
						,"54321"
						,"44421"
						,"33333"
						,"54222"
						,"53331"
						,"54411"
						,"55221"
						,"55311"
						));
						
				/* 1 a cada [43332-3; 44322-4; 44331-9; 53322-11; 54321-11; 44421-16; 54222-30; 33333-30; 53331-32; 54330-99; 54411-124; 55221-153; 54420-198;
				44430-248; 55320-397; 55311-662;]*/
				/*43332=612,44322=448,44331=216, 53322=188, 54321=175, 44421=77,  54222=66, 33333=66, 53331=62, 54330=20, 54411=16, 55221=13, 54420=10, 
					44430=8, 55320=5, 55311=3, */
				colunas (Arrays.asList(
						"43332"
						,"44322"
						,"44331"
						,"53322"
						,"54321"
						,"44421"
						,"54222"
					//	,"33333"
						,"53331"
						,"54411"
						,"55221"
						,"55311"
						));
			
				//dezMenos();
			
		//		 incluir(Arrays.asList(25)); //
				// 1[4];2[4];3[3,4];4[4];5[4];6[4];7[4];8[4];9[4];10[3,4];11[4];12[3,4];13[4];14[3,4];15[4];16[4];17[3,4];18[4];19[3,4];20[4];21[4];22[3,4];23[4];24[4];25[4];
				// retirar(Arrays.asList()); 
				// 1 [7,8];2[7,8];3[6,7,8];4[8];5[8];6[7,8];7[6,7,8];8[7];9[7];10[8,9];11[7,8,9,10];12[7,8,9];13[8,9];14[7,8,9];15[6,7,8];16[7,8];17[6,7,8];18[7,8,9];19[7,8];20[7,8];21[7,8];22[6,7];23[7,8];24[7,8,9];25[7,8,9];
				// naoIniciarCom(Arrays.asList( ));
				// naoTerminarCom(Arrays.asList( ));
				
						/*10=4 a 8*/
				listaMaisSaem(Arrays.asList(2, 8, 9, 14, 20, 1, 7, 18, 3, 6, 11, 12, 17, 23));
				listaMaisSaemNaoSairam(Arrays.asList(7, 8, 11, 12, 16, 23));
				//listaMenosSaemNaoSairam(Arrays.asList());
				//menores();
				//soma();

		return this.listaJogosCombinados;

	}

	private void QtdFiltros(List<Integer> lista) throws FileNotFoundException, IOException {

		if (todosSorteios.size() < 15)
			return;

		// System.out.println("filtrando filtros");
		// TODO Auto-generated method stub
		Filtro filtro = new Filtro();
		Estatisticas estatisticas = new Estatisticas();
		estatisticas.iniciarListas();
		filtro.iniciaListas();
		filtro.setaListaTodosSorteios(estatisticas.lerTodosOsJogos());

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		Jogo pares = estatisticas.buscarNumerosPares();
		Jogo primos = estatisticas.buscarNumerosPrimos();
		Jogo fibonacci = estatisticas.buscarNumerosSequenciaDeFibonacci();
		Jogo quadrado = estatisticas.buscarNumerosQuadrado();
		Jogo multiplosDeTres = estatisticas.buscarNumerosMultiplosDeTres();
		Jogo numerosImportantes = estatisticas.buscarNumerosImportantes();

		List<String> resultCSV = new ArrayList<String>();

		int numeroSorteio = 0;

		for (Jogo jogo : listaParaAnalise) {

			Set<Integer> intersectionPares = new HashSet<Integer>(jogo.getJogo());
			intersectionPares.retainAll(pares.getJogo());

			Set<Integer> intersectionPrimos = new HashSet<Integer>(jogo.getJogo());
			intersectionPrimos.retainAll(primos.getJogo());

			Set<Integer> intersectionFibonacci = new HashSet<Integer>(jogo.getJogo());
			intersectionFibonacci.retainAll(fibonacci.getJogo());

			Set<Integer> intersectionQuadrado = new HashSet<Integer>(jogo.getJogo());
			intersectionQuadrado.retainAll(quadrado.getJogo());

			Set<Integer> intersectionMultiplosDeTres = new HashSet<Integer>(jogo.getJogo());
			intersectionMultiplosDeTres.retainAll(multiplosDeTres.getJogo());

//			Set<Integer> intersectionDezMais = new HashSet<Integer>(jogo.getJogo());
//			
//			List<Jogo> todosSorteios = filtro.buscaListaTodosSorteios();
//			
//			intersectionDezMais.retainAll(estatisticas.buscarDezMais(todosSorteios,jogoAtual).getJogo());

			Set<Integer> intersectionNumerosImportantes = new HashSet<Integer>(jogo.getJogo());
			intersectionNumerosImportantes.retainAll(numerosImportantes.getJogo());

			List<Integer> list = new ArrayList<>();
			list.add(intersectionPares.size());
			list.add(intersectionPrimos.size());
			list.add(intersectionFibonacci.size());
			list.add(intersectionQuadrado.size());
			list.add(intersectionMultiplosDeTres.size());
//			list.add(intersectionDezMais.size());
			list.add(intersectionNumerosImportantes.size());

			int maxNumeroFiltroRepetido = 0;

			for (int j = 1; j < 13; j++) {
				maxNumeroFiltroRepetido = (maxNumeroFiltroRepetido > Collections.frequency(list, j))
						? maxNumeroFiltroRepetido
						: Collections.frequency(list, j);
			}

			if (!lista.contains(maxNumeroFiltroRepetido)) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

		}
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar filtros -> " + listaJogosCombinados.size());
	}

	public void maiorSequenciaDoisEmDois(List<Integer> lista) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			int cont = 0;
			int numero = 0;
			int maiorSequencia = 0;
			for (Integer n : jogo.getJogo()) {

				if (numero != 0) {
					if (numero + 2 == n) {
						cont++;
						if (maiorSequencia < cont) {
							maiorSequencia = cont;
						}
					} else {
						cont = 0;
					}

				}
				numero = n;
			}

			if (!lista.contains(maiorSequencia + 1)) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar dois em dois -> " + listaJogosCombinados.size());

	}

	public void maiorSequenciaUmEmUm(List<Integer> lista) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			int cont = 0;
			int numero = 0;
			int maiorSequencia = 0;
			for (Integer n : jogo.getJogo()) {

				if (numero != 0) {
					if (numero + 1 == n) {
						cont++;
						if (maiorSequencia < cont) {
							maiorSequencia = cont;
						}
					} else {
						cont = 0;
					}

				}
				numero = n;
			}

			if (!lista.contains(maiorSequencia + 1)) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar um em um -> " + listaJogosCombinados.size());

	}

	public List<Jogo> filtrarLista() throws FileNotFoundException, IOException {
		this.listaJogosCheia = new ArrayList<Jogo>();
		listaJogosCheia.addAll(listaJogosCombinados);

		repetidosJogoAnterior(listaParametrosRepetidos);
		/* 6 � forte */ pares(Arrays.asList(6, 7, 8)); // 5a9; // {3=3, 4=21, 5=117, 6=339, 7=516, 8=429, 9=182,
															// 10=41, 11=5}
		primos(Arrays.asList(5, 6, 7));
		// 4a7 {2=5, 3=76, 4=278, 5=520, 6=451, 7=257, 8=62, 9=4}
		/* 10 � forte */ quadrado(Arrays.asList(8, 10));
		// 8a11 {6=2, 7=52,8=199, 9=450, 10=483, 11=261, 12=88,13=6}
		// dezMais(Arrays.asList(4, 5, 6, 7)); // 4a8 {2=2, 3=24, 4=140, 5=387, 6=567,
		// 7=415, 8=165, 9=24, 10=3}
		/* 5 � forte */multiplosDeTres(Arrays.asList(5, 6)); // 3a7 {1=1, 2=33, 3=193, 4=430, 5=541, 6=317,7=111,8=14}
		sequenciaDeFibonacci(Arrays.asList(4, 5, 6));// 2a6 {1=8, 2=85, 3=330,4=567,5=453,6=182,7=15}

		linha(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 4, 5), Arrays.asList(1, 2, 4, 5), Arrays.asList(1, 3, 4),
				Arrays.asList(1, 2, 4));

		coluna(Arrays.asList(1, 3, 4, 5), Arrays.asList(1, 2, 3, 5), Arrays.asList(1, 2, 3, 4, 5),
				Arrays.asList(1, 2, 3, 5), Arrays.asList(1, 2, 3, 4));

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

		System.out.println("Tamaho da lista depois das combina��es ->" + listaJogosCombinados.size());

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
		
		retirarGrandesSequencias(10, estatisticas.buscarNumerosQuadrado());
		
		System.out.println("Tamanho da lista depois de filtrar a quadrado -> " + listaJogosCombinados.size());

	}

	private void multiplosDeTres(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosMultiplosDeTres());
		
		System.out.println("Tamanho da lista depois de filtrar a multiplos de tres -> " + listaJogosCombinados.size());

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
		
		retirarGrandesSequencias(8, estatisticas.buscarNumerosPares());
		
		System.out.println("Tamanho da lista depois de filtrar pares -> " + listaJogosCombinados.size());

	}

//	private void dezMais(List<Integer> lista) throws FileNotFoundException, IOException {
//
//		if (todosSorteios.size() < 15)
//			return;
//
//		analisaNumeros(lista, estatisticas.buscarDezMais(todosSorteios, todosSorteios.size()));
//
//		System.out.println("Tamanho da lista depois de filtrar dez melhores -> " + listaJogosCombinados.size());
//
//	}

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

	private void numerosImportantes(List<Integer> lista) {

		analisaNumeros(lista, estatisticas.buscarNumerosImportantes());

		System.out.println("Tamanho da lista depois de filtrar numeros importantes -> " + listaJogosCombinados.size());

	}
	
	public void linhas(List<String> lista) throws FileNotFoundException, IOException {
		
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		/*43332=626 ; 44322=452; 44331=242; 53322=185; 54321=159; 44421=78; 33333=60; 54222=56; 53331=52
		54411=16; 44430=15; 54330=14; 54420=10; 55221=10; 55311=7; 55320=2 */
		
		Jogo linha1 = new Jogo(estatisticas.buscarNumerosLinha1().getJogo());
		Jogo linha2 = new Jogo(estatisticas.buscarNumerosLinha2().getJogo());
		Jogo linha3 = new Jogo(estatisticas.buscarNumerosLinha3().getJogo());
		Jogo linha4 = new Jogo(estatisticas.buscarNumerosLinha4().getJogo());
		Jogo linha5 = new Jogo(estatisticas.buscarNumerosLinha5().getJogo());
		
		
	  for (Jogo jogo : listaParaAnalise) {
			
			List<Integer> linhas = new ArrayList<>();
			
			Set<Integer> l1 = new HashSet<Integer>(linha1.getJogo());
			l1.retainAll(jogo.getJogo());
			
			linhas.add(l1.size());
			
			Set<Integer> l2 = new HashSet<Integer>(linha2.getJogo());
			l2.retainAll(jogo.getJogo());
			
			linhas.add(l2.size());
			
			Set<Integer> l3 = new HashSet<Integer>(linha3.getJogo());
			l3.retainAll(jogo.getJogo());
			
			linhas.add(l3.size());
			
			Set<Integer> l4 = new HashSet<Integer>(linha4.getJogo());
			l4.retainAll(jogo.getJogo());
			
			linhas.add(l4.size());
			
			Set<Integer> l5 = new HashSet<Integer>(linha5.getJogo());
			l5.retainAll(jogo.getJogo());
			
			linhas.add(l5.size());
			
			Collections.sort (linhas,Collections.reverseOrder());
			
			String strLinhas = linhas.toString().replace("[", "").replace("]","").replace(", ","");
			
			if (lista.contains(strLinhas)) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar sequencia de linhas -> " + listaJogosCombinados.size());
		
	}
	
public void colunas(List<String> lista) throws FileNotFoundException, IOException {
		
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		/*43332=626 ; 44322=452; 44331=242; 53322=185; 54321=159; 44421=78; 33333=60; 54222=56; 53331=52
		54411=16; 44430=15; 54330=14; 54420=10; 55221=10; 55311=7; 55320=2 */
		
		Jogo coluna1 = new Jogo(estatisticas.buscarNumerosColuna1().getJogo());
		Jogo coluna2 = new Jogo(estatisticas.buscarNumerosColuna2().getJogo());
		Jogo coluna3 = new Jogo(estatisticas.buscarNumerosColuna3().getJogo());
		Jogo coluna4 = new Jogo(estatisticas.buscarNumerosColuna4().getJogo());
		Jogo coluna5 = new Jogo(estatisticas.buscarNumerosColuna5().getJogo());
		
		
	  for (Jogo jogo : listaParaAnalise) {
			
		    List<Integer> colunas = new ArrayList<>();
			
			Set<Integer> c1 = new HashSet<Integer>(coluna1.getJogo());
			c1.retainAll(jogo.getJogo());
			
			colunas.add(c1.size());
			
			Set<Integer> c2 = new HashSet<Integer>(coluna2.getJogo());
			c2.retainAll(jogo.getJogo());
			
			colunas.add(c2.size());
			
			Set<Integer> c3 = new HashSet<Integer>(coluna3.getJogo());
			c3.retainAll(jogo.getJogo());
			
			colunas.add(c3.size());
			
			Set<Integer> c4 = new HashSet<Integer>(coluna4.getJogo());
			c4.retainAll(jogo.getJogo());
			
			colunas.add(c4.size());
			
			Set<Integer> c5 = new HashSet<Integer>(coluna5.getJogo());
			c5.retainAll(jogo.getJogo());
			
			colunas.add(c5.size());
			
			Collections.sort (colunas,Collections.reverseOrder());
			
			String strColunas = colunas.toString().replace("[", "").replace("]","").replace(", ","");
			
			if (lista.contains(strColunas)) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar sequencia de colunas -> " + listaJogosCombinados.size());
		
	}

	private void linha(List<Integer> primeiraLinha, List<Integer> segundaLinha, List<Integer> terceiraLinha,
			List<Integer> quartaLinha, List<Integer> quintaLinha) {

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

			if (contPrimeiraLinha == contPrimeiraLinhaJogoAnterior && contSegundaLinha == contSegundaLinhaJogoAnterior
					&& contTerceiraLinha == contTerceiraLinhaJogoAnterior
					&& contQuartaLinha == contQuartaLinhaJogoAnterior
					&& contQuintaLinha == contQuintaLinhaJogoAnterior) {
				continue;
			}

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

			if (!primeiraLinha.contains(contPrimeiraLinha) || !segundaLinha.contains(contSegundaLinha)
					|| !terceiraLinha.contains(contTerceiraLinha) || !quartaLinha.contains(contQuartaLinha)
					|| !quintaLinha.contains(contQuintaLinha)) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar linhas -> " + listaJogosCombinados.size());

	}

	private void coluna(List<Integer> primeiraColuna, List<Integer> segundaColuna, List<Integer> terceiraColuna,
			List<Integer> quartaColuna, List<Integer> quintaColuna) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		Jogo strUltimoSorteio = todosSorteios.get(todosSorteios.size() - 1);
		for (Jogo jogo : listaParaAnalise) {

			int contPrimeiraColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna1());
			int contSegundaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna2());
			int contTerceiraColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna3());
			int contQuartaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna4());
			int contQuintaColuna = retornaIntersecao(jogo, estatisticas.buscarNumerosColuna5());

			int contPrimeiraColunaJogoAnterior = retornaIntersecao(strUltimoSorteio,
					estatisticas.buscarNumerosColuna1());
			int contSegundaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio,
					estatisticas.buscarNumerosColuna2());
			int contTerceiraColunaJogoAnterior = retornaIntersecao(strUltimoSorteio,
					estatisticas.buscarNumerosColuna3());
			int contQuartaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna4());
			int contQuintaColunaJogoAnterior = retornaIntersecao(strUltimoSorteio, estatisticas.buscarNumerosColuna5());

			if (contPrimeiraColuna == contPrimeiraColunaJogoAnterior
					&& contSegundaColuna == contSegundaColunaJogoAnterior
					&& contTerceiraColuna == contTerceiraColunaJogoAnterior
					&& contQuartaColuna == contQuartaColunaJogoAnterior
					&& contQuintaColuna == contQuintaColunaJogoAnterior) {
				continue;
			}

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

			if (!primeiraColuna.contains(contPrimeiraColuna) || !segundaColuna.contains(contSegundaColuna)
					|| !terceiraColuna.contains(contTerceiraColuna) || !quartaColuna.contains(contQuartaColuna)
					|| !quintaColuna.contains(contQuintaColuna)) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);

		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar colunas -> " + listaJogosCombinados.size());

	}
	
	private void dezMenos() throws FileNotFoundException, IOException {
		
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		Estatisticas est = new Estatisticas();

		List<Jogo> todosJogos = est.lerTodosOsJogos();
		
		int numeroJogo = todosJogos.size();
		
		List<Jogo> jogoAnteriores = ultimosJogos(numeroJogo);
		
		List<Integer> numeros = numerosQueMenosSaem (jogoAnteriores);
		
		for (Jogo jogo : listaParaAnalise) {
			
			Set<Integer> total = new HashSet<Integer>(numeros);
			
			total.retainAll(jogo.getJogo());
			
			if (Arrays.asList(2,3,4).contains(total.size())) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			
					
		}
		
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar dez menos -> " + listaJogosCombinados.size());
	}
	private void acompanhaO15() throws FileNotFoundException, IOException {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {
			
			if (jogo.getJogo().contains(15)) {
				Set<Integer> n = new HashSet<Integer>(Arrays.asList(22,23,24,25));
				n.retainAll(jogo.getJogo());
				
				if (n.size() > 0) {
					
					jogo.somaQuantidadeFiltros();
					listaPorFiltro.add(jogo);
				
				}
				
			} else {
				
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
				
			}
			
		}
		
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar acompanha o 15 -> " + listaJogosCombinados.size());
	}	
	
	private void acompanhaO1() throws FileNotFoundException, IOException {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {
			
			if (jogo.getJogo().contains(1)) {
				Set<Integer> n = new HashSet<Integer>(Arrays.asList(2,3,4));
				n.retainAll(jogo.getJogo());
				
				if (n.size() > 0) {
					
					jogo.somaQuantidadeFiltros();
					listaPorFiltro.add(jogo);
				
				}
				
			} else {
				
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
				
			}
			
		}
		
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar acompanha o 15 -> " + listaJogosCombinados.size());
	}

	private void melhoresCombinacoes() throws FileNotFoundException, IOException {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(jogo.getJogo());
			intersectionRepetidos.retainAll(Arrays.asList(11,12,13,14));

			if (Arrays.asList(2, 3, 4).contains(intersectionRepetidos.size())) {
				jogo.somaQuantidadeFiltros();
				listaPorFiltro.add(jogo);
			}
			
		}
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar melhores combina��es -> " + listaJogosCombinados.size());
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
			if (soma > 166 && soma < 225) {
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
		 * [9 a 17] posição 9 [11 a 18] posição 10 [13 a 19] posição 11 [15 a 21]
		 * posição 12 [17 a 22] posição 13 [19 a 23] posição 14 [21 a 24]
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
		return filtrar();
	}

	public List<Jogo> listaJogosFiltrados() throws FileNotFoundException, IOException {
		return filtrarLista();
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

	private void incluir(List<Integer> lista) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {
			boolean foi = false;
			for (Integer numero : lista) {
				if (!jogo.getJogo().contains(numero)) {
					foi = true;
				}

			}
			if (foi) {
				continue;
			}
			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar inclus�o de n�meros -> " + listaJogosCombinados.size());
	}

	private void retirar(List<Integer> lista) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {
			boolean foi = false;
			for (Integer numero : lista) {
				if (jogo.getJogo().contains(numero)) {
					foi = true;
				}

			}
			if (foi) {
				continue;
			}
			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar retirada de n�meros -> " + listaJogosCombinados.size());
	}

	private void naoIniciarCom(List<Integer> lista) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			if (lista.contains(jogo.getJogo().get(0))) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar n�o iniciar -> " + listaJogosCombinados.size());
	}

	private void naoTerminarCom(List<Integer> lista) {
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);

		for (Jogo jogo : listaParaAnalise) {

			if (lista.contains(jogo.getJogo().get(14))) {
				continue;
			}

			jogo.somaQuantidadeFiltros();
			listaPorFiltro.add(jogo);
		}

		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar n�o iniciar -> " + listaJogosCombinados.size());
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

	public void testaJogo() {

		Jogo teste = new Jogo(Arrays.asList(3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15, 16, 17, 22, 25));

		for (Jogo jogo : listaCambinacoes) {
			Set<Integer> intersection = new HashSet<Integer>(jogo.getJogo());
			intersection.retainAll(teste.getJogo());

			if (intersection.size() > 14) {
				System.err.println("passou");
			}

		}

	}

	public int getJogoAtual() {
		return jogoAtual;
	}

	public void setJogoAtual(int jogoAtual) {
		this.jogoAtual = jogoAtual;
	}
	
	public void retirarGrandesSequencias(int seq, Jogo numeros) {
		
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		Map<Integer, Integer> mapaSeqA = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> mapaSeqB = new HashMap<Integer, Integer>();
		
		int valor = 1;
		
		for (Integer integer : numeros.getJogo()) {
			mapaSeqA.put(integer, valor++);
		}
		
		int chave = 1;
		
		for (Integer integer : numeros.getJogo()) {
			mapaSeqB.put(chave++,integer);
		}
		
		for (Jogo j : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(numeros.getJogo());
			intersectionRepetidos.retainAll(j.getJogo());

			Integer numeroAux = 0;

			Integer cont = 1;

			boolean passou = true;
			
			for (Integer proximo : intersectionRepetidos) {

				if (numeroAux != 0) {

					if (mapaSeqB.get(mapaSeqA.get(numeroAux) + 1) == proximo) {

						cont++;

					} else {

						cont = 1;

					}
				}

				if (cont > seq) {
				
					passou = false;
					break;

				}

				numeroAux = proximo;

			}

			if (passou) {

				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);

			}

		}
		
		atualizaListaFiltrada();
	}
	
	public  List<Jogo> ultimosJogos (int jogoAtual) throws FileNotFoundException, IOException {
		
		Estatisticas est = new Estatisticas();

		List<Jogo> todosJogos = est.lerTodosOsJogos();
		
		List<Jogo> jogos = new ArrayList<>();
		
		List<Integer> dezenas = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25));
		
		int posicaoInicial = jogoAtual - 20;
		
		for (int i = posicaoInicial; i < jogoAtual; i++) {
			
				jogos.add(todosJogos.get(i));
		
		}
		
		return jogos;
		
	}
	
	public  List<Integer> numerosQueMenosSaem(List<Jogo> jogos) throws FileNotFoundException, IOException {
		
		
		//List<Jogo> jogos =  ultimosJogos(20);
		
		List<Integer> dezenas = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25));
		
		Map<Integer, Map<Integer, Integer>> mapaPorListaDezenas = new HashMap();
		
		Map<Integer, Integer> mapaResult = new HashMap();
		
		for (Integer d1 : dezenas) {
			
			int cont = 0;
			
			Map<Integer, Integer> mapaDezenas = new HashMap();
			
			mapaPorListaDezenas.put(d1, mapaDezenas);
			
			for (Jogo jogo : jogos) {
				
				if (jogo.getJogo().contains(d1)) {
					
					cont++;
				
					for (Integer d2 : dezenas) {
						
						if (d2 != d1 && jogo.getJogo().contains(d2)) {
							
							if (mapaDezenas.containsKey(d2)) {
								int valor = mapaDezenas.get(d2).intValue();
								mapaDezenas.put(d2, valor + 1);
							} else {
								mapaDezenas.put(d2, 1);
							}
						}
				
					}
				}
			}
			
			
	        
	        //System.out.println(String.format("%02d", d1) + " [" + cont + "] = > " + result);
	        //System.out.println(cont);
	        mapaResult.put(d1,cont);
	        
	       // mapaPorListaDezenas.put(d1, result);
			
		}
		
		List<Entry<Integer, Integer>> list = new ArrayList<>(mapaResult.entrySet());
        list.sort(Entry.comparingByValue());

        Map<Integer, Integer> result = new LinkedHashMap<>();
        int qtd = 0;
        List<Integer> numeros = new ArrayList<>();
        
        for (Entry<Integer, Integer> entry : list) {
        	
        	qtd++;
        	
            result.put(entry.getKey(), entry.getValue());
            
            numeros.add(entry.getKey());
            
            if (qtd == 5) break;
            
        }
        
        return numeros;
		
	//	System.out.println(numeros);
//		
//		for (Entry<Integer, Map<Integer, Integer>> mapa : mapaPorListaDezenas.entrySet()) {
//			System.out.println(mapa);
//		}
	}
	
	private void listaMaisSaem(List<Integer> lista) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		for (Jogo j : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(lista);
			intersectionRepetidos.retainAll(j.getJogo());
			//quando tem 15 -> 6 a 10
			//quando tem 14 -> 6 a 10  
			//quando tem 13 -> 6 a 10 *revisar
			//quando tem 12 -> 5 a 9 *revisar
			//quando tem 10 -> 4 a 8 * revisar
			if (Arrays.asList(6,7,8,9,10).contains(intersectionRepetidos.size())) {
				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);
			}
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar os que mais saem -> " + listaJogosCombinados.size());

	}
	
	private void listaMaisSaemNaoSairam(List<Integer> lista) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		for (Jogo j : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(lista);
			intersectionRepetidos.retainAll(j.getJogo());
			//25%
			//quando tem 8 -> 3 a 6
			//quando tem 6 -> 2 a 5
			//quando tem 5 -> 1 a 5
			if (Arrays.asList(2,3,4,5).contains(intersectionRepetidos.size())) {
				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);
			}
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar os que mais saem e n�o sa�ram-> " + listaJogosCombinados.size());

	}
	
	private void listaMenosSaemNaoSairam(List<Integer> lista) {

		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		//quando tem 8 -> 3 a 7
		//quando tem 5 -> 1 a 4
		//quando tem 2 3 e 4 -> n�o arriscar
		for (Jogo j : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(lista);
			intersectionRepetidos.retainAll(j.getJogo());
			
			if (Arrays.asList(1,2,3,4).contains(intersectionRepetidos.size())) {
				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);
			}
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar os que menos saem e n�o sa�ram-> " + listaJogosCombinados.size());

	}
	
	public void menores() throws FileNotFoundException, IOException {
		
		limpaListasAuxiliares();
		listaParaAnalise.addAll(listaJogosCombinados);
		
		List<Integer> menores = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
/*		
		6	219
		7	587
		8	614
		9	405
		10	142
*/

		for (Jogo j : listaParaAnalise) {

			Set<Integer> intersectionRepetidos = new HashSet<Integer>(menores);
			intersectionRepetidos.retainAll(j.getJogo());
			
			if (intersectionRepetidos.size() > 4 && intersectionRepetidos.size() < 10) {
				j.somaQuantidadeFiltros();
				listaPorFiltro.add(j);
			}
		}
		
	
		atualizaListaFiltrada();
		System.out.println("Tamanho da lista depois de filtrar os menores-> " + listaJogosCombinados.size());
		
		
	}

}
