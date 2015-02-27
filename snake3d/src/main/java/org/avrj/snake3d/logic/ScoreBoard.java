package org.avrj.snake3d.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import org.avrj.snake3d.fileutils.ScoreBoardFileReader;
import org.avrj.snake3d.fileutils.ScoreBoardFileUtils;
import org.avrj.snake3d.fileutils.ScoreBoardFileWriter;
import org.avrj.snake3d.helpers.ScoreBoardItem;

/**
 * A scoreboard that keeps track of points earned
 *
 * @author araiha
 */
public class ScoreBoard {

    private final String storedScoresDirectoryPath = "savedScores/";
    private final String storedScoresFilePath = "scores.txt";
    private final File file, dir;

    private int score = 0;

    public ScoreBoard() {
        dir = new File(storedScoresDirectoryPath);
        file = new File(storedScoresDirectoryPath + storedScoresFilePath);
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
     * Sets the score to zero
     */
    public void clearScore() {
        score = 0;
    }

    /**
     * A method to return the saved scores from a file
     *
     * @return List of saved scores
     */
    public ArrayList<ScoreBoardItem> getSavedScores() {
        if (!ScoreBoardFileUtils.createDirIfNotExists(dir)) {
            return new ArrayList<>();
        }

        if (!ScoreBoardFileUtils.createFileIfNotExists(file)) {
            return new ArrayList<>();
        }

        ArrayList<ScoreBoardItem> savedScores = new ArrayList<>();

        ScoreBoardFileReader fileReader = new ScoreBoardFileReader(storedScoresDirectoryPath + storedScoresFilePath);

        for (String row : fileReader.getLines(10)) {
            String[] splittedRow = row.split("\\|");

            long rowTimestamp = Long.parseLong(splittedRow[0]);
            Integer rowScore = Integer.parseInt(splittedRow[1]);

            savedScores.add(new ScoreBoardItem(rowTimestamp, rowScore));
        }

        Collections.sort(savedScores);

        return savedScores;
    }

    /**
     * Saves the current score to a file if the score is bigger or as big as the
     * lowest score in the file
     *
     * @return true if score is saved
     */
    public boolean saveScore() {
        ArrayList<ScoreBoardItem> savedScores = getSavedScores();

        int lowestScore = 0;

        if (!savedScores.isEmpty()) {
            savedScores.get(savedScores.size() - 1).getScore();
        }

        if (score >= lowestScore) {
            if (savedScores.size() == 10) {
                savedScores.remove(savedScores.size() - 1);
            }

            long currentTimestamp = System.currentTimeMillis() / 1000L;

            savedScores.add(new ScoreBoardItem(currentTimestamp, score));
        } else {
            return false;
        }

        Collections.sort(savedScores);

        if (!ScoreBoardFileUtils.createDirIfNotExists(dir)) {
            return false;
        }

        if (!ScoreBoardFileUtils.createFileIfNotExists(file)) {
            return false;
        }

        if (!ScoreBoardFileUtils.clearFile(file)) {
            return false;
        }

        ScoreBoardFileWriter fileWriter = new ScoreBoardFileWriter(file);

        for (ScoreBoardItem scoreBoardItem : savedScores) {
            fileWriter.writeLine(scoreBoardItem.getTimestamp() + "|" + scoreBoardItem.getScore());
        }

        fileWriter.close();

        return true;
    }

    /**
     * Formats Unix timestamp to readable date
     *
     * @param unixTimestamp Timestamp in Unix format
     * @return Formatted date
     */
    public String formatTimestamp(Long unixTimestamp) {
        Date date = new Date(unixTimestamp * 1000L);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM. HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));

        return sdf.format(date);
    }
}
