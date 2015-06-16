/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import types.Automata;
import types.State;
import types.Transition;

/**
 *
 * @author Jose
 */
public class AutomataGenerator {
    
    public static int REGULAR = 0;
    public static int STRING = 1;
    public static int ILLEGAL = 2;
    
    public Automata generate(String exp, String token){
        ArrayList<State> states = new ArrayList<>();
        //ArrayList<Transition> transitions = new ArrayList<>();
        if(getType(exp)==STRING){
            for(int i=1; i < exp.length() - 1; i++){
                states.add(new State());
            }
            State q = new State();
            q.setToken(token);
            q.setType(State.ACCEPT);
            states.add(q);
            for(int i=1; i < exp.length() - 1; i++){
                states.get(i-1).addTransition(new Transition(states.get(i), "" + exp.charAt(i)));
            }
        }
        
        return new Automata(states.get(0));
    }
    
    public int getType(String exp){
        if(exp.charAt(0)=='\"' && exp.charAt(exp.length()-1)=='\"')
            return STRING;
        if(exp.charAt(0)=='[' && exp.charAt(exp.length()-1)==']')
            return REGULAR;
        return ILLEGAL;
    }
    
}
