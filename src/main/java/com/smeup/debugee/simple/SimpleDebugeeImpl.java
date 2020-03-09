/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.simple;

import com.smeup.debugger.chromedev.DebugeeImpl;
import com.smeup.debugger.chromedev.simpledebugee.SimpleDebugee;
import com.smeup.debugger.chromedev.simpledebugee.SimpleInterpreter;
import java.io.IOException;

/**
 *
 * @author marco.lanari
 */
@DebugeeImpl (descr = "A simple debugee implementation", id = "simple")
public class SimpleDebugeeImpl extends SimpleDebugee{
    

    @Override
    public SimpleInterpreter createInterpreter(String scriptId) {
        try {
            return new SimpleInterpreterImpl(scriptId);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
