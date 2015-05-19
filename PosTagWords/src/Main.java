import java.io.File;
import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Main {

	private File file;
	private Scanner scanner;
	
	public static void main(String[] args) {
		MaxentTagger t = new MaxentTagger("tagger/english-left3words-distsim.tagger");
		String sentence = "Jemil is a warrior. Mike has a big leg. The wall is cool.";
		
		openFile(args[0]);
		
		System.out.println();
		System.out.println(sentence);
		System.out.println(t.tagString(sentence));
	}	
	
	private static void openFile(String filename) {
		
	}
	
}

/***

Kastrerade barn är snygga.
Fredrik är cool.




***/