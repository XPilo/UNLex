/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

/**
 *
 * @author Camilo Alaguna
 */
public class Transition {
    
    public State nextState;
    public String condition;

    public Transition(State nextState, String condition) {
        this.nextState = nextState;
        this.condition = condition;
    }
    
}
