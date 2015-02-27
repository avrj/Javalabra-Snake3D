/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author avrj
 */
public class ScoreBoardFileReader {

    private BufferedReader bufferedReader;

    public ScoreBoardFileReader(String fileName) {
        bufferedReader = null;
        File file = new File(fileName);
        System.out.println(file.getAbsoluteFile());
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.out.println("Error opening file: " + fileName);
        }
    }

    public ArrayList<String> getLines(int maxLines) {
        ArrayList<String> lines = new ArrayList<>();

        String row;

        try {
            while ((row = bufferedReader.readLine()) != null) {
                if (lines.size() == maxLines) {
                    break;
                }

                if (!row.contains("|")) {
                    continue;
                }

                lines.add(row);
            }
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }

        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Error closing file");
        }

        return lines;
    }

}
