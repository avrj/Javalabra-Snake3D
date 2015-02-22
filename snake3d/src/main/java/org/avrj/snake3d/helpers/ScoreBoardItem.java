/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d.helpers;

/**
 *
 * @author avrj
 */
public class ScoreBoardItem {
    private Long unixTimestamp;
    private Integer score;
    
    public ScoreBoardItem(Long unixTimestamp, Integer score) {
        this.unixTimestamp = unixTimestamp;
        this.score = score;
    }
    
    public Long getTimestamp() {
        return unixTimestamp;
    }
    
    public int getScore() {
        return score;
    }
}
