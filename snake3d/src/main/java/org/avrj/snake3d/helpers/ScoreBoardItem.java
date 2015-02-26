package org.avrj.snake3d.helpers;

/**
 * A helper class for reading and saving scores
 *
 * @author avrj
 */
public class ScoreBoardItem implements Comparable<ScoreBoardItem> {

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

    @Override
    public int compareTo(ScoreBoardItem scoreBoardItem) {
        if (scoreBoardItem.getScore() - this.getScore() == 0) {
            return (int) (scoreBoardItem.getTimestamp() - this.getTimestamp());
        } else {
            return scoreBoardItem.getScore() - this.getScore();
        }
    }
}
