/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Automata implements Serializable{
    
    public static int REGULAR = 0;
    public static int STRING = 1;
    public static int ILLEGAL = 2;
    
    private State initial;
    private State actual;
    private ArrayList<State> states;
    private int type;
    
    public Automata(State initial) {
        this.initial = initial;
        this.actual = initial;
        this.states = new ArrayList<State> ();
        this.type = 2;
    }

    public Automata(State initial, ArrayList<State> states, int type) {
        this.initial = initial;
        this.states = states;
        this.type = type;
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
        if(!actual.hasRegularTransition())
            for(int i = 0;i < tape.length() && actual != null; ++i)
                next(tape.charAt(i));
        else
            next(tape);
        return actual;
    }
    
    public void next(char car) {
        if(actual != null)
            actual = actual.nextTransition(car);
    }
    
    public void init() {
        this.actual = initial;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    private void next(String tape) {
        if(actual != null)
            actual = actual.nextTransition(tape);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    
}
