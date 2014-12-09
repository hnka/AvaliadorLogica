package llf.Input;

import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		
		String exp = "(((.(x))*(y)).(z))";
		
		String stm = "+";
		
		String resul = picotador(exp);
		System.out.println(resul);
		
	}
	
	public static String picotador(String exp){
		int check = exp.length() - 1;
		boolean loop = true;
		int i = 0;
		String exp1 = "";
		while(loop){
			
			int back = (exp.length() - 1) - i;
			check = check - i;
			
			String ida = "" + exp.charAt(i);
			String volta = "" + exp.charAt(back);
			
			Pattern stm = Pattern.compile("[\\(][-][\\(].*[\\)][\\)]");
			
			System.out.println(ida);
			System.out.println(volta);
			
			boolean b = ida.equals(stm) ;
			
			if(check <= 0 && (b)){
				exp1 = exp.substring(0, i);
				String exp2 = exp.substring(i+1, exp.length());
				
				System.out.println(exp1);
				System.out.println(exp2);
				
				picotador(exp1);
				
				loop = false;
			} else if(check <= 0){
				exp1 = exp.substring(0, i);
				loop = false;
			} 
			
			i++;
			
			
		}
		return exp1;	
	}

}
