/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        
        if(getType(exp) == STRING) {
            states = generateNStates(exp.length() - 2);
            states.add(generateAcceptationState(token));
            
            for(int i=1; i < exp.length() - 1; i++)
                states.get(i-1).addTransition(new Transition(states.get(i), "" + exp.charAt(i)));
            return new Automata(states.get(0), states);
        } else if(getType(exp) == REGULAR) {
            states = generateNStates(1);
            states.add(generateAcceptationState(token));
            states.get(0).addTransition(new Transition(states.get(1), exp, Transition.REGULAR));
            return new Automata(states.get(0), states);
        }
        
        return null;
    }
    
    public ArrayList<State> generateNStates(int n) {
        ArrayList<State> states = new ArrayList<>();
        for(; n > 0; --n)
            states.add(new State());
        return states;
    }
    
    public State generateAcceptationState(String token) {
        State q = new State();
        q.setToken(token);
        q.setType(State.ACCEPT);
        return q;
    }
    
    public int getType(String exp){
        try{
            if(exp.charAt(0)=='\"' && exp.charAt(exp.length()-1)=='\"')
                return STRING;
            Pattern p = Pattern.compile(exp);
            return REGULAR;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return ILLEGAL;
        }
    }
    
}
