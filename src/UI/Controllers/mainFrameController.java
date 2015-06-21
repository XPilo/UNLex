/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controllers;
import generator.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexGenerator.LanguageRules;
import types.Automata;
import types.Grammar;

/**
 *
 * @author Camilo
 */
public class mainFrameController {

    public static void saveGrammar(File file, Grammar grammar) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(file))) {
            objectOutputStream.writeObject(grammar);
            objectOutputStream.flush();
        }catch (IOException ex) {
            Logger.getLogger(mainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Grammar loadGrammar(File file) {
        Grammar grammar = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(file));
            grammar = (Grammar)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(mainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grammar;
    }

    private String line;
    private String message;
    private  static LanguageRules lenguage = new LanguageRules();
    private  String[] words;
    
    public mainFrameController() {
    }
    
    public boolean correctLine(int lineNumber){
        int aux=words.length;
        if(3 == aux){
            if(words[1].equals("return")) return true;
            message = "Linea " + lineNumber + ": Error, se esperaba la palabra reservada return";
        }else{
            message = "Linea " + lineNumber + ": Error, se esperaba la definición del token, la parabra reservada return y la acción asignada";
        }
        return false;
    }
    
    public Grammar generateGrammar(String input){
        String[] lines = input.split("\n");
        Automata automata;
        Grammar grammar;
        ArrayList<Automata> strings = new ArrayList<>();
        ArrayList<Automata> regulars = new ArrayList<>();
        
        for(int i=0; i<lines.length ; i++){
            automata = lineProcess(lines[i],i + 1);
            if(automata!= null){
                if(automata.getType()== Automata.REGULAR)
                    regulars.add(automata);
                else
                    strings.add(automata);
            }             
            else{
                message = "Linea " + i+1 + ": Error de entrada";
                return null;
            }
        }
        grammar = new Grammar(strings);
        
        regulars.stream().forEach((a) -> {
            grammar.addAutomata(a);
        });
        message = "Analizador creado correctamente";
        return grammar;
    }
    
    
    public Automata lineProcess(String line, int lineNumber){
        words = line.split(" ");
        if(correctLine(lineNumber)){
            AutomataGenerator gen = new AutomataGenerator();
            return gen.generate(words[0], words[2]);
        }
        return null;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
