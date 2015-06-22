
import UI.Views.mainFrame;
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
        mainFrame main = new mainFrame();
        main.setLocationRelativeTo(null);
        main.setTitle("UNLEX");
        main.setVisible(true);
    }
    
}
