/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.helloworld;

import com.smeup.debugger.chromedev.simpledebugee.SimpleInterpreter;
import com.smeup.debugger.chromedev.simpledebugee.model.SimpleDebugeeContext;
import com.smeup.debugger.chromedev.simpledebugee.model.SimpleScript;

/**
 *
 * @author marco.lanari
 */
public class HelloWorldInterpreter implements SimpleInterpreter{
    
    private SimpleScript simpleScript = new SimpleScript("HelloWorld", "Hello world!!!");
    private SimpleDebugeeContext simpleDebugeeContext = new SimpleDebugeeContext();

    public HelloWorldInterpreter() {
        simpleDebugeeContext.setScriptId("HelloWorld");
        simpleDebugeeContext.setLineNumber(0);
        simpleDebugeeContext.setFunctionName("main");
    }
    
    @Override
    public SimpleScript getSimpleScript(String scriptId) {
        return simpleScript;
    }

    @Override
    public SimpleDebugeeContext getSimpleDebugeeContext() {
        return simpleDebugeeContext;
    }

    @Override
    public boolean stepOver() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
    @Override
    public boolean stepInto() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
    @Override
    public boolean stepOut() {
        System.out.println(simpleScript.getSource());
        return false;
    }

    @Override
    public boolean resume() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
}
