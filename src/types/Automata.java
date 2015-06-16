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
    
    private State initial;
    private State actual;
    
    public Automata(State initial) {
        this.initial = initial;
        this.actual = initial;
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
        init();
        for(int i = 0;i < tape.length() && actual != null; ++i)
            next(tape.charAt(i));
        return actual;
    }
    
    public void next(char car) {
        if(actual != null)
            actual = actual.nextTransition(car);
    }
    
    public void init() {
        this.actual = initial;
    }
    
}
