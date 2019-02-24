package arkanoid;

import shapes.*;
import shapes.RectangleCustom;

import java.util.List;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class View extends JComponent
{
    //Game state flags
    private Boolean paused = Boolean.TRUE;
    private Boolean gameOver = Boolean.FALSE;
    
    //Game properties
    private Color[] colors = {black, green, blue, red, yellow, magenta, pink, cyan};
    private Integer width = 20;
    private Integer height = 20;
    private Integer size = 30;
    private int[][] grid = new int[width][height];
    
    //Main elements
    private Paddle paddle;
    private LifeTracker lifeTracker;
    private Ball ball;
    private List<RectangleCustom> obstacles;
    
    View(Paddle paddle, Ball ball, List<RectangleCustom> obstacles, LifeTracker lifeTracker)
    {
        this.paddle = paddle;
        this.ball = ball;
        this.obstacles = obstacles;
        this.lifeTracker = lifeTracker;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        //Draw empty grid
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                g.setColor(colors[grid[i][j]]);
                g.fillRect(i * size, j * size, size, size);
            }
        }
        
        //If you need to test the shape drawing
        //testDraw(g);
        
        //Draw main elements
        for (RectangleCustom o : obstacles)
        {
            o.draw(g);
        }
        
        paddle.draw(g);
        ball.draw(g);
        
        //Display UI
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + lifeTracker.getLives(), 550, 580);
        g.drawString("Points: " + ball.getPoints(), 20, 580);
        
        if (paused)
        {
            g.drawString("Press Space or click anywhere to start!", 200, 300);
        }
        
        if (gameOver)
        {
            g.drawString("Your score is: " + ball.getPoints(), 250, 300);
            g.drawString("Press R to restart", 250, 320);
        }
    }
    
    /**
     * Get the size of the component
     *
     * @return - Dimension
     */
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width * size, height * size);
    }
    
    /**
     * Update the game every frame
     */
    public void update()
    {
        paddle.update();
        ball.update();
    }
    
    /**
     * This function is for testing purposes only if you need to quickly check that all shapes are displayed correctly
     *
     * @param g - Graphics object
     */
    private void testDraw(Graphics g)
    {
        Square square = new Square(60, 200.0, 200.0, 0.0);
        Circle circle = new Circle(50.0, 100.0, 100.0, 45.0);
        Pie pie = new Pie(50, 90, 90, 300.0, 300.0);
        RectangleCustom rectangleCustom = new RectangleCustom(30, 100, 350.0, 350.0, 45.0);
        Triangle triangle = new Triangle(new Coordinate(400.0, 500.0), new Coordinate(550.0, 550.0), new Coordinate(350.0, 350.0), 45.0);
        
        square.draw(g);
        circle.draw(g);
        pie.draw(g);
        rectangleCustom.draw(g);
        triangle.draw(g);
    }
    
    //------------------------- ACCESSORS-------------------------
    public Boolean getPaused()
    {
        return paused;
    }
    
    public void setPaused(Boolean paused)
    {
        this.paused = paused;
    }
    
    public Boolean getGameOver()
    {
        return gameOver;
    }
    
    public void setGameOver(Boolean gameOver)
    {
        this.gameOver = gameOver;
    }
}
