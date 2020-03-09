/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugee.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco.lanari
 */
public class MockInterpreter {
    
    private static final Logger LOGGER = Logger.getLogger(MockInterpreter.class.getName());
    
    private final URL sourceUrl;
    private final String scriptId;
    private final String source;
    private final Collection<String> statements = new ArrayList<>();
    private final Iterator<String> it;
    private int lineNumber = -1;
    private String currentStatement;

    public MockInterpreter(String scriptId) throws IOException{
        this.scriptId = scriptId;
        this.sourceUrl = getClass().getResource("/mock/" + scriptId);
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                (sourceUrl.openStream())))) {
            while((line = in.readLine()) != null) {
               statements.add(line);
                sb.append(line).append("\n");
            }
        }
        it = statements.iterator();
        source = sb.toString();
        currentStatement = it.next();
        lineNumber++;
    }

    
    public String getSource() {
        return source;
    }
    
    public URL getSourceUrl() {
        return sourceUrl;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getCurrentStatement() {
        return currentStatement;
    }
    
    public String getFunctionName() {
        return "main";
    }
    
    public String getScriptId() {
        return scriptId;
    }
    
    /**
     * Interpreta la riga di codice corrente
     * @return false Se non c'è più niente da interpretare
     */
    public boolean interpret() {
        LOGGER.log(Level.INFO, "Interpreting: {0}", currentStatement);
        if (it.hasNext()) {
            currentStatement = it.next();
            lineNumber++;
            return true;
        }
        else {
            return false;
        }
        
    }
    
    
    
}
