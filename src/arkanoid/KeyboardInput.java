package arkanoid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class KeyboardInput implements KeyListener
{
    private Boolean moveLeft = Boolean.FALSE;
    private Boolean moveRight = Boolean.FALSE;
    private Boolean start = Boolean.FALSE;
    private Boolean restart = Boolean.FALSE;
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    
    }
    
    /**
     * Do action on key press
     *
     * @param e - Key Event
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        //Paddle Movement
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moveRight = Boolean.FALSE;
            moveLeft = Boolean.TRUE;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moveLeft = Boolean.FALSE;
            moveRight = Boolean.TRUE;
        }
        
        //Start
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            start = Boolean.TRUE;
        }
        
        //Restart
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            restart = Boolean.TRUE;
        }
    }
    
    /**
     * Do action on the release of a key
     *
     * @param e - Key Event
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        //Key Resets
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moveLeft = Boolean.FALSE;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moveRight = Boolean.FALSE;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            restart = Boolean.FALSE;
        }
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Boolean getMoveLeft()
    {
        return moveLeft;
    }
    
    public Boolean getMoveRight()
    {
        return moveRight;
    }
    
    public Boolean getStart()
    {
        return start;
    }
    
    public void setStart(Boolean start)
    {
        this.start = start;
    }
    
    public Boolean getRestart()
    {
        return restart;
    }
    
    public void setRestart(Boolean restart)
    {
        this.restart = restart;
    }
}
