/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Camilo Alaguna
 */
public class State {
    
    public static int REJECT = 1;
    public static int ACCEPT = 2;
    
    private ArrayList<Transition> transitions;
    private int type;
    private String token;
    
    public State() {
        this.transitions = new ArrayList<Transition>();
        this.type = REJECT;
        this.token = null;
    }

    public State nextTransition(char car) {
        for(Transition transition: transitions)
            if(transition.condition.indexOf(car) >= 0)
                return transition.nextState;
        return null;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return this.token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }
    
    public boolean hasRegularTransition() {
        return (this.transitions.size() == 1) && (this.transitions.get(0).type == Transition.REGULAR);
    }

    public State nextTransition(String tape) {
        Transition transition = this.transitions.get(0);
        Pattern p = Pattern.compile(transition.condition);
        Matcher m = p.matcher(tape);
        if(m.matches())
            return transition.nextState;
        return null;
    }
    
}
