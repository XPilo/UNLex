/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexGenerator;

import types.Automata;

/**
 *
 * @author Camilo
 */
public class Token {
    private Automata automata=null; 
    private String type;
    
    public Token(String type, Automata Auto){
        this.type = type;
        automata = Auto;
    }

    /**
     * @param automata the automata to set
     */
    public void setAutomata(Automata automata) {
        this.automata = automata;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the automata
     */
    public Automata getAutomata() {
        return automata;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}
