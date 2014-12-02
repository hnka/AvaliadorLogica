package llf.Input;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
			
			String[] test = new String[2];
			test[0] = "You're dumb";
			
			fecharArquivo(test);
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	
	
	//Leitura do Arquivo
	public static Object[] lerArquivo() throws IOException{
		
		//Seta o tipo de chars
		Charset ct = Charset.forName("utf-8");
		
		//Cria um lista para adicionar os termos
		List<String> ex = new ArrayList<String>();
		
		//Um for que varre todo o arquivo
		for (String line : Files.readAllLines(Paths.get("Expressao.in"), ct)) {
				ex.add(line);
		
		}
		
		//Retorna um array com todas as expressoes
		return ex.toArray();
		
	}
	
	//Escreve mudanças para o arquivo
	public static void fecharArquivo(String[] ex) throws IOException{
		
		//Inicializa novo objeto que registra no arquivo especificado
		Writer autor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Expressao.out"), "utf-8"));
		
		//Um for para pegar todos os itens do array
		for(int i = 0; i< ex.length; i++){
			autor.write((String) ex[i] + "\n");
		}
		
		//fecha o objeto
		autor.close();
	}
	

}
