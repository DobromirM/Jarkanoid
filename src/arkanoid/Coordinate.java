package arkanoid;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class Coordinate
{
    private final Double x;
    private final Double y;
    
    public Coordinate(Double x, Double y)
    {
        this.x = x;
        this.y = y;
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Double getX()
    {
        return x;
    }
    
    public Double getY()
    {
        return y;
    }
}
