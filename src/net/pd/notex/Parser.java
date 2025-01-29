package net.pd.notex;

import java.util.Hashtable;
import java.util.ArrayList;

public class Parser {
	
	public Parser() {
		String code = "< i(0 : type : text) 'text 2kki' >";
		Hashtable<Character, Tokens> tokenLookup = new Hashtable<Character, Tokens>();
		
		tokenLookup.put('(', Tokens.PARENTHESIS_LEFT);
		tokenLookup.put(')', Tokens.PARENTHESIS_RIGHT);
		tokenLookup.put('<', Tokens.ARROW_LEFT);
		tokenLookup.put('>', Tokens.ARROW_RIGHT);
		tokenLookup.put(':', Tokens.COLON);
		tokenLookup.put('i', Tokens.INPUT);
		tokenLookup.put('\'', Tokens.QUOTE);
		tokenLookup.put('_', Tokens.VARIADIC);
		tokenLookup.put('$', Tokens.SYMBOL);
		
		ArrayList<String> currentTokens = new ArrayList<String>();
		String currentToken = "";
		
		for(int i = 0; i < code.length(); i++) {
			char currentChar = code.charAt(i);
			//skip whitespace
			if(currentChar == ' ') {
				continue;
			} else if(tokenLookup.get(currentChar) != null) {
				currentTokens.add("" + currentChar);
				currentToken = "";
				continue;
			}
			currentToken += currentChar;
			//System.out.println(currentToken);
			
			if(tokenLookup.get(currentChar) != null) {
				currentTokens.add(currentToken);
				currentToken = "";
			} else if(i == code.length() - 1) {
				currentTokens.add(currentToken);
			}/* else {
				String currentSubToken = "";
				for(int sub = currentToken.length() - 1; sub > 0; sub--) {
					currentSubToken += currentToken.charAt(sub);
					if(tokenLookup.get(currentSubToken) != null) {
						currentTokens.add(currentSubToken);
						
						String currentFrontToken = "";
						for(int f = 0; f < sub - currentToken.length(); f++) {
							currentFrontToken += currentToken.charAt(sub); 
						}
						currentTokens.add(currentFrontToken);
						break;
					}
				}
			}*/
		}
		System.out.println("-----");
		for(int i = 0; i < currentTokens.size(); i++) {
			System.out.println(currentTokens.get(i));
		}
	}
}