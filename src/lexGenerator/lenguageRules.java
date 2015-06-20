/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexGenerator;

import java.util.ArrayList;
import types.Automata;
import types.State;

/**
 *
 * @author Camilo
 */
public class lenguageRules {
     private ArrayList<Token> Tokens = new ArrayList();
     private static Token foundToken;     
     
     public void addToken(String type, Automata auto){
         Token token = new Token(type,auto);
         Tokens.add(token);
     }
     
     public boolean isValidToken(String word){
         for(Token token: Tokens){
             State state = token.getAutomata().processTape(word);
             if (state != null){
                 foundToken = token;
                 return true;                 
             }
         }
         return false;
     }
     
     public String getTokenType(String word){
         if(isValidToken(word)) return foundToken.getType();
         return null;
     }
}
