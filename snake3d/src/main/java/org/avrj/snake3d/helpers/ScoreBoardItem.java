package org.avrj.snake3d.helpers;

/**
 * A helper class for reading and saving scores
 * 
 * @author avrj
 */
public class ScoreBoardItem {

    private final Long unixTimestamp;
    private final Integer score;

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
