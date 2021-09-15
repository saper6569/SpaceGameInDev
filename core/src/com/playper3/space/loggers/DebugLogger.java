package com.playper3.space.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DebugLogger {
    public void log(String logInfo, String file) {
        File logFile = new File(file);
        try {
            FileWriter logWriter = new FileWriter(file, true);
            logWriter.write(logInfo);
            logWriter.write(System.getProperty("line.separator"));
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String logInfo, String file, Boolean doAppend) {
        File logFile = new File(file);
        try {
            FileWriter logWriter = new FileWriter(file, true);
            logWriter.write(logInfo);
            if (doAppend) {
                logWriter.write(System.getProperty("line.separator"));
            }
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reader(String file, int line) {
        String lineString = String.format("%03d", line);
        Scanner logReader = new Scanner(file);
        String currentLine = "";
        while (currentLine != lineString) {
            currentLine = logReader.nextLine();
        }
        if (currentLine == lineString) {
            currentLine = logReader.nextLine();
        }
        return currentLine;
    }
}

