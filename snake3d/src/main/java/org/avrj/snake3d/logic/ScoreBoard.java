package org.avrj.snake3d.logic;

/**
 * A scoreboard that keeps track of points earned
 * 
 * @author araiha
 */
public class ScoreBoard {
    private int score = 0;
    
    public ScoreBoard() {
        
    }
    
    public int getScore() {
        return score;
    }
    
    /**
     *  Increase the score by one points
     */
    public void increaseScore() {
        score++;
    }
}
