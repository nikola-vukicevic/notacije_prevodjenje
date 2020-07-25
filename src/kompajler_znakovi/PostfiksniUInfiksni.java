package kompajler_znakovi;

import java.util.*;

public class PostfiksniUInfiksni {

	public static String Prebacivanje(String s) {
		Stack<Token>     stek        = new Stack<Token>();
		Queue<Character> izrazTokeni = new LinkedList<Character>();
		int i, d = s.length();
		for (i = 0; i < d; i++) izrazTokeni.add(s.charAt(i));
		
		while (!izrazTokeni.isEmpty()) {
			char Token = izrazTokeni.remove();
			
			if ((Token >= 'a' && Token <= 'z') || (Token >= 'A' && Token <= 'Z')){
				Token i_pom = new Token("" + Token, 0);
				stek.push(i_pom);
				continue;
			}
			
			if (Token == '+' || Token == '-' || Token == '*' || Token == '/') {
				Token i_2  = stek.pop();
				Token i_1  = stek.pop();
				Token novi = new Token("", 0);
				
				int trenutni_prioritet = (Token == '+' || Token == '-')? 1 : 2;
				
				if(trenutni_prioritet > i_1.Prioritet && i_1.Prioritet > 0)
					i_1.Zapis= "(" + i_1.Zapis + ")"; 
				
				if(trenutni_prioritet > i_2.Prioritet && i_2.Prioritet > 0)
					i_2.Zapis= "(" + i_2.Zapis + ")";
				
				novi.Zapis = i_1.Zapis + Token + i_2.Zapis;
				novi.Prioritet = trenutni_prioritet;
				
				stek.push(novi);
			}
		}
		
		return stek.pop().Zapis;
	}
	
	public static void main(String[] args) {
		Scanner Ucitavac   = new Scanner(System.in);
		String  Postfiksni = Ucitavac.nextLine();
		String  Infiksni   = Prebacivanje(Postfiksni);
		System.out.println(Infiksni);
		Ucitavac.close();
	}

}
