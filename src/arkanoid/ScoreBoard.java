package arkanoid;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <br><br>
 *
 * @author Dobromir
 * Created on: 06/Dec/2017
 */

public class ScoreBoard
{
    //File with scores
    private File scoreFile = new File("src/scores.txt");
    
    //Flags if any error occurs during read/write
    private Boolean readError = Boolean.FALSE;
    private Boolean writeError = Boolean.FALSE;
    
    /**
     * Get the top 10 scores of all time
     *
     * @return - List of the top 10 scores
     */
    public List<Score> getTopScores()
    {
        List<Score> scores = getScores();
        scores.sort(Comparator.comparing(Score::getScore).reversed());
        if (scores.size() > 10)
        {
            return scores.subList(0, 10);
        }
        
        return scores;
    }
    
    /**
     * Get the top 10 scores for the past 24 hours
     *
     * @return - List of the top 10 scores for the past 24 hours
     */
    public List<Score> getTopRecentScores()
    {
        List<Score> scores = getScores();
        
        Iterator<Score> iterator = scores.iterator();
        
        while (iterator.hasNext())
        {
            Score scoreEntry = iterator.next();
            LocalDateTime timeNow = LocalDateTime.now();
            
            if (scoreEntry.getTime().isBefore(timeNow.minusHours(24)))
            {
                iterator.remove();
            }
        }
        
        scores.sort(Comparator.comparing(Score::getScore).reversed());
        
        if (scores.size() > 10)
        {
            return scores.subList(0, 10);
        }
        
        return scores;
    }
    
    /**
     * Get all scores from a text file
     *
     * @return - List of all the scores obtained from the file
     */
    private List<Score> getScores()
    {
        List<Score> scores = new ArrayList<>();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(scoreFile)))
        {
            String line = bufferedReader.readLine();
            
            while (line != null)
            {
                List<String> scoreString = Arrays.asList(line.split(","));
                Integer score = Integer.parseInt(scoreString.get(1));
                LocalDateTime time = LocalDateTime.parse(scoreString.get(2));
                
                Score scoreEntry = new Score(scoreString.get(0), score, time);
                scores.add(scoreEntry);
                
                line = bufferedReader.readLine();
            }
            
            return scores;
        }
        catch (Exception e)
        {
            readError = Boolean.TRUE;
            return scores;
        }
    }
    
    /**
     * Record a new score of a person in a text file
     *
     * @param name  - Name of the person
     * @param score - The score of the person
     */
    public void recordScore(String name, Integer score)
    {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreFile, true)))
        {
            LocalDateTime timeNow = LocalDateTime.now();
            String scoreRecord = name + "," + score + "," + timeNow + "\n";
            bufferedWriter.write(scoreRecord);
        }
        catch (IOException e)
        {
            writeError = Boolean.TRUE;
        }
    }
    
    //------------------------- ACCESSORS-------------------------
    
    public Boolean getReadError()
    {
        return readError;
    }
    
    public Boolean getWriteError()
    {
        return writeError;
    }
}
