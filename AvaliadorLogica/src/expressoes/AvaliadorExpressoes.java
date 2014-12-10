package expressoes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvaliadorExpressoes {
  
  	private String atomX;
	private String atomY;
	private String atomZ;
	
	public AvaliadorExpressoes() {

		this.atomX = "x";
		this.atomX = "y";
		this.atomX = "z";
		
	}
	
	public int alturaArvore(String expressao) {
		
		int altura = 0;
		
		//base: regex for atoms
		if(expressao.matches("[xyz]")) {

			altura = altura + 0;
			
		} else {
			
			//case 1: regex for (-atom)
			if(expressao.matches("[\\(][-][xyz][\\)]")) {
				
				altura = altura + 1;
				
				String temp = expressao.substring(2, 3);
				
				int alturaTemp = this.alturaArvore(temp);
				
				altura = altura + alturaTemp;
			
			//case 2: regex for (-(subexpression))
			} else if(expressao.matches("[\\(][-][\\(].*[\\)][\\)]")) {
				
				altura = altura + 1;
				
				String temp = expressao.substring(2, expressao.length()-1);
				
				int alturaTemp = this.alturaArvore(temp);
				
				altura = altura + alturaTemp;
				
			
			//case 3: regex for (atom symbol atom)
			} else if(expressao.matches("[\\(][xyz][^xyz01][xyz][\\)]")) {
				
				altura = altura + 1;

				String temp1 = expressao.substring(1,2);
				String temp2 = expressao.substring(3,4);
				
				int altura1 = this.alturaArvore(temp1);
				int altura2 = this.alturaArvore(temp2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					
				} else {
					
					altura = altura + altura1;
					
				}
			
			//case 4: regex for (atom symbol (subexpression))
			} else if(expressao.matches("[\\(][xyz][^xyz01][\\(].*[\\)][\\)]")) {
				
				altura = altura + 1;
				
				String regex = "[\\(][xyz][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,2);
				String sub2 = expressao.substring(trim-1,expressao.length()-1);
				
				int altura1 = this.alturaArvore(sub1);
				int altura2 = this.alturaArvore(sub2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					
				} else {
					
					altura = altura + altura1;
					
				}
			
			//case 5: regex for ((subexpression) symbol atom)
			} else if (expressao.matches("[\\(][\\(].*[\\)][^xyz01][xyz][\\)]")) {
				
				altura = altura + 1;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,trim-1);
				String sub2 = expressao.substring(trim,expressao.length()-1);
				
				int altura1 = this.alturaArvore(sub1);
				int altura2 = this.alturaArvore(sub2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					
				} else {
					
					altura = altura + altura1;
					
				}
				
			//case 6: regex for ((subexpression) symbol (subexpression))
			} else if(expressao.matches("[\\(][\\(].*[\\)][^xyz01][\\(].*[\\)][\\)]")) {
				
				altura = altura + 1;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1, trim-2);
				String sub2 = expressao.substring(trim-1, expressao.length()-1);
				
				int altura1 = this.alturaArvore(sub1);				
				int altura2 = this.alturaArvore(sub2);

				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					
				} else {
					
					altura = altura + altura1;
					
				}
				
			}

		}
		
		return altura;
		
	}
	
	public int quantidadeSubexpressoes(String expressao) {
		
		int quantidadeSubs = 0;
		
		//base: regex for atoms
		if(expressao.matches("[xyz]")) {

			quantidadeSubs = quantidadeSubs + 1;
			
		} else {
			
			//case 1: regex for (-atom)
			if(expressao.matches("[\\(][-][xyz][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;
				
				String temp = expressao.substring(2, 3);
				
				int subsTemp = this.quantidadeSubexpressoes(temp);
				
				quantidadeSubs = quantidadeSubs + subsTemp;
			
			//case 2: regex for (-(subexpression))
			} else if(expressao.matches("[\\(][-][\\(].*[\\)][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;
				
				String temp = expressao.substring(2, expressao.length()-1);
				
				int subsTemp = this.quantidadeSubexpressoes(temp);
				
				quantidadeSubs = quantidadeSubs + subsTemp;
				
			
			//case 3: regex for (atom symbol atom)
			} else if(expressao.matches("[\\(][xyz][^xyz01][xyz][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;

				String sub1 = expressao.substring(1,2);
				String sub2 = expressao.substring(3,4);
				
				if(sub1.equals(sub2)) {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					quantidadeSubs = quantidadeSubs + quantidade1;
					
				} else {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					int quantidade2 = this.quantidadeSubexpressoes(sub2);
					quantidadeSubs = quantidadeSubs + quantidade1 + quantidade2;
					
				}
	
			//case 4: regex for (atom symbol (subexpression))
			} else if(expressao.matches("[\\(][xyz][^xyz01][\\(].*[\\)][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;
				
				String regex = "[\\(][xyz][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,2);
				String sub2 = expressao.substring(trim-1,expressao.length()-1);
				
				if(sub1.equals(sub2)) {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					quantidadeSubs = quantidadeSubs + quantidade1;
					
				} else {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					int quantidade2 = this.quantidadeSubexpressoes(sub2);
					quantidadeSubs = quantidadeSubs + quantidade1 + quantidade2;
					
				}
			
			//case 5: regex for ((subexpression) symbol atom)
			} else if (expressao.matches("[\\(][\\(].*[\\)][^xyz01][xyz][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {

					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,trim-1);
				String sub2 = expressao.substring(trim,expressao.length()-1);
				
				if(sub1.equals(sub2)) {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					quantidadeSubs = quantidadeSubs + quantidade1;
					
				} else {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					int quantidade2 = this.quantidadeSubexpressoes(sub2);
					quantidadeSubs = quantidadeSubs + quantidade1 + quantidade2;
					
				}
				
			//case 6: regex for ((subexpression) symbol (subexpression))
			} else if(expressao.matches("[\\(][\\(].*[\\)][^xyz01][\\(].*[\\)][\\)]")) {
				
				quantidadeSubs = quantidadeSubs + 1;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1, trim-2);
				String sub2 = expressao.substring(trim-1, expressao.length()-1);

				if(sub1.equals(sub2)) {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					quantidadeSubs = quantidadeSubs + quantidade1;
					
				} else {
					
					int quantidade1 = this.quantidadeSubexpressoes(sub1);
					int quantidade2 = this.quantidadeSubexpressoes(sub2);
					quantidadeSubs = quantidadeSubs + quantidade1 + quantidade2;
					
				}
				
			}

		}
		
		return quantidadeSubs;
			
	}
	
	//tests the expression
	//if it's well formed, the boolean will return true
	//if not, false
	public boolean expressaoBF (String expressao) {
		
		boolean ebf = false;
		
		//base: regex for atoms
		if(expressao.matches("[xyz]")) {

			ebf = true;
			
		} else {
		
			//case 1: regex for (-atom)
			if(expressao.matches("[\\(][-][xyz][\\)]")) {
				
				ebf = true;
				
				String temp = expressao.substring(2, 3);
				
				boolean ebfT = this.expressaoBF(temp);
				
				if(ebfT == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}

			//case 2: regex for (-(subexpression))
			} else if(expressao.matches("[\\(][-][\\(].*[\\)][\\)]")) {
				
				ebf = true;
				
				String temp = expressao.substring(2, expressao.length()-1);
				
				boolean ebfT = this.expressaoBF(temp);
				
				if(ebfT == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}

			//case 3: regex for (atom symbol atom)
			} else if(expressao.matches("[\\(][xyz][^xyz01][xyz][\\)]")) {
				
				ebf = true;

				String sub1 = expressao.substring(1,2);
				String sub2 = expressao.substring(3,4);
				
				boolean ebfT1 = this.expressaoBF(sub1);
				boolean ebfT2 = this.expressaoBF(sub2);
				
				if((ebfT1 || ebfT2) == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}
			
			//case 4: regex for (atom symbol (subexpression))
			} else if(expressao.matches("[\\(][xyz][^xyz01][\\(].*[\\)][\\)]")) {
				
				ebf = true;
				
				String regex = "[\\(][xyz][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,2);
				String sub2 = expressao.substring(trim-1,expressao.length()-1);
				
				boolean ebfT1 = this.expressaoBF(sub1);
				boolean ebfT2 = this.expressaoBF(sub2);
				
				if((ebfT1 || ebfT2) == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}
			
			//case 5: regex for ((subexpression) symbol atom)
			} else if (expressao.matches("[\\(][\\(].*[\\)][^xyz01][xyz][\\)]")) {
				
				ebf = true;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {

					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,trim-1);
				String sub2 = expressao.substring(trim,expressao.length()-1);
				
				boolean ebfT1 = this.expressaoBF(sub1);
				boolean ebfT2 = this.expressaoBF(sub2);
				
				if((ebfT1 || ebfT2) == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}
			
			//case 6: regex for ((subexpression) symbol (subexpression))
			} else if (expressao.matches("[\\(][\\(].*[\\)][^xyz01][\\(].*[\\)][\\)]")) {
				
				ebf = true;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01][\\(]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1, trim-2);
				String sub2 = expressao.substring(trim-1, expressao.length()-1);
				
				boolean ebfT1 = this.expressaoBF(sub1);
				boolean ebfT2 = this.expressaoBF(sub2);
				
				if((ebfT1 || ebfT2) == false) {
					
					ebf = false;
					return ebf;
					
				} else {
					
					ebf = true;
					
				}
				
			}
		
		}
		
		return ebf;
		
	}
	
	//[Incomplete Method]
	//when calling this method for the first time, since we want the truth values, int valor = 1;
	//the array of ints have the values for x,y,z in this order
	//don't use the first position of the tabela list
	public List<String> valorVerdade(String expressao, List<String> tabela, int numeroOperadores, List<String> operadores, int valor) {
		
		List<String> variaveis  = new ArrayList<String>();
		
		//each position of this array will have the starting e ending indexes of the truth tables for each operator
		//I'm eliminating the first position of the array
		int[] indexes = new int[numeroOperadores*2];
		
		//pointer to the position at the truth table
		int track = 1;

		//calculating the indexes for the truth table from the number of operators and the arraylist operadores
		for (int i = 0; i < numeroOperadores; i++) {
		
			//for unary operators
			if(operadores.get(i).equals("-1")) {
				
				int j = 0;
				
				while((indexes[j] != 0) || (indexes.length < numeroOperadores*2)) {					
					j++;	
				}

				indexes[j] = track;
				indexes[j+1] = track+3;
				track = track+4;
			
			//for binary
			} else {
				
				int k = 0;
				
				while((indexes[k] != 0) || (indexes.length < numeroOperadores*2)) {					
					k++;	
				}
				
				indexes[k] = track;
				indexes[k+1] = track+11;
				track = track+12;

			}
			
		}
		
		System.out.println(indexes[0]);
		System.out.println(indexes[1]);
		System.out.println(indexes[2]);
		System.out.println(indexes[3]);
	
		//base: regex for atoms
		if(expressao.matches("[xyz]")) {
			
			String temp = expressao + "=" + valor;
			
			variaveis.add(temp);
			
		} else {
			
			//case 1: regex for (-atom)
			if(expressao.matches("[\\(][-][xyz][\\)]")) {
				
				if(valor == 1) {
					
					valor = 0;
					String temp = expressao.substring(2,3);
					
					variaveis = this.valorVerdade(temp, tabela, numeroOperadores, operadores, valor);
	
				} else {
					
					valor = 1;
					String temp = expressao.substring(2,3);
					
					variaveis = this.valorVerdade(temp, tabela, numeroOperadores, operadores, valor);
					
				}
				
			//case 2: regex for (-(subexpression))
			} else if(expressao.matches("[\\(][-][\\(].*[\\)][\\)]")) {
				
				if(valor == 1) {
					
					valor = 0;
					String temp = expressao.substring(2, expressao.length()-1);
					
					variaveis = this.valorVerdade(temp, tabela, numeroOperadores, operadores, valor);
					
				} else {
					
					valor = 0;
					String temp = expressao.substring(2, expressao.length()-1);
					
					variaveis = this.valorVerdade(temp, tabela, numeroOperadores, operadores, valor);
					
				}
			
			//case 3: regex for (atom symbol atom)
			} else if(expressao.matches("[\\(][xyz][^xyz01][xyz][\\)]")) {
				
				String regex = "[xyz][^xyz01][xyz]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					trim = matcher.end();
					
				}
				
				System.out.println(trim);
				int pSymbol = trim-1;
				String tempSymbol = expressao.charAt(pSymbol) + "2";
				int positionOperator = 0;
				
				for(int n = 0; n<numeroOperadores; n++) {
					
					if(operadores.get(n).equals(tempSymbol)) {
						
						positionOperator = n;
						
					}
					
				}

				
				if(valor == 1) {
					
					tabela.get(indexes[positionOperator*2]);
					
					//if( tabela.get(indexes[positionOperator*2]+2) || ) 

					
				} else {
					
					
					
				}
				
				String temp1 = expressao.substring(1,2);
				String temp2 = expressao.substring(3,4);
				
				
				
			}

		}

		return variaveis;
	
	}

}
