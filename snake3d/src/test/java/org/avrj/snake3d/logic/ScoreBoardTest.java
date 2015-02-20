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

    private ScoreBoard scoreBoard;

    public ScoreBoardTest() {
        scoreBoard = new ScoreBoard();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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

        File scoresFilePath = new File("savedScores/scores.txt");
        File scoresDirectoryPath = new File("savedScores");
        scoresDirectoryPath.mkdir();

        assertTrue(scoreBoard.saveScore());

        scoresFilePath.delete();
        scoresDirectoryPath.delete();
    }

    @Test
    public void scoreIsSavedIfDirectoryNotExists() {
        File scoresFilePath = new File("savedScores/scores.txt");
        File scoresDirectoryPath = new File("savedScores");

        scoresFilePath.delete();
        scoresDirectoryPath.delete();

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
        File scoresDirectoryPath = new File("savedScores");
        scoresDirectoryPath.mkdir();
        File scoresFilePath = new File("savedScores/scores.txt");

        try {
            scoresFilePath.createNewFile();
        } catch (IOException e) {

        }

        assertTrue(scoreBoard.saveScore());

        scoresFilePath.delete();
    }

    @Test
    public void scoreIsSavedIfFileNotExists() {
        File scoresDirectoryPath = new File("savedScores");
        scoresDirectoryPath.mkdir();
        File scoresFilePath = new File("savedScores/scores.txt");

        scoresFilePath.delete();

        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.saveScore());
    }

    @Test
    public void getSavedScoresIfFileNotExists() {
        File scoresDirectoryPath = new File("savedScores");
        scoresDirectoryPath.mkdir();
        File scoresFilePath = new File("savedScores/scores.txt");

        scoresFilePath.delete();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();
        scoreBoard.increaseScore();

        assertTrue(scoreBoard.getSavedScores().isEmpty());
    }

    @Test
    public void getSavedScoresIfFileExists() {
        File scoresDirectoryPath = new File("savedScores");
        scoresDirectoryPath.mkdir();
        File scoresFilePath = new File("savedScores/scores.txt");

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
        File scoresFilePath = new File("savedScores/scores.txt");
        
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
        System.out.println(scoreBoard.getSavedScores());
        assertEquals(2, scoreBoard.getSavedScores().size());
        
    }
}
