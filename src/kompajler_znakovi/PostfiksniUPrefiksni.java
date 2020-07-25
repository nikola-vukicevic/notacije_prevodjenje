package kompajler_znakovi;

import java.util.*;

public class PostfiksniUPrefiksni {
	
	public static String Pretvaranje(String s) {
		Stack<String> stek = new Stack<String>();
		int           i, d = s.length();
		
		for (i = 0; i < d; i++) {
			char c = s.charAt(i);
			
			if (c >= 'a' && c <= 'z') {
				stek.push("" + c);
				continue;
			}
			
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				String o_2 = stek.pop();
				String o_1 = stek.pop();
				stek.push(c + o_1 + o_2);
			}
		}
		
		return stek.pop();
	}
	
	public static void main(String[] args) {
		Scanner Ucitavac   = new Scanner(System.in);
		String  Postfiksni = Ucitavac.nextLine();
		System.out.println(Pretvaranje(Postfiksni));
		Ucitavac.close();
	}
}
