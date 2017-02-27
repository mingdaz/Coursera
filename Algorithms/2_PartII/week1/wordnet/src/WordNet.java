/**
 * Created by mingdzhang on 1/30/17.
 */

public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){
        if(synsets==null) throw new NullPointerException("synsets is null");
        if(hypernyms==null) throw new NullPointerException("hypernyms is null");

    }

    // returns all WordNet nouns
    public Iterable<String> nouns(){

    }

    // is the word a WordNet noun?
    public boolean isNoun(String word){
        if(word == null) throw new NullPointerException("word is null");
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        if(nounA == null) throw new NullPointerException("nounA is null");
        if(nounB == null) throw new NullPointerException("nounB is null");
        if(!isNoun(nounA)) throw new IllegalArgumentException("nounA is not wordnet noun");
        if(!isNoun(nounB)) throw new IllegalArgumentException("nounB is not wordnet noun");
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
        if(nounA == null) throw new NullPointerException("nounA is null");
        if(nounB == null) throw new NullPointerException("nounB is null");
        if(!isNoun(nounA)) throw new IllegalArgumentException("nounA is not wordnet noun");
        if(!isNoun(nounB)) throw new IllegalArgumentException("nounB is not wordnet noun");

    }

    // do unit testing of this class
    public static void main(String[] args){

    }
}
