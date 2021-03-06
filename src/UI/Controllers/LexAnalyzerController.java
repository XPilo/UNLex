/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controllers;

import java.util.ArrayList;
import types.State;
import types.Grammar;

/**
 *
 * @author Jose
 */
public class LexAnalyzerController {

    public static ArrayList<String> analyzeInput(String input,Grammar grammar) {
        String[] lines = input.split("\n");
        ArrayList<String> output = new ArrayList<>();
        State state;
        for (int i=0; i<lines.length;i++) {
            String[] tokens = lines[i].split(" ");
            for(int j = 0;j < tokens.length; ++j) {
                state = grammar.validateInput(tokens[j]);
                if(state!=null){
                    if(state.getToken().contains("$"))
                        output.add(tokens[j]);
                    else
                        output.add(state.getToken() + " : " + tokens[j]);
                }else
                    output.add("Linea " + (i+1) + " entrada invalida");
            }
        }
        return output;
    }
    
}
