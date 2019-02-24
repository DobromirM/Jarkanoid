package arkanoid;

import shapes.RectangleCustom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

class GameFrame extends JFrame
{
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private Paddle paddle;
    private List<RectangleCustom> obstacles;
    private LifeTracker lifeTracker;
    private Ball ball;
    private View view;
    private ScoreBoard scoreBoard;
    
    GameFrame()
    {
        init();
        Timer timer = run();
        timer.start();
    }
    
    /**
     * Initial setup of the frame
     */
    private void init()
    {
        keyboardInput = new KeyboardInput();
        mouseInput = new MouseInput();
        paddle = new Paddle(keyboardInput);
        obstacles = createLevel();
        lifeTracker = new LifeTracker();
        scoreBoard = new ScoreBoard();
        ball = new Ball(obstacles, paddle, lifeTracker);
        view = new View(paddle, ball, obstacles, lifeTracker);
        
        addKeyListener(keyboardInput);
        addMouseListener(mouseInput);
        getContentPane().add(BorderLayout.CENTER, view);
        setVisible(Boolean.TRUE);
        repaint();
    }
    
    /**
     * Main loop of the game
     *
     * @return - Timer
     */
    private Timer run()
    {
        return new Timer(15, e -> {
            
            if (mouseInput.getStart() == Boolean.TRUE || keyboardInput.getStart() == Boolean.TRUE)
            {
                //Wait for input to start the game/round
                view.setPaused(Boolean.FALSE);
                mouseInput.setStart(Boolean.FALSE);
                keyboardInput.setStart(Boolean.FALSE);
            }
            
            if (lifeTracker.getDead() || obstacles.size() == 0)
            {
                if (lifeTracker.getLives() > 0 && obstacles.size() != 0)
                {
                    //Reset the field if there are more lives and obstacles
                    view.setPaused(Boolean.TRUE);
                    ball.reset();
                    paddle.reset();
                    lifeTracker.setDead(Boolean.FALSE);
                    view.update();
                    view.repaint();
                }
                else
                {
                    if (!view.getGameOver())
                    {
                        gameOverScreen();
                    }
                }
            }
            else
            {
                if (view.getPaused() == Boolean.FALSE)
                {
                    //Run the game
                    view.setPaused(Boolean.FALSE);
                    view.update();
                    view.repaint();
                }
            }
            
            if (view.getGameOver() && keyboardInput.getRestart())
            {
                //Restart the game
                reset();
                view.update();
                view.repaint();
            }
        });
    }
    
    /**
     * Display the game over screen and the scores
     */
    private void gameOverScreen()
    {
        view.setGameOver(Boolean.TRUE);
        view.update();
        view.repaint();
        
        //Record the score of the user
        String name = JOptionPane.showInputDialog("Name:", "John Doe");
        if (name != null && !name.equals(""))
        {
            name = name.replace(",", " ");
            scoreBoard.recordScore(name, ball.getPoints());
        }
        
        //Display the scoreboard
        JDialog dialog = new JDialog();
        JTabbedPane tabbedPane = new JTabbedPane();
        
        Component topScores = addScores(scoreBoard.getTopScores());
        Component topRecentScores = addScores(scoreBoard.getTopRecentScores());
        
        tabbedPane.addTab("Top Scores", null, topScores);
        tabbedPane.addTab("Recent Top Scores", null, topRecentScores);
        
        dialog.add(tabbedPane);
        dialog.setSize(300, 500);
        dialog.setVisible(true);
    }
    
    /**
     * Add scores to the panel
     *
     * @param scores - List of scores
     *
     * @return - The complete panel
     */
    private Component addScores(List<Score> scores)
    {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(12, 1));
        
        for (Score score : scores)
        {
            JLabel s = new JLabel(score.toString());
            s.setHorizontalAlignment(JLabel.CENTER);
            panel.add(s);
        }
        
        if (scoreBoard.getReadError())
        {
            JLabel s = new JLabel("There was a problem with loading the scores!");
            s.setHorizontalAlignment(JLabel.CENTER);
            panel.add(s);
        }
        
        if (scoreBoard.getWriteError())
        {
            JLabel s = new JLabel("Your score was not recorded!");
            s.setHorizontalAlignment(JLabel.CENTER);
            panel.add(s);
        }
        
        return panel;
    }
    
    /**
     * Create the level by placing the obstacles
     *
     * @return - List of all obstacles in the level
     */
    private List<RectangleCustom> createLevel()
    {
        List<Color> colors = Arrays.asList(Color.orange, Color.magenta, Color.cyan, Color.yellow, Color.red);
        List<RectangleCustom> rectangles = new ArrayList<>();
        
        for (int i = 0; i < 5; i++)
        {
            
            for (int j = 0; j < 11; j++)
            {
                RectangleCustom rect = new RectangleCustom(50, 20, j * 50.0 + 3.0 * j + 10, 20.0 * i + 3.0 * i + 7);
                rect.setColor(colors.get(i));
                rectangles.add(rect);
            }
        }
        
        return rectangles;
    }
    
    /**
     * Reset the game frame and prepare for new game
     */
    private void reset()
    {
        getContentPane().remove(0);
        init();
    }
}
