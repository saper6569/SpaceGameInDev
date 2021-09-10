package com.playper3.space.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DebugLogger {
    public void log(String logInfo) {
        File logFile = new File("debugLog.txt");
        try {
            FileWriter logWriter = new FileWriter("debugLog.txt");
            logWriter.write(logInfo);
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

