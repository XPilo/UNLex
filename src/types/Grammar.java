/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Grammar {
    private ArrayList<Automata> grammar;

    public Grammar() {
        this.grammar = new ArrayList<>();
    }
    
    public Grammar(ArrayList<Automata> grammar) {
        this.grammar = grammar;
    }
    
    public ArrayList<Automata> getGrammar() {
        return grammar;
    }

    public void setGrammar(ArrayList<Automata> grammar) {
        this.grammar = grammar;
    }
    
    public State validateInput(String input){
        State actual = null;
        for(Automata a: grammar){
            actual = a.processTape(input);
            if(actual != null)
                break;
        }
        return actual;
    }
}
