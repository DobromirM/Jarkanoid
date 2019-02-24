package arkanoid;

import java.time.LocalDateTime;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Score
{
    private final String name;
    private final Integer score;
    private final LocalDateTime time;
    
    public Score(String name, Integer score, LocalDateTime time)
    {
        this.name = name;
        this.score = score;
        this.time = time;
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public String getName()
    {
        return name;
    }
    
    public Integer getScore()
    {
        return score;
    }
    
    public LocalDateTime getTime()
    {
        return time;
    }
    
    @Override
    public String toString()
    {
        return name + ", " + score + " points, " + time.getDayOfMonth() + "/" + time.getMonthValue() + " " + time.getHour() + ":" + time.getMinute();
    }
}
