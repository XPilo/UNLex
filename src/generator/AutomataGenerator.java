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
    
        
    public class BadSTringException extends Exception {
        public BadSTringException(String msg) {
            super(msg);
        }
    }
    
    public Automata generate(String exp, String token){
        ArrayList<State> states = new ArrayList<>();
        
        if(getType(exp) == Automata.STRING) {
            states = generateNStates(exp.length() - 2);
            states.add(generateAcceptationState(token));
            
            for(int i=1; i < exp.length() - 1; i++)
                states.get(i-1).addTransition(new Transition(states.get(i), "" + exp.charAt(i)));
            return new Automata(states.get(0), states,Automata.STRING);
            
        } else if(getType(exp) == Automata.REGULAR) {
            states = generateNStates(1);
            states.add(generateAcceptationState(token));
            states.get(0).addTransition(new Transition(states.get(1), exp, Transition.REGULAR));
            return new Automata(states.get(0), states, Automata.REGULAR);
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
                return Automata.STRING;
            Pattern p = Pattern.compile(exp);
            return Automata.REGULAR;
        }catch (Exception ex){
            String message = ex.toString().replaceAll("java.util.regex.PatternSyntaxException: ", "");
            System.out.println(message);
            return Automata.ILLEGAL;
        }
    }
    
}
