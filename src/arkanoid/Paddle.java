package arkanoid;

import shapes.RectangleCustom;

import java.awt.*;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Paddle extends RectangleCustom
{
    private KeyboardInput keyboardInput;
    
    Paddle(KeyboardInput keyboardInput)
    {
        super(50, 10, 275.0, 550.0);
        super.setColor(Color.PINK);
        this.keyboardInput = keyboardInput;
    }
    
    /**
     * Update the paddle according to the input
     */
    public void update()
    {
        if (keyboardInput.getMoveLeft() == Boolean.TRUE)
        {
            setPosX(getPosX() - 10);
        }
        
        if (keyboardInput.getMoveRight() == Boolean.TRUE)
        {
            setPosX(getPosX() + 10);
        }
        
        setBound();
    }
    
    /**
     * Reset the paddle to its original state
     */
    public void reset()
    {
        setPosX(275.0);
        setPosY(550.0);
    }
    
    /**
     * Set bounds for the paddle so it can't leave the screen
     */
    private void setBound()
    {
        if (getPosX() < 0)
        {
            setPosX(0.0);
        }
        
        if (getPosX() + getWidth() > 600)
        {
            setPosX(600.0 - getWidth());
        }
    }
}
