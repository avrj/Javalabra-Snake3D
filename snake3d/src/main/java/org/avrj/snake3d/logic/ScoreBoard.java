package org.avrj.snake3d.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * A scoreboard that keeps track of points earned
 *
 * @author araiha
 */
public class ScoreBoard {

    private final String storedScoresDirectoryPath = "savedScores/";
    private final String storedScoresFilePath = "scores.txt";

    private int score = 0;

    public ScoreBoard() {

    }

    public int getScore() {
        return score;
    }

    /**
     * Increase the score by one points
     */
    public void increaseScore() {
        score++;
    }

    /**
     * A method to return the saved scores from a file
     *
     * @return List of saved scores
     */
    public Map<String, Integer> getSavedScores() {
        Map<String, Integer> savedScores = new HashMap<>();

        File file = new File(storedScoresDirectoryPath + storedScoresFilePath);

        if (file.isFile()) {
            BufferedReader in = null;

            try {
                in = new BufferedReader(new FileReader(file));

                String row;

                while ((row = in.readLine()) != null) {
                    if (savedScores.size() == 10) {
                        break;
                    }

                    if (!row.contains("|")) {
                        continue;
                    }

                    String[] splitted_row = row.split("\\|");

                    long rowTimestamp = Long.parseLong(splitted_row[0]);
                    Integer rowScore = Integer.parseInt(splitted_row[1]);

                    Date date = new Date(rowTimestamp * 1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM. HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));
                    String formattedDate = sdf.format(date);

                    savedScores.put(formattedDate, rowScore);
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {

                    }
                }
            }
        }
        return savedScores;
    }

    /**
     * Saves the current score to a file
     *
     * @return true if score is saved
     */
    public boolean saveScore() {
        File dir = new File(storedScoresDirectoryPath);
        File file = new File(storedScoresDirectoryPath + storedScoresFilePath);

        if (dir.exists()) {
            if (!dir.isDirectory()) {
                return false;
            }
        } else {
            dir.mkdir();
        }

        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

        long currentTimestamp = System.currentTimeMillis() / 1000L;

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            printWriter.println(currentTimestamp + "|" + score);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
