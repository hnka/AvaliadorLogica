package llf.Input;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class AvalLogico {

	public static void main(String[] args) {		
		
		
		try {			
			
			Object[] ex = lerArquivo();
			System.out.println(ex[1]);
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	
	
	//Leitura do Arquivo
	public static Object[] lerArquivo() throws IOException{
		
		//Seta o tipo de chars
		Charset ct = Charset.forName("US-ASCII");
		
		//Cria um lista para adicionar os termos
		List<String> ex = new ArrayList<String>();
		
		//Um for que varre todo o arquivo
		for (String line : Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/src/llf/Input/Expressao.in"), ct)) {
				ex.add(line);
		
		}
		
		//Retorna um array com todas as expressoes
		return ex.toArray();
		
	}
	

}
