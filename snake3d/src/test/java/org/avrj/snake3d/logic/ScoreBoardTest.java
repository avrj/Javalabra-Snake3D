/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d.logic;

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
}
