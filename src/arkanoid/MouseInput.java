package arkanoid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class MouseInput implements MouseListener
{
    //Track if the round has started
    private Boolean start = Boolean.FALSE;
    
    /**
     * Do action on mouse click
     *
     * @param e - Mose event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        start = Boolean.TRUE;
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
    
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Boolean getStart()
    {
        return start;
    }
    
    public void setStart(Boolean start)
    {
        this.start = start;
    }
}
