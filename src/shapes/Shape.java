package shapes;

import java.awt.*;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public abstract class Shape
{
    Double posX = 0.0;
    Double posY = 0.0;
    Double rotation = 0.0;
    Color color = Color.WHITE;
    
    /**
     * Abstract function that draws the shape on the screen
     *
     * @param g - Graphics object
     */
    public abstract void draw(Graphics g);
    
    //------------------------- ACCESSORS-------------------------
    public Double getPosX()
    {
        return posX;
    }
    
    public void setPosX(Double posX)
    {
        this.posX = posX;
    }
    
    public Double getPosY()
    {
        return posY;
    }
    
    public void setPosY(Double posY)
    {
        this.posY = posY;
    }
    
    public Double getRotation()
    {
        return rotation;
    }
    
    public void setRotation(Double rotation)
    {
        this.rotation = rotation;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
}
