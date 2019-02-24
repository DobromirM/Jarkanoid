package arkanoid;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class LifeTracker
{
    private Boolean isDead = Boolean.FALSE;
    //Total number of lives
    private Integer lives = 3;
    
    /**
     * Removes a life from the total number
     */
    public void removeLife()
    {
        lives--;
    }
    
    //------------------------- ACCESSORS-------------------------
    public Integer getLives()
    {
        return lives;
    }
    
    public Boolean getDead()
    {
        return isDead;
    }
    
    public void setDead(Boolean dead)
    {
        isDead = dead;
    }
}
