package expressoes;

public class AvaliadorExpressoes {
  
  	private String atomX;
	private String atomY;
	private String atomZ;
	
	public AvaliadorExpressoes() {

		this.atomX = "x";
		this.atomX = "y";
		this.atomX = "z";
		
	}
	
	//create method to receive two strings as arguments and organize the returns
	public int alturaArvore(String expressao) {
		
		int altura = 0;
		
		if(expressao == this.atomX || expressao == this.atomY || expressao == this.atomZ) {
			
			return altura;
			
		} else {
			
			//regex matching (-
			if(expressao.matches("^([\\(])(-)")) {
				
				altura = altura + 1;
				return this.alturaArvore(expressao.substring(2, expressao.length()-1));
				
			//regex matching the first occurence of () any symbol ()
			} else if (expressao.matches("([\\(])(.*)([\\)])(\\W)([\\(])(.*)([\\)])")) {
				
				altura = altura + 1;
				String[] subs = expressao.split("([\\(])(.*)([\\)])(\\W)([\\(])(.*)([\\)])", 1);
	
				int altura1 = this.alturaArvore(subs[0]);
				int altura2 = this.alturaArvore(subs[1]);
				
				if(altura1 < altura2) {
					
					return altura2;
					
				} else {
					
					return altura1;
					
				}
				
			}

		}
		return altura;
		
	}

}
