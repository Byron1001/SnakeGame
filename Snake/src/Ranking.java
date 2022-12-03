import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ranking {
    private static final File historyScoreFile = new File ("history.txt");
    private static ArrayList<String> scoreHistory = new ArrayList<>();

    public static void readHistory(){
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

    public static ArrayList<String> getHistory(){
        readHistory();
        return scoreHistory;
    }

    public static void main(String args[]){
        ArrayList<String> history = getHistory();
        for(String s : scoreHistory){
            System.out.println(s);
        }
    }
}