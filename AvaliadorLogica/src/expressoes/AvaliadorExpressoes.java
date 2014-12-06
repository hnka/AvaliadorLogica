package expressoes;

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

}
