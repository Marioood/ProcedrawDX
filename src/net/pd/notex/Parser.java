package net.pd.notex;

import java.util.Hashtable;
import java.util.ArrayList;
import net.pd.notex.*;

public class Parser {
	public ArrayList<Symbol> display = new ArrayList<Symbol>();
	
	public Parser() {
		//String code = "( $(times) $(question) )";
		String code = "( 'boolean ' $(question) ' output 0 ' $(colon) ' output 1' )";
		Hashtable<Character, Tokens> tokenLookup = new Hashtable<Character, Tokens>();
		//TODO: probably remove the token.CONSTANT thingies
		tokenLookup.put('(', Tokens.PARENTHESIS_LEFT);
		tokenLookup.put(')', Tokens.PARENTHESIS_RIGHT);
		tokenLookup.put('<', Tokens.ARROW_LEFT);
		tokenLookup.put('>', Tokens.ARROW_RIGHT);
		tokenLookup.put(':', Tokens.COLON);
		tokenLookup.put('i', Tokens.INPUT);
		tokenLookup.put('\'', Tokens.QUOTE);
		tokenLookup.put('_', Tokens.VARIADIC);
		tokenLookup.put('$', Tokens.SYMBOL);
		//lexer
		ArrayList<String> lexedTokens = new ArrayList<String>();
		String currentToken = "";
		boolean inString = false;
		
		for(int i = 0; i < code.length(); i++) {
			char currentChar = code.charAt(i);
			//dont skip whitespace if we're lexing a string
			if(currentChar == '\'') inString = !inString;
			//skip whitespace
			if(currentChar == ' ' && !inString) {
				continue;
			}
			//System.out.println(currentToken);
			
			if(tokenLookup.get(currentChar) != null) {
				//dont add empty strings
				if(!currentToken.equals("")) {
					lexedTokens.add(currentToken);
				}
				lexedTokens.add("" + currentChar);
				currentToken = "";
				continue;
			} else if(i == code.length() - 1) {
				lexedTokens.add(currentToken);
				continue;
			}

			currentToken += currentChar;
		}
		for(int i = 0; i < lexedTokens.size(); i++) {
			System.out.println(lexedTokens.get(i));
		}
		System.out.println("-----");
		
		//add start cap
		this.display.add(new SymbolVector(lexedTokens.get(0)));
		
		for(int i = 1; i < lexedTokens.size() - 1; i++) {
			String tolkien = lexedTokens.get(i);
			//TODO: switch statement
			if(tolkien.equals("$")) {
				String symbol = "";
				i++;
				i++;
				//clean up divided symbol names (ex: quest - i - on) from bad lexer
				while(!lexedTokens.get(i).equals(")")) {
					symbol += lexedTokens.get(i);
					i++;
				}
				System.out.println(symbol);
				this.display.add(new SymbolVector(symbol));
			} else if(tolkien.equals("'")) {
				String text = "";
				i++;
				//clean up divided symbol names (ex: quest - i - on) from bad lexer
				while(!lexedTokens.get(i).equals("'")) {
					text += lexedTokens.get(i);
					i++;
				}
				System.out.println(text);
				this.display.add(new SymbolText(text));
			}
		}
		//add end cap
		this.display.add(new SymbolVector(lexedTokens.get(lexedTokens.size() - 1)));
	}
}