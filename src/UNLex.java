
import types.Automata;
import types.State;
import types.Transition;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Camilo Alaguna
 */
public class UNLex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // states definiton
        // q0 definition
        State q0 = new State();
        q0.setType(State.ACCEPT);
        q0.setToken("ACEPTED");
        // q1 definition
        State q1 = new State();
        q1.setType(State.REJECT);
        
        // transition definition
        Transition q0a = new Transition(q0, "a");
        Transition q0b = new Transition(q1, "b");
        q0.addTransition(q0a);
        q0.addTransition(q0b);
        
        Transition q1a = new Transition(q1, "a");
        Transition q1b = new Transition(q0, "b");
        q1.addTransition(q1a);
        q1.addTransition(q1b);
        
        // Automata
        Automata automata = new Automata(q0);
        State res = automata.processTape("aaaabab");
        String token = (res.getToken() != null)? res.getToken() : "";
        
        System.out.println("Token: " + token);
    }
    
}
