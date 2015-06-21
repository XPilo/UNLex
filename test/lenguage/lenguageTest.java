/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenguage;

import generator.AutomataGenerator;
import lexGenerator.LanguageRules;
import org.junit.Assert;
import org.junit.Test;
import types.Automata;

/**
 *
 * @author Camilo
 */
public class lenguageTest {
    
    @Test
    public void lengage(){
        AutomataGenerator generator = new AutomataGenerator();
        String exp="[a-z]*";
        String accept="Realizar acción";
        Automata auto = generator.generate(exp, accept);
        LanguageRules len = new LanguageRules();
        len.addToken("ID", auto);
        //---//
        exp="OTRA";
        Automata auto1 = generator.generate(exp, accept);
        len.addToken("reserv", auto1);
        len.addToken(exp, auto1);
        
        if(len.isValidToken("variable"))
            Assert.assertEquals("ID",len.getTokenType("variable"));
        if(len.isValidToken("OTRA"))
            Assert.assertEquals("reserv",len.getTokenType("OTRA"));
    }
}
