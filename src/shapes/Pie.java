package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Pie extends Shape
{
    private Integer radius;
    private Integer extent;
    private Integer start;
    
    public Pie(Integer radius, Integer start, Integer extent)
    {
        setRadius(radius);
        setStart(start);
        setExtent(extent);
    }
    
    public Pie(Integer radius, Integer start, Integer extent, Double posX, Double posY)
    {
        setRadius(radius);
        setStart(start);
        setExtent(extent);
        setPosX(posX);
        setPosY(posY);
    }
    
    public Pie(Integer radius, Integer start, Integer extent, Double posX, Double posY, Double rotation)
    {
        setRadius(radius);
        setStart(start);
        setExtent(extent);
        setPosX(posX);
        setPosY(posY);
        setRotation(rotation);
    }
    
    /**
     * Draw the pie on the screen
     *
     * @param g - Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = g2d.getTransform();
        
        g.setColor(color);
        Arc2D.Double pie = new Arc2D.Double(getPosX(), getPosY(), getRadius(), getRadius(), getStart(), getExtent(), Arc2D.PIE);
        g2d.rotate(Math.toRadians(rotation), posX, posY);
        g2d.draw(pie);
        g2d.fill(pie);
        
        g2d.setTransform(affineTransform);
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Integer getRadius()
    {
        return radius;
    }
    
    public void setRadius(Integer radius)
    {
        this.radius = radius;
    }
    
    public Integer getStart()
    {
        return start;
    }
    
    public void setStart(Integer start)
    {
        this.start = start;
    }
    
    public Integer getExtent()
    {
        return extent;
    }
    
    public void setExtent(Integer extent)
    {
        this.extent = extent;
    }
}
