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
import expressoes.AvaliadorExpressoes;
public class AvalLogico {

	public static void main(String[] args) {		
		
		
		try {			
			
			List<String> ex = lerArquivo();
			
			List<String> termos = new ArrayList<String>();
			int pos = 0;
			
			for(int i = 0; i < ex.size(); i++){
				if(ex.get(i).matches("\\d\\s\\d")){
					pos = i;
					
				}else if(ex.get(i).matches("\\d\\s\\d\\s\\d")){
					pos = i;
				}
			}
			
			for(int i = pos+2; i < ex.size(); i++){
				termos.add(ex.get(i));
			}
			
			System.out.print(termos.get(0));
			
			boolean loop = true;
			int looper = 0;
			AvaliadorExpressoes aval = new AvaliadorExpressoes(); 
			while(loop){
				
				if(aval.expressaoBF(termos.get(looper))){
					fecharArquivo("Expressão " + looper + ": bem formada");
					
					int altura = aval.alturaArvore(termos.get(looper));
					fecharArquivo("Expressão tem altura: " + altura );
					
					int subs = aval.quantidadeSubexpressoes(termos.get(looper));
					fecharArquivo("Expressão tem: " + subs + " subesxpressoes" );
					
				}else {
					fecharArquivo("Expressão " + looper + ": mal formada");
				}
				
				looper++;
				
				if(looper == termos.size()){
					loop = false;
				}
			}
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	
	
	//Leitura do Arquivo
	public static List lerArquivo() throws IOException{
		
		//Seta o tipo de chars
		Charset ct = Charset.forName("utf-8");
		
		//Cria um lista para adicionar os termos
		List<String> ex = new ArrayList<String>();
		
		//Um for que varre todo o arquivo
		for (String line : Files.readAllLines(Paths.get("Expressao.in"), ct)) {
				ex.add(line);
		
		}
		
		//Retorna um array com todas as expressoes
		return ex;
		
	}
	
	//Escreve mudanças para o arquivo
	public static void fecharArquivo(String ex) throws IOException{
		
		//Inicializa novo objeto que registra no arquivo especificado
		Writer autor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Expressao.out"), "utf-8"));
		
		autor.write((String) ex + "\n");
	
		
		//fecha o objeto
		autor.close();
	}
	

}
