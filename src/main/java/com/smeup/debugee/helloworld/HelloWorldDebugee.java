/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.helloworld;

import com.smeup.debugger.chromedev.DebugeeImpl;
import com.smeup.debugger.chromedev.simpledebugee.SimpleDebugee;
import com.smeup.debugger.chromedev.simpledebugee.SimpleInterpreter;

/**
 *
 * @author marco.lanari
 */
@DebugeeImpl (id = "helloworld", descr = "My debugee implementation")
public class HelloWorldDebugee extends SimpleDebugee{

    public HelloWorldDebugee() {
    }

    @Override
    public SimpleInterpreter createInterpreter(String scriptId) {
        return new HelloWorldInterpreter();
    }
    
    
    
}
