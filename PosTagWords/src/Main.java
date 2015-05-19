import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Main {

	public static void main(String[] args) throws IOException {
		MaxentTagger t = new MaxentTagger("tagger/english-left3words-distsim.tagger");
		File file = new File("files/testdump");
		File outFile = new File("files/wikidump_sentence_splitted_pos_tagged");
		Scanner scanner = new Scanner(file);
		
		FileWriter fw = new FileWriter(outFile);
		BufferedWriter bw = new BufferedWriter(fw);
		
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			String taggedSentence = t.tagString(line);
			bw.write(taggedSentence + "\n");
		}
		scanner.close();
		bw.close();
		
	}	
	
}