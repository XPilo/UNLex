/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.util.ArrayList;

/**
 *
 * @author Camilo Alaguna
 */
public class State {
    
    public static int REJECT = 1;
    public static int ACCEPT = 2;
    
    private ArrayList<Transition> transitions;
    protected int type;
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
    
}
