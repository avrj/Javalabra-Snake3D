/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d.logic;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author araiha
 */
public class ScoreBoardTest {

    private final ScoreBoard scoreBoard;
    private final File scoresFilePath, scoresDirectoryPath;

    public ScoreBoardTest() {
        scoreBoard = new ScoreBoard();

        scoresFilePath = new File("savedScores/scores.txt");
        scoresDirectoryPath = new File("savedScores");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        if(scoresFilePath.exists())
            scoresFilePath.delete();
        
        if(scoresDirectoryPath.exists())
            scoresDirectoryPath.delete();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void scoreIsZeroAtStart() {
        assertEquals(scoreBoard.getScore(), 0);
    }

    @Test
    public void scoreIsIncreasing() {
        scoreBoard.increaseScore();

        assertEquals(scoreBoard.getScore(), 1);
    }

    @Test
    public void scoreIsSavedIfDirectoryExists() {
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        scoresDirectoryPath.mkdir();

        assertTrue(scoreBoard.saveScore());

        scoresFilePath.delete();
        scoresDirectoryPath.delete();
    }

    @Test
    public void scoreIsSavedIfDirectoryNotExists() {
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.saveScore());
    }

    @Test
    public void scoreIsSavedIfFileExists() {
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        scoresDirectoryPath.mkdir();

        try {
            scoresFilePath.createNewFile();
        } catch (IOException e) {

        }

        assertTrue(scoreBoard.saveScore());

        scoresFilePath.delete();
    }

    @Test
    public void scoreIsSavedIfFileNotExists() {
        scoresDirectoryPath.mkdir();

        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.saveScore());
    }

    @Test
    public void getSavedScoresIfFileNotExists() {
        scoresDirectoryPath.mkdir();

        scoresFilePath.delete();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.getSavedScores().isEmpty());
    }

    @Test
    public void getSavedScoresIfFileExists() {
        scoresDirectoryPath.mkdir();

        try {
            scoresFilePath.createNewFile();
        } catch (IOException e) {

        }

        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.getSavedScores().isEmpty());
    }
    @Test
    public void savedScoresAreReturned() {
        scoresFilePath.delete();

        try {
            scoresFilePath.createNewFile();
        } catch (IOException e) {

        }

        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.saveScore();

        assertEquals(1, scoreBoard.getSavedScores().size());

        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        scoreBoard.saveScore();
        
        assertEquals(2, scoreBoard.getSavedScores().size());

    }

    @Test
    public void unixTimestampIsFormattedCorrectly() {
        assertEquals(scoreBoard.formatTimestamp(1424425550L), "20.02. 11:45:50");
    }
}
