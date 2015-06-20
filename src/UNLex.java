
import generator.AutomataGenerator;
import lexGenerator.Token;
import types.Automata;
import types.State;
import types.Transition;
import lexGenerator.*;

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
//        // states definiton
//        // q0 definition
//        State q0 = new State();
//        q0.setType(State.ACCEPT);
//        q0.setToken("ACEPTED");
//        // q1 definition
//        State q1 = new State();
//        q1.setType(State.REJECT);
//        
//        // transition definition
//        Transition q0a = new Transition(q0, "[a-z]");
//        Transition q0b = new Transition(q1, "1");
//        q0.addTransition(q0a);
//        q0.addTransition(q0b);
//        
//        Transition q1a = new Transition(q1, "2");
//        Transition q1b = new Transition(q0, "[a-z]");
//        q1.addTransition(q1a);
//        q1.addTransition(q1b);
//        
//        // Automata
//        Automata automata = new Automata(q0);
//        State res = automata.processTape("abcerft");
//        String token;
//        if(res != null){
//            token = (res.getToken() != null)? res.getToken() : "";
//            System.out.println("Token: " + token);
//        }else System.out.println("Caracter no valido");
//        AutomataGenerator generator = new AutomataGenerator();
//        String exp="[a-z]*";
//        String accept="Realizar acción";
//        Automata auto = generator.generate(exp, accept);
//        auto.processTape("casA");
//        if(auto.getActualState() != null)
//            System.out.println(auto.getActualState().getToken());
//        else
//            System.out.println("Error léxico");
        AutomataGenerator generator = new AutomataGenerator();
        String exp="[a-z]*";
        String accept="Realizar acción";
        Automata auto = generator.generate(exp, accept);
        lenguageRules len = new lenguageRules();
        len.addToken("ID", auto);
        //---//
        exp="OTRA";
        Automata auto1 = generator.generate(exp, accept);
        len.addToken("reserv", auto1);
        len.addToken(exp, auto1);
        
        if(len.isValidToken("variable"))
            System.out.println(len.getTokenType("variable"));
        if(len.isValidToken("OTRA"))
            System.out.println(len.getTokenType("OTRA"));
    }
    
}
