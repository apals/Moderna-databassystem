import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Main {

	public static void main(String[] args) {
		MaxentTagger t = new MaxentTagger("tagger/english-left3words-distsim.tagger");
		String sentence = "Jemil is a warrior. Mike has a big leg. The wall is cool.";
		
		System.out.println();
		System.out.println(sentence);
		System.out.println(t.tagString(sentence));
	}	
	
}

/***

Kastrerade barn är snygga.
Fredrik är cool.




***/