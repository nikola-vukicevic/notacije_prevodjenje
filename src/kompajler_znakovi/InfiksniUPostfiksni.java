package kompajler_znakovi;

import java.util.*;

public class InfiksniUPostfiksni {
	
	public static void obradaTokenaOperand(Queue<Character> red, char znak) {
		red.add(znak);
	}
	
	public static void obradaTokenaOtvorenaZagrada(Stack<Character> stek, char znak) {
		stek.push(znak);
	}
	
	public static void obradaTokenaPlusMinus(Stack<Character> stek, Queue<Character> red, char znak) {
		if (stek.isEmpty() || stek.peek() == '(') {
			stek.push(znak);
			return;
		}
		
		red.add(stek.pop());
		stek.push(znak);
		return;
	}
	
	public static void obradaTokenaPutaKroz(Stack<Character> stek, Queue<Character> red, char znak){
		if (stek.isEmpty() || stek.peek() == '(' || stek.peek() == '+' || stek.peek() == '-') {
			stek.push(znak);
			return;
		}
		
		red.add(stek.pop());
		stek.push(znak);
		return;
	}
	
	public static void obradaTokenaZatvorenaZagrada(Stack<Character> stek, Queue<Character> red) {
		if (stek.isEmpty()) return;
		char gornji = stek.pop();
		while (gornji != '(') {
			red.add(gornji);
			gornji = stek.pop();
		}
	}
	
	public static void praznjenjeStekaURed(Stack<Character> stek, Queue<Character> red) {
		while (!stek.isEmpty()) {
			red.add(stek.pop());
		}
	}
	
	public static String praznjenjeRedaUString(Queue<Character> red) {
		String s_red = "";
		while (!red.isEmpty()) {
			s_red += red.remove();
		}
		return s_red;
	}
	
	public static String pretvaranjeUPostfiksnuNotaciju(String s) {
		Stack<Character> stek = new Stack<Character>();
		Queue<Character> red  = new LinkedList<Character>();
		int i, d = s.length();
		
		for (i = 0; i < d; i++) {
			char znak = s.charAt(i);
			if (znak >= 'a' && znak <= 'z') {
				obradaTokenaOperand(red, znak);
				continue;
			}
			switch(znak) {
				case '(': obradaTokenaOtvorenaZagrada(stek, '(');  break;
				case '+': obradaTokenaPlusMinus(stek, red, '+');   break;
				case '-': obradaTokenaPlusMinus(stek, red, '-');   break;
				case '*': obradaTokenaPutaKroz(stek, red, '*');    break;
				case '/': obradaTokenaPutaKroz(stek, red, '/');    break;
				case ')': obradaTokenaZatvorenaZagrada(stek, red); break;
				default: break;
			}
		}
		
		praznjenjeStekaURed(stek, red);
		return praznjenjeRedaUString(red);
	}
		
	public static void main(String[] args) {
		Scanner ucitavac      = new Scanner(System.in);
		String str_infiksni   = ucitavac.nextLine();
		String str_postfiksni = pretvaranjeUPostfiksnuNotaciju(str_infiksni);
		System.out.print(str_postfiksni);
		ucitavac.close();
	}
}
