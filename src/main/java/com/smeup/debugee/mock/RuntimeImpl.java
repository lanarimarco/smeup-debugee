/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.mock;

import com.smeup.debugger.chromedev.messageshandlers.Runtime;
import com.smeup.debugger.chromedev.model.Properties;
import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.methods.DebugeeMethodFactory;
import com.smeup.debugger.chromedev.model.params.GetPropertiesParams;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author marco.lanari
 */
public class RuntimeImpl implements Runtime {

    private final MockInterpreter interpreter;

    public RuntimeImpl(MockInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public Result enable(int id) {
        return new Result(id);
    }

    @Override
    public Result runIfWaitingForDebugger(int id) {
        Result result = new Result(id);
        result.afterSend(debugeeEventDispatcher -> {
            int contextId = 1;
            try {
                //Nodifico che ho creato già il runtime di esecuzione
                debugeeEventDispatcher.notifyRuntimeExecutionContextCreated(DebugeeMethodFactory.
                        createRuntimeExecutionContextCreated(contextId, "", "Main context"));

                debugeeEventDispatcher.notifyDebuggerScriptParsed(
                        DebugeeMethodFactory.createDebuggerScriptParsed(
                                interpreter.getScriptId(), interpreter.getSourceUrl().toString(), 
                                interpreter.getSource(), contextId));
                debugeeEventDispatcher.notifyDebuggerPaused(DebugeeMethodFactory.
                        createDebuggerPaused(interpreter.getScriptId(), 
                                interpreter.getSourceUrl().toString(),
                                interpreter.getFunctionName(), interpreter.getLineNumber()));
            } catch (NoSuchAlgorithmException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return result;
    }

    @Override

    public Result<Properties> getProperties(int id, GetPropertiesParams params) {
        return new Result(id, new Properties());
    }

}
