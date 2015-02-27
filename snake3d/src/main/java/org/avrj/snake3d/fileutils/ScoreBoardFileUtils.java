package org.avrj.snake3d.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Provides tools for file operations in scoreboard
 *
 * @author avrj
 */
public class ScoreBoardFileUtils {

    public static boolean clearFile(File fileImport) {
        FileInputStream fileStream = null;

        try (PrintWriter writer = new PrintWriter(fileImport)) {
            writer.print("");
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public static boolean createDirIfNotExists(File dir) {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                return false;
            }
        } else {
            dir.mkdir();
        }

        return true;
    }

    public static boolean createFileIfNotExists(File file) {
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

        return true;
    }
}
