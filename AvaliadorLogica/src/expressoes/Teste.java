package expressoes;

import java.util.ArrayList;
import java.util.List;

public class Teste {
	
	public static void main(String[] args) {
		
		String expressao = "(-(x.(-z)))";
		List<String> tabela = new ArrayList<String>();
		List<String> operadores = new ArrayList<String>();
		
		operadores.add("-1");
		operadores.add(".2");
		
		tabela.add("182");
		tabela.add("1");
		tabela.add("0");
		tabela.add("0");
		tabela.add("1");
		tabela.add("0");
		tabela.add("1");
		tabela.add("0");
		tabela.add("1");
		tabela.add("0");
		tabela.add("0");
		tabela.add("1");
		tabela.add("1");
		tabela.add("0");
		tabela.add("0");
		tabela.add("0");
		tabela.add("1");
		
		int valor = 1;
		int quantidadeOperadores = 2;
		
		AvaliadorExpressoes x = new AvaliadorExpressoes();
		System.out.println(x.expressaoBF(expressao));
		System.out.println(x.alturaArvore(expressao));
		System.out.println(x.quantidadeSubexpressoes(expressao));
		//System.out.println(x.valorVerdade(expressao, tabela, quantidadeOperadores, operadores, valor));
		
		
	}

}
