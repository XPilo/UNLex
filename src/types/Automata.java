/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

/**
 *
 * @author Jose
 */
public class Automata {
    
   // private State states[];
    private State initial;
    private State actual;
    
    public Automata(State initial) {
        this.initial = initial;
    }
    
    public void setInitial(State initial) {
        this.initial = initial;
    }

    public State getInitialState() {
        return initial;
    }

    public State getActualState() {
        return actual;
    }
    
    public State processTape(String tape) {
        actual = initial;
        for(int i = 0;i < tape.length() && actual != null; ++i)
            actual = actual.nextTransition(tape.charAt(i));
        return actual;
    }
    
}
