package arkanoid;

import shapes.Circle;
import shapes.RectangleCustom;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Ball extends Circle
{
    private Double speed;
    private Double angle = 25.0;
    private Coordinate velocity;
    private Integer points = 0;
    
    private List<RectangleCustom> obstacles;
    private Paddle paddle;
    private LifeTracker lifeTracker;
    
    //Constants used in the calculations
    private final Double MAX_ANGLE = 60.0;
    private final Double BASE_SPEED = 7.0;
    private final Double BOOST = 5.0;
    
    public Ball(List<RectangleCustom> obstacles, Paddle paddle, LifeTracker lifeTracker)
    {
        super(10.0, 295.0, 540.0);
        this.speed = BASE_SPEED + BOOST * 0.5;
        this.velocity = calculateVelocity(angle);
        this.obstacles = obstacles;
        this.paddle = paddle;
        this.lifeTracker = lifeTracker;
    }
    
    /**
     * Update the ball for every change
     */
    public void update()
    {
        setPosX(getPosX() + velocity.getX());
        setPosY(getPosY() + velocity.getY());
        
        wallBounce();
        checkCollision(obstacles);
        paddleCollision(paddle);
    }
    
    /**
     * Reset the ball to its initial state
     */
    public void reset()
    {
        this.setPosX(290.0);
        this.setPosY(550.0);
        this.angle = 25.0;
        this.speed = BASE_SPEED + BOOST * 0.5;
        this.velocity = calculateVelocity(angle);
    }
    
    /**
     * Calculate the velocity of the ball for a given angle
     *
     * @param angle - Angle in degrees
     *
     * @return - The new velocity
     */
    private Coordinate calculateVelocity(Double angle)
    {
        Double velocityX = speed * (Math.sin(Math.toRadians(angle)));
        Double velocityY = speed * (-Math.cos(Math.toRadians(angle)));
        
        return new Coordinate(velocityX, velocityY);
    }
    
    /**
     * Handle the collision of the ball with the walls
     */
    private void wallBounce()
    {
        //If the ball collides with the right wall
        if (getRight() > 600)
        {
            this.setPosX(600.0 - 5);
            this.angle = -angle;
            this.velocity = new Coordinate(calculateVelocity(angle).getX(), this.velocity.getY());
        }
        
        //If the ball collides with the left wall
        if (getLeft() < 0)
        {
            this.setPosX(0.0 + 5);
            this.angle = -angle;
            this.velocity = new Coordinate(calculateVelocity(angle).getX(), this.velocity.getY());
        }
        
        //If the ball collides with the top wall
        if (getTop() < 0)
        {
            this.setPosY(0.0);
            this.velocity = new Coordinate(this.velocity.getX(), -this.velocity.getY());
        }
        
        //If the ball collides with the bottom wall
        if (getBottom() > 600)
        {
            lifeTracker.removeLife();
            lifeTracker.setDead(Boolean.TRUE);
        }
    }
    
    /**
     * Handle the collision of the ball with any of the obstacles
     *
     * @param rectangles - List of all the obstacles
     */
    private void checkCollision(List<RectangleCustom> rectangles)
    {
        Iterator<RectangleCustom> iterator = rectangles.iterator();
        
        while (iterator.hasNext())
        {
            RectangleCustom rect = iterator.next();
            
            //If the ball collides with a rectangle from the bottom
            if (bottomCollision(rect))
            {
                this.setPosY(rect.getPosY() + rect.getHeight());
                this.velocity = new Coordinate(this.velocity.getX(), -this.velocity.getY());
                removeRectangle(iterator, rect);
                points += 10;
                continue;
            }
            
            //If the ball collides with a rectangle from the top
            if (topCollision(rect))
            {
                this.setPosY(rect.getPosY() - this.getRadius());
                this.velocity = new Coordinate(this.velocity.getX(), -this.velocity.getY());
                removeRectangle(iterator, rect);
                points += 10;
                continue;
            }
            
            //If the ball collides with a rectangle from the left
            if (leftCollision(rect) && this.getCenter().getX() < rect.getLeft())
            {
                this.angle = -angle;
                this.velocity = new Coordinate(calculateVelocity(angle).getX(), this.velocity.getY());
                removeRectangle(iterator, rect);
                points += 10;
                continue;
            }
            
            //If the ball collides with a rectangle from the right
            if (rightCollision(rect) && this.getCenter().getX() > rect.getRight())
            {
                this.setPosX(rect.getPosX() + rect.getWidth());
                this.angle = -angle;
                this.velocity = new Coordinate(calculateVelocity(angle).getX(), this.velocity.getY());
                removeRectangle(iterator, rect);
                points += 10;
            }
        }
    }
    
    /**
     * Remove a rectangle from the field
     *
     * @param iterator - Iterator for the list of obstacles
     * @param rect     - The rectangle to be removed
     */
    private void removeRectangle(Iterator<RectangleCustom> iterator, RectangleCustom rect)
    {
        rect.setColor(Color.black);
        iterator.remove();
    }
    
    /**
     * Handle the collision of the ball with the paddle
     *
     * @param paddle - Paddle controlled by the player
     */
    private void paddleCollision(Paddle paddle)
    {
        if (topCollision(paddle))
        {
            this.setPosY(paddle.getPosY() - this.getRadius());
            Double coefficient = collisionCoefficient(paddle);
            this.angle = MAX_ANGLE * coefficient;
            this.speed = BASE_SPEED + BOOST * Math.abs(coefficient);
            this.velocity = calculateVelocity(this.angle);
        }
    }
    
    /**
     * Calculate the collision coefficient between the ball and the paddle used for calculating the new angle and velocity
     *
     * @param paddle - Paddle controlled by the player
     *
     * @return - collision coefficient between 0 and 1
     */
    private Double collisionCoefficient(Paddle paddle)
    {
        Double paddleLeftPoint = paddle.getPosX();
        Double paddleRightPoint = paddleLeftPoint + paddle.getWidth();
        Double middlePoint = (paddleRightPoint + paddleLeftPoint) / 2;
        
        Double distanceFromCenter = this.getCenter().getX() - middlePoint;
        if (distanceFromCenter > paddle.getWidth() / 2)
        {
            distanceFromCenter = paddle.getWidth() / 2.0;
        }
        
        return distanceFromCenter / (paddle.getWidth() / 2);
    }
    
    /**
     * Check if the ball has collided with obstacle its top side
     *
     * @param rect - Obstacle
     *
     * @return - Boolean result
     */
    private Boolean topCollision(RectangleCustom rect)
    {
        return this.getBottom() > rect.getTop() && this.getBottom() < rect.getBottom() && this.getRight() > rect.getLeft() && this.getLeft() < rect.getRight();
    }
    
    /**
     * Check if the ball has collided with obstacle its bottom side
     *
     * @param rect - Obstacle
     *
     * @return - Boolean result
     */
    private Boolean bottomCollision(RectangleCustom rect)
    {
        return this.getTop() < rect.getBottom() && this.getTop() > rect.getTop() && this.getRight() > rect.getLeft() && this.getLeft() < rect.getRight();
    }
    
    /**
     * Check if the ball has collided with obstacle its right side
     *
     * @param rect - Obstacle
     *
     * @return - Boolean result
     */
    private Boolean rightCollision(RectangleCustom rect)
    {
        return this.getRight() > rect.getLeft() && this.getRight() < rect.getRight() && this.getBottom() > rect.getTop() && this.getTop() < rect.getBottom();
    }
    
    /**
     * Check if the ball has collided with obstacle its left side
     *
     * @param rect - Obstacle
     *
     * @return - Boolean result
     */
    private Boolean leftCollision(RectangleCustom rect)
    {
        return this.getLeft() < rect.getRight() && this.getLeft() > rect.getLeft() && this.getBottom() > rect.getTop() && this.getTop() < rect.getBottom();
    }
    
    //------------------------- ACCESSORS-------------------------
    public Integer getPoints()
    {
        return points;
    }
}
