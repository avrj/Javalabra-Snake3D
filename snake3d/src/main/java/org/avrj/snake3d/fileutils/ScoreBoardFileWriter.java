package org.avrj.snake3d.fileutils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File writing class for scoreboard
 *
 * @author avrj
 */
public class ScoreBoardFileWriter {

    private PrintWriter printWriter = null;

    public ScoreBoardFileWriter(File file) {
        try {
            printWriter = new PrintWriter(new FileOutputStream(file, true));
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public boolean writeLine(String text) {
        if (printWriter == null) {
            return false;
        }

        try {
            printWriter.println(text);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean close() {
        try {
            printWriter.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
