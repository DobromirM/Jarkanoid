package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Square extends Shape
{
    private Integer length;
    
    public Square(Integer length)
    {
        setLength(length);
    }
    
    public Square(Integer length, Double posX, Double posY)
    {
        setLength(length);
        setPosX(posX);
        setPosY(posY);
    }
    
    public Square(Integer length, Double posX, Double posY, Double rotation)
    {
        setLength(length);
        setPosX(posX);
        setPosY(posY);
        setRotation(rotation);
    }
    
    /**
     * Draw the square on the screen
     *
     * @param g - Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = g2d.getTransform();
        
        g.setColor(color);
        Rectangle.Double rect = new Rectangle.Double(getPosX(), getPosY(), getLength(), getLength());
        g2d.rotate(Math.toRadians(rotation), posX, posY);
        g2d.draw(rect);
        g2d.fill(rect);
        
        g2d.setTransform(affineTransform);
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Integer getLength()
    {
        return length;
    }
    
    public void setLength(Integer length)
    {
        this.length = length;
    }
}
