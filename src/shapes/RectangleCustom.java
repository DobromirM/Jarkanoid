package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class RectangleCustom extends Shape
{
    private Integer width;
    private Integer height;
    
    public RectangleCustom(Integer width, Integer height)
    {
        this.width = width;
        this.height = height;
    }
    
    public RectangleCustom(Integer width, Integer height, Double posX, Double posY)
    {
        this.width = width;
        this.height = height;
        setPosX(posX);
        setPosY(posY);
    }
    
    public RectangleCustom(Integer width, Integer height, Double posX, Double posY, Double rotation)
    {
        this.width = width;
        this.height = height;
        setPosX(posX);
        setPosY(posY);
        setRotation(rotation);
    }
    
    /**
     * Draw the rectangle on the screen
     *
     * @param g - Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = g2d.getTransform();
        
        g.setColor(color);
        Rectangle.Double rect = new Rectangle.Double(getPosX(), getPosY(), getWidth(), getHeight());
        g2d.rotate(Math.toRadians(rotation), posX, posY);
        g2d.draw(rect);
        g2d.fill(rect);
        
        g2d.setTransform(affineTransform);
    }
    
    /**
     * Calculate the Y coordinate of the top side
     *
     * @return - Y coordinate of top side
     */
    public Double getTop()
    {
        return posY;
    }
    
    /**
     * Calculate the Y coordinate of the bottom side
     *
     * @return - Y coordinate of bottom side
     */
    public Double getBottom()
    {
        return posY + height;
    }
    
    /**
     * Calculate the X coordinate of the left side
     *
     * @return - X coordinate of the left side
     */
    public Double getLeft()
    {
        return posX;
    }
    
    /**
     * Calculate the X coordinate of the right side
     *
     * @return - X coordinate of the right side
     */
    public Double getRight()
    {
        return posX + width;
    }
    
    //------------------------- ACCESSORS-------------------------
    public Integer getWidth()
    {
        return width;
    }
    
    public Integer getHeight()
    {
        return height;
    }
}
