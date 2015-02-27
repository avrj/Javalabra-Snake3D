/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d.fileutils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author avrj
 */
public class ScoreBoardFileWriter {

    private PrintWriter printWriter;

    public ScoreBoardFileWriter(File file) {
        try {
            printWriter = new PrintWriter(new FileOutputStream(file, true));
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void writeLine(String text) {
        printWriter.println(text);
    }

    public void close() {
        printWriter.close();
    }
}
