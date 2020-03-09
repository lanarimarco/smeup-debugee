/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.mock;

import com.smeup.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.ScriptSource;
import com.smeup.debugger.chromedev.model.methods.DebugeeMethodFactory;
import com.smeup.debugger.chromedev.model.methods.DebuggerResumed;
import com.smeup.debugger.chromedev.model.params.DebuggerEnableParams;
import com.smeup.debugger.chromedev.model.params.GetScriptSourceParams;
import com.smeup.debugger.chromedev.model.params.SetAsyncCallStackDepthParams;
import com.smeup.debugger.chromedev.model.params.SetBlackboxPatternsParams;
import com.smeup.debugger.chromedev.model.params.SetPauseOnExceptionParams;
import java.util.logging.Logger;

/**
 *
 * @author marco.lanari
 */
public class DebuggerImpl implements Debugger {

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(DebuggerImpl.class.getName());

    private final MockInterpreter interpreter;

    public DebuggerImpl(MockInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public Result enable(int id, DebuggerEnableParams params) {
        return new Result(id);
    }

    @Override
    public Result setPauseOnExceptions(int id, SetPauseOnExceptionParams params) {
        return new Result(id);
    }

    @Override
    public Result setAsyncCallStackDepth(int id, SetAsyncCallStackDepthParams setAsyncCallStackDepthParams) {
        return new Result(id);
    }

    @Override
    public Result setBlackboxPatterns(int id, SetBlackboxPatternsParams params) {
        return new Result(id);
    }

    @Override
    public Result<ScriptSource> getScriptSource(int id, GetScriptSourceParams params) {
        return new Result<>(id, new ScriptSource(interpreter.getSource()));
    }

    @Override
    public Result stepOver(int id) {
        if (interpreter.interpret()) {
            Result result = new Result(id);
            result.afterSend(d -> {
                d.notifyDebuggerResumed(new DebuggerResumed());
                d.notifyDebuggerPaused(DebugeeMethodFactory.createDebuggerPaused(
                        interpreter.getScriptId(), interpreter.getSourceUrl().toString(),
                        interpreter.getFunctionName(), interpreter.getLineNumber()));
            });
            return result;
        }
        else {
            Result result = new Result(id);
            result.afterSend(d -> {
                d.notifyCloseSession("Interpreted session closed");
            });
            return result;
        }
    }

    @Override
    public Result stepInto(int id) {
        interpreter.interpret();
        return new Result(id);
    }

    @Override
    public Result stepOut(int id) {
        interpreter.interpret();
        return new Result(id);
    }

}
