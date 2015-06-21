/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.io.Serializable;

/**
 *
 * @author Camilo Alaguna
 */
public class Transition implements Serializable{
    public static int STRING = 0;
    public static int REGULAR = 1;
    
    public State nextState;
    public String condition;
    public int type;
    

    public Transition(State nextState, String condition) {
        this.nextState = nextState;
        this.condition = condition;
        this.type = STRING;
    }

    public Transition(State nextState, String condition, int type) {
        this.nextState = nextState;
        this.condition = condition;
        this.type = type;
    }
    
}
