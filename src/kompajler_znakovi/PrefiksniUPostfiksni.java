package kompajler_znakovi;

import java.util.*;

public class PrefiksniUPostfiksni {
	
	public static String Pretvaranje(String s) {
		Stack<String> stek = new Stack<String>();
		int           i, d = s.length();
		
		for (i = d - 1; i >= 0; i--) {
			char c = s.charAt(i);
			
			if (c >= 'a' && c <= 'z') {
				stek.push("" + c);
				continue;
			}
			
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				String o_1 = stek.pop();
				String o_2 = stek.pop();
				stek.push(o_1 + o_2 + c);
			}
		}
		
		return stek.pop();
	}
	
	public static void main(String[] args) {
		Scanner Ucitavac  = new Scanner(System.in);
		String  Prefiksni = Ucitavac.nextLine();
		System.out.println(Pretvaranje(Prefiksni));
		Ucitavac.close();
	}
}
