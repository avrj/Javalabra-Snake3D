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
import java.util.Date;
import java.util.TimeZone;
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
        BufferedReader bufferedReader = createReader(storedScoresDirectoryPath + storedScoresFilePath);

        if (bufferedReader == null) {
            return new ArrayList<>();
        }

        return createScoresList(bufferedReader);
    }

    private BufferedReader createReader(String fileName) {
        try {
            return new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            return null;
        }
    }

    private ArrayList<ScoreBoardItem> createScoresList(BufferedReader br) {
        ArrayList<ScoreBoardItem> savedScores = new ArrayList<>();

        try {
            String row;

            try {
                while ((row = br.readLine()) != null) {
                    if (savedScores.size() == 10) {
                        break;
                    }

                    if (!row.contains("|")) {
                        continue;
                    }

                    String[] splitted_row = row.split("\\|");

                    long rowTimestamp = Long.parseLong(splitted_row[0]);
                    Integer rowScore = Integer.parseInt(splitted_row[1]);

                    savedScores.add(new ScoreBoardItem(rowTimestamp, rowScore));
                }
            } catch (IOException ex) {

            }
        } finally {
            try {
                br.close();
            } catch (IOException ex) {

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
        ArrayList<ScoreBoardItem> savedScores = getSavedScores();

        if (savedScores.size() == 10) {
            savedScores.remove(savedScores.size() - 1);
        }

        long currentTimestamp = System.currentTimeMillis() / 1000L;

        savedScores.add(0, new ScoreBoardItem(currentTimestamp, score));

        if (!createDirIfNotExists(dir)) {
            return false;
        }

        if (!createFileIfNotExists(file)) {
            return false;
        }

        if (!clearFile(file)) {
            return false;
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            for (ScoreBoardItem scoreBoardItem : savedScores) {
                printWriter.println(scoreBoardItem.getTimestamp() + "|" + scoreBoardItem.getScore());
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private boolean clearFile(File fileImport) {
        FileInputStream fileStream = null;

        try (PrintWriter writer = new PrintWriter(fileImport)) {
            writer.print("");
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    private boolean createDirIfNotExists(File dir) {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                return false;
            }
        } else {
            dir.mkdir();
        }

        return true;
    }

    private boolean createFileIfNotExists(File file) {
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

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
