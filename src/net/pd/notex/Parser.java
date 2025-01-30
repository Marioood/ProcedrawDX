package net.pd.notex;

import java.util.Hashtable;
import java.util.ArrayList;
import net.pd.notex.*;

public class Parser {
	public ArrayList<Symbol> display = new ArrayList<Symbol>();
	Hashtable<Character, Tokens> tokenLookup = new Hashtable<Character, Tokens>();
	
	public Parser() {
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
	}
	
	public ArrayList<Symbol> parse(String code) {
		//parses the script (called "NoTeX") used to render the nodes
		
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
			
			if(tokenLookup.get(currentChar) != null) {
				//avoid unecessary splitting
				if(currentChar == 'i' || currentChar == '$') {
					if(code.charAt(i + 1) != '(') {
						currentToken += currentChar;
						continue;
					}
				}
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
		//parsing
		ArrayList<Symbol> display = new ArrayList<Symbol>();
		//add start cap
		display.add(new SymbolVector(lexedTokens.get(0)));
		
		for(int i = 1; i < lexedTokens.size() - 1; i++) {
			String tolkien = lexedTokens.get(i);
			//construct symbols
			switch(tolkien) {
				case "$":
					String symbol = "";
					i++;
					i++;
					symbol = lexedTokens.get(i);
					i++;
					System.out.println(symbol);
					display.add(new SymbolVector(symbol));
					break;
				case "'":
					String text = "";
					i++;
					text = lexedTokens.get(i);
					i++;
					System.out.println(text);
					display.add(new SymbolText(text));
					break;
				case "i":
					int index;
					String type = "any";
					Symbol name = null;
					i++;
					i++;
					//get index
					index = Integer.valueOf(lexedTokens.get(i));
					i++;
					//get type if it exists
					if(lexedTokens.get(i).equals(":")) {
						i++;
						type = lexedTokens.get(i);
						i++;
						//get name if it exists
						if(lexedTokens.get(i).equals(":")) {
							i++;
							//is it a vector symbol? otherwise treat it like plain text
							if(lexedTokens.get(i).equals("$")) {
								i++;
								i++;
								name = new SymbolVector(lexedTokens.get(i));
								i++;
							} else {
								name = new SymbolText(lexedTokens.get(i));
							}
							i++;
						}
					}
					System.out.println(index + ":" + type + ":" + name);
					//todo: this is wrong!!
					display.add(new SymbolInput(index, name));
					break;
				default:
					System.out.println("WARNING: mystery token \"" + tolkien + "\"");
			}
		}
		//add end cap
		display.add(new SymbolVector(lexedTokens.get(lexedTokens.size() - 1)));
		
		return display;
	}
}