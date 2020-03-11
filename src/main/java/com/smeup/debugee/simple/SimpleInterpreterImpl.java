/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.simple;

import com.smeup.debugger.chromedev.simpledebugee.SimpleInterpreter;
import com.smeup.debugger.chromedev.simpledebugee.model.SimpleDebugeeContext;
import com.smeup.debugger.chromedev.simpledebugee.model.SimpleScript;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple interpreter, interprets only a source file.
 * @author marco.lanari
 */
public class SimpleInterpreterImpl implements SimpleInterpreter{
  
    private static final Logger LOGGER = Logger.getLogger(SimpleInterpreterImpl.class.getName());

    private final SimpleScript simpleScript;
    private final SimpleDebugeeContext simpleDebugeeContext;
    private final List<String> statements; 
    
    

    public SimpleInterpreterImpl(String scriptId) throws IOException {
        simpleScript = SimpleScript.createBy(scriptId, getClass().getResource("/simple/" + scriptId));
        simpleDebugeeContext = new SimpleDebugeeContext();
        simpleDebugeeContext.setFunctionName("main");
        simpleDebugeeContext.setLineNumber(0);
        simpleDebugeeContext.setScriptId(scriptId);
        statements = Arrays.asList(simpleScript.getSource().split("\n"));
    }
    
    

    @Override
    public SimpleScript getSimpleScript(String scriptId) {
        //interprets only a source file
        return simpleScript;
    }

    @Override
    public SimpleDebugeeContext getSimpleDebugeeContext() {
        return simpleDebugeeContext;
    }

    @Override
    public boolean stepOver() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebugeeContext.getLineNumber()));
        simpleDebugeeContext.setLineNumber(simpleDebugeeContext.getLineNumber()+1);
        return simpleDebugeeContext.getLineNumber() < statements.size();   
    }
    
    @Override
    public boolean stepInto() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebugeeContext.getLineNumber()));
        simpleDebugeeContext.setLineNumber(simpleDebugeeContext.getLineNumber()+1);
        return simpleDebugeeContext.getLineNumber() < statements.size();   
    }

    @Override
    public boolean stepOut() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebugeeContext.getLineNumber()));
        simpleDebugeeContext.setLineNumber(simpleDebugeeContext.getLineNumber()+1);
        return simpleDebugeeContext.getLineNumber() < statements.size();   
    }

    @Override
    public boolean resume() {
        do{}while(stepOver());
        return false;
    }
      
}
