import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author SoongMingLiang (soongmingliang647@gmail.com)
 * @version 1.0.0
 *
 * This class is to provide the ranking system service to the SnakeGame.
 * The methods that are available to other classes currently are
 * getHistory(), insertScore(int new_score), and resetHistory()
 * The main functionalities of these methods are return the game score history, insert a new score into the file, and
 * reset the score history of the game respectively.
 */
public class Ranking {
    private static final File historyScoreFile = new File ("history.txt");
    private static ArrayList<String> scoreHistory = new ArrayList<>();
    private static ArrayList<Integer> scoreHistory_write = new ArrayList<>();

    private static void readHistory(){
        try(BufferedReader scoreReader = new BufferedReader(new FileReader(historyScoreFile))){
            String score = scoreReader.readLine();
            if(score == null){
                scoreHistory.add("0");
            }
            else{
                while (score != null) {
                    scoreHistory.add(score);
                    score = scoreReader.readLine();
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Caution!!!");
            System.out.println("File is not found! A new file will be generated for you.");
            System.out.println("Note: All local history will be clear out!");
            try{
                if(historyScoreFile.createNewFile()){
                    for(int i = 0; i < 3; i++){
                        System.out.print(".");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.out.println("\nYour history score file has been created successfully!");
                    System.out.println("You are good to go now, kindly restart this program.");
                }
            }
            catch(IOException | InterruptedException ioe){
                ioe.printStackTrace();
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private static void sortHistory(){
        Collections.fill(scoreHistory_write, 0);
        for(var i : scoreHistory){
            scoreHistory_write.add(Integer.parseInt(i));
        }
        Collections.sort(scoreHistory_write);
    }

    public static ArrayList<String> getHistory(){
        readHistory();
        return scoreHistory;
    }

    public static void insertScore(int new_score){
        String newScore = String.valueOf(new_score);
        scoreHistory.add(newScore);
        sortHistory();
        try(FileWriter scoreWriter = new FileWriter(historyScoreFile, false)){
            for(var i : scoreHistory_write){
                if(i == 0){
                    continue;
                }
                else{
                    scoreWriter.write(i + "\n");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void resetHistory(){
        try(FileWriter scoreWriter = new FileWriter(historyScoreFile, false)){
            scoreWriter.write("");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Collections.fill(scoreHistory, null);
        Collections.fill(scoreHistory_write, 0);
    }

    public static void main(String args[]){
        /*ArrayList<String> history = getHistory();
        for(String s : history){
            System.out.println(s);
        }*/

        /*insertScore(10);
        insertScore(300);
        insertScore(100);*/

        /*ArrayList<String> history = getHistory();
        for(String s : history){
            System.out.println(s);
        }*/

        /*resetHistory();*/
    }
}