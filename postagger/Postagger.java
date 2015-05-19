import java.io.*;
import java.util.*;
import java.io.IOException;

public class Postagger {
    private int article_id;
    private long token_id;
    private long sentence_id;
    public Postagger(int aid) {
        article_id = aid;
        token_id = 0;
        sentence_id = 0;
    }
    //doing the whole process
    void do_stuff(String filename) {
        try {
            File file = new File(filename);
            Scanner sc  = new Scanner(file);
            while(sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                for(int i = 0; i < line.length; i++) {
                    token_it(line[i]);
                }
              sentence_id++;                 
            }
            sc.close();
        }catch(Exception e) {
            System.out.println("wow crash!");
        }
    }

    //for each word, split the _ and create a row
    public void token_it(String word) {
        word = word.replaceAll("\\.", ""); //remove all dots [ if word contains "julle." remove "."
        String [] words = word.split("_");
        if(words.length == 2) {
            System.out.println(token_id + "." + words[0] + "." + words[1] + ".");
            token_id++;
        }       
    }
    //main <article id> <filnamn>
    public static void main(String [] args) {
        Postagger pg = new Postagger(Integer.parseInt(args[0]));
        pg.do_stuff(args[1]);
    }
}
