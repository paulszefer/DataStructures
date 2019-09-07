package io.github.paulszefer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final String DEFAULT_PATH = "";

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public Log() {
        // try {
        //     file = new File(DEFAULT_PATH);
        //     fileWriter = new FileWriter(file);
        //     bufferedWriter = new BufferedWriter(fileWriter);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    public void close() {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String message) {
        // try {
        //     bufferedWriter.write(message);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        System.out.print(message);
    }

    public void writeLine(String message) {
        // try {
            // bufferedWriter.write(message);
            // bufferedWriter.write(LINE_SEPARATOR);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        System.out.println(message);
    }

    public void error(Exception e) {
        e.printStackTrace();
    }
}