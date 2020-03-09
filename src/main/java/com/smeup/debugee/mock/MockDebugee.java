/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.mock;

import com.smeup.debugger.chromedev.Debugee;
import com.smeup.debugger.chromedev.DebugeeImpl;
import com.smeup.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.debugger.chromedev.messageshandlers.Runtime;
import java.io.IOException;

/**
 * Fa partire l'interprete
 * @author marco.lanari
 */
@DebugeeImpl (descr = "A mock debugee interpreter for experiments", id = "mock")
public class MockDebugee implements Debugee<MockInterpreter>{

    @Override
    public MockInterpreter createInterpreter(String scriptId) {
        try {
            return new MockInterpreter(scriptId);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Debugger createDebugger(MockInterpreter interpreter) {
        return new DebuggerImpl(interpreter);
    }

    @Override
    public Profiler createProfiler(MockInterpreter interpreter) {
        return new ProfilerImpl();
    }

    @Override
    public Runtime createRuntime(MockInterpreter interpreter) {
        return new RuntimeImpl(interpreter);
    }

   
   
}
