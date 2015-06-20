/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import types.Automata;
import types.State;
import types.Grammar;

/**
 *
 * @author Jose
 */
public class AutomataGeneratorTest {
    
    public AutomataGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGrammar() {
        System.out.println("grammar");
        AutomataGenerator automataGenerator = new AutomataGenerator();
        Grammar grammarInstance = new Grammar();
        ArrayList<Automata> grammar = new ArrayList<>();
        grammar.add(automataGenerator.generate("\"print\"", "print"));
        grammar.add(automataGenerator.generate("\"exit\"", "exit"));
        grammar.add(automataGenerator.generate("[a-zA-z]*", "$"));
        grammarInstance.setGrammar(grammar);
        State resultState;
        String expToken;
        resultState = grammarInstance.validateInput("print");
        expToken = resultState.getToken();
        assertEquals(expToken, "print");
        resultState = grammarInstance.validateInput("exit");
        expToken = resultState.getToken();
        assertEquals(expToken, "exit");
        resultState = grammarInstance.validateInput("variAble");
        expToken = resultState.getToken();
        assertEquals(expToken, "$");
        resultState = grammarInstance.validateInput("1736a");
        assertEquals(resultState, null);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of generate method, of class AutomataGenerator.
     */
    @Test
    public void testGenerate() {
        System.out.println("generate");
        String exp = "\"print\"";
        String token = "acción";
        AutomataGenerator instance = new AutomataGenerator();
        String expResult = "acción";
        Automata automata = instance.generate(exp, token);
        String result = automata.processTape("print").getToken();
        assertEquals(expResult, result);
        State expResultState = null;
        State resultState;
        resultState = automata.processTape("\"otra\"");
        assertEquals(expResultState, resultState);
        exp = "[a-zA-z]*";
        automata = instance.generate(exp, token);
        result = automata.processTape("CarrO").getToken();
        assertEquals(expResult, result);
        resultState = automata.processTape("1284");
        assertEquals(expResultState, resultState);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
       
    /**
     * Test of getType method, of class AutomataGenerator.
     */
    //@Test
    public void testGetType() {
        System.out.println("getType");
        String exp = "";
        AutomataGenerator instance = new AutomataGenerator();
        int expResult = 0;
        int result = instance.getType(exp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
