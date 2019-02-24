package shapes;

import arkanoid.Coordinate;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Circle extends Shape
{
    private Double radius;
    private Coordinate center;
    
    public Circle(Double radius)
    {
        setRadius(radius);
    }
    
    public Circle(Double radius, Double posX, Double posY)
    {
        setRadius(radius);
        setPosX(posX);
        setPosY(posY);
    }
    
    public Circle(Double radius, Double posX, Double posY, Double rotation)
    {
        setRadius(radius);
        setPosX(posX);
        setPosY(posY);
        setRotation(rotation);
    }
    
    /**
     * Draw the circle on the screen
     *
     * @param g - Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = g2d.getTransform();
        
        g.setColor(color);
        Ellipse2D.Double circle = new Ellipse2D.Double(getPosX(), getPosY(), getRadius(), getRadius());
        this.center = new Coordinate(circle.getCenterX(), circle.getCenterY());
        
        g2d.rotate(Math.toRadians(rotation), posX, posY);
        g2d.draw(circle);
        g2d.fill(circle);
        
        g2d.setTransform(affineTransform);
    }
    
    /**
     * Calculate the Y coordinate of the top side
     *
     * @return - Y coordinate of top side
     */
    public Double getTop()
    {
        return posY - radius / 2;
    }
    
    /**
     * Calculate the Y coordinate of the bottom side
     *
     * @return - Y coordinate of bottom side
     */
    public Double getBottom()
    {
        return posY + radius / 2;
    }
    
    /**
     * Calculate the X coordinate of the left side
     *
     * @return - X coordinate of the left side
     */
    public Double getLeft()
    {
        return posX - radius / 2;
    }
    
    /**
     * Calculate the X coordinate of the right side
     *
     * @return - X coordinate of the right side
     */
    public Double getRight()
    {
        return posX + radius / 2;
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Double getRadius()
    {
        return radius;
    }
    
    public void setRadius(Double radius)
    {
        this.radius = radius;
    }
    
    public Coordinate getCenter()
    {
        return center;
    }
}
