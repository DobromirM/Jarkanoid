package shapes;

import arkanoid.Coordinate;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Triangle extends Shape
{
    private Coordinate pointA;
    private Coordinate pointB;
    private Coordinate pointC;
    
    public Triangle(Coordinate pointA, Coordinate pointB, Coordinate pointC)
    {
        setPointA(pointA);
        setPointB(pointB);
        setPointC(pointC);
        
        Coordinate center = calculateCenter();
        setPosX(center.getX());
        setPosY(center.getY());
    }
    
    public Triangle(Coordinate pointA, Coordinate pointB, Coordinate pointC, Double rotation)
    {
        setPointA(pointA);
        setPointB(pointB);
        setPointC(pointC);
        setRotation(rotation);
        
        Coordinate center = calculateCenter();
        setPosX(center.getX());
        setPosY(center.getY());
    }
    
    /**
     * Draw the triangle on the screen
     *
     * @param g - Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affineTransform = g2d.getTransform();
        
        g.setColor(color);
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(pointA.getX(), pointA.getY());
        triangle.lineTo(pointB.getX(), pointB.getY());
        triangle.lineTo(pointC.getX(), pointC.getY());
        g2d.rotate(Math.toRadians(rotation), getPosX(), getPosY());
        g2d.draw(triangle);
        g2d.fill(triangle);
        
        g2d.setTransform(affineTransform);
    }
    
    /**
     * Calculate the coordinates of the center of the triangle
     *
     * @return - Coordinates of the center
     */
    private Coordinate calculateCenter()
    {
        double centerX = (pointA.getX() + pointB.getX() + pointC.getX()) / 3;
        double centerY = (pointA.getY() + pointB.getY() + pointC.getY()) / 3;
        return new Coordinate(centerX, centerY);
    }
    //------------------------- ACCESSORS-------------------------
    
    public Coordinate getPointA()
    {
        return pointA;
    }
    
    public void setPointA(Coordinate pointA)
    {
        this.pointA = pointA;
    }
    
    public Coordinate getPointB()
    {
        return pointB;
    }
    
    public void setPointB(Coordinate pointB)
    {
        this.pointB = pointB;
    }
    
    public Coordinate getPointC()
    {
        return pointC;
    }
    
    public void setPointC(Coordinate pointC)
    {
        this.pointC = pointC;
    }
}
