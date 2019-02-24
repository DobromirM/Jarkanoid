package arkanoid;

import javax.swing.*;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Main
{
    public static void main(String[] args)
    {
        GameFrame screen = new GameFrame();
        screen.setTitle("Jarkanoid");
        screen.pack();
        screen.setVisible(true);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
