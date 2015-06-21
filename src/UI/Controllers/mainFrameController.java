/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controllers;
import generator.*;
import lexGenerator.lenguageRules;
import types.Automata;

/**
 *
 * @author Camilo
 */
public class mainFrameController {

    private String line;
    private String message;
    private  static lenguageRules lenguage = new lenguageRules();
    private  String[] words;
    
    public mainFrameController() {
    }
    
    public boolean correctLine(){
        int aux=words.length;
        if(3 == aux){
            if(words[1].equals("return")) return true;
            message = "Error, esperada la palabra reservada return.";
        }else{
            message = "Error, se espera la definición del token, la parabra reservada return y la acción asiganada.";
        }
        return false;
    }
    
    public boolean lineProcess(String line){
        words = line.split(" ");
        if(correctLine()){
            AutomataGenerator gen = new AutomataGenerator();
            Automata automata = gen.generate(words[0], words[2]);
            lenguage.addToken(words[0], automata);
            return true;
        }
        return false;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
