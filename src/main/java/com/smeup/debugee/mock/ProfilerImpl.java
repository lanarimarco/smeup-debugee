/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.mock;

import com.smeup.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.debugger.chromedev.model.Result;

/**
 *
 * @author marco.lanari
 */
public class ProfilerImpl implements Profiler{

    @Override
    public Result enable(int id) {
        return new Result(id);
    }
    
    
    
}
