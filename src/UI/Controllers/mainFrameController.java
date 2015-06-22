/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controllers;
import generator.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import types.Automata;
import types.Grammar;

/**
 *
 * @author Camilo
 */
public class mainFrameController {

    public static void saveGrammar(File file, Grammar grammar) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(grammar);
            objectOutputStream.flush();
            objectOutputStream.close();
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
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(mainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grammar;
    }
    public static void saveText(String file, String text) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
            pw.println(text);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public static String loadText(File file) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String text="";
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (file.getPath());
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String line;
           while((line=br.readLine())!=null)
              text += line + "\n";
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
        return text;
    }

    private String line;
    private String message;
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
