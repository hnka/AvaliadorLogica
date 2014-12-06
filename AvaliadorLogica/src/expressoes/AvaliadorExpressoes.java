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
			
			System.out.println(expressao);

			altura = altura + 0;
			
		} else {
			
			//case 1: regex for (-atom)
			if(expressao.matches("[\\(][-][xyz][\\)]")) {
				
				altura = altura + 1;
				
				String temp = expressao.substring(2, 3);
				System.out.println(temp);
				
				int alturaTemp = this.alturaArvore(temp);
				
				altura = altura + alturaTemp;
			
			//case 2: regex for (-(subexpression))
			} else if(expressao.matches("[\\(][-][\\(].*[\\)][\\)]")) {
				
				altura = altura + 1;
				
				String temp = expressao.substring(2, expressao.length()-1);
				System.out.println(temp);
				
				//observar comportamento, se a altura esta sendo aumentada
				int alturaTemp = this.alturaArvore(temp);
				
				altura = altura + alturaTemp;
				
			
			//case 3: regex for (atom symbol atom)
			} else if(expressao.matches("[\\(][xyz][^xyz01][xyz][\\)]")) {
				
				altura = altura + 1;

				String temp1 = expressao.substring(1,2);
				System.out.println(temp1);
				String temp2 = expressao.substring(3,4);
				System.out.println(temp2);
				
				int altura1 = this.alturaArvore(temp1);
				int altura2 = this.alturaArvore(temp2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					System.out.println(altura);
					
				} else {
					
					altura = altura + altura1;
					System.out.println(altura);
					
				}
			
			//case 4: regex for (atom symbol subexpression)
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
				System.out.println(sub1);
				String sub2 = expressao.substring(trim-1,expressao.length()-1);
				System.out.println(sub2);
				
				int altura1 = this.alturaArvore(sub1);
				int altura2 = this.alturaArvore(sub2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					System.out.println(altura);
					
				} else {
					
					altura = altura + altura1;
					System.out.println(altura);
					
				}
			
			//case 5: regex for (subexpression symbol atom)
			} else if (expressao.matches("[\\(][\\(].*[\\)][^xyz01][xyz][\\)]")) {
				
				altura = altura + 1;
				
				String regex = "[\\(][\\(].*[\\)][^xyz01]";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(expressao);
				
				int trim = 0;
				
				if(matcher.find()) {
					
					System.out.println(matcher.start());
					System.out.println(matcher.end());
					trim = matcher.end();
					
				}
				
				String sub1 = expressao.substring(1,trim-1);
				System.out.println(sub1);
				String sub2 = expressao.substring(trim,expressao.length()-1);
				System.out.println(sub2);
				
				int altura1 = this.alturaArvore(sub1);
				int altura2 = this.alturaArvore(sub2);
				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					System.out.println(altura);
					
				} else {
					
					altura = altura + altura1;
					System.out.println(altura);
					
				}
				
			//case 6: regex for (subexpression symbol subexpression)
			} else if(expressao.matches("[\\(][\\(].*[\\)][^xyz01][\\(].*[\\)][\\)]")) {
				
				System.out.println("achei");
				
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
				
				System.out.println(sub1);
				System.out.println(sub2);
				
				int altura1 = this.alturaArvore(sub1);				
				int altura2 = this.alturaArvore(sub2);

				
				if(altura1 < altura2) {
					
					altura = altura + altura2;
					System.out.println(altura);
					
				} else {
					
					altura = altura + altura1;
					System.out.println(altura);
					
				}
				
			}

		}
		
		return altura;
		
	}

}
