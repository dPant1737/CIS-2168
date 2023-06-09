package Assignments.Assignment_7.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list; 
	
	
	
	IndexNode left;
	IndexNode right;
	
	
	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	
	public IndexNode(String word, int lineNum){
		this.word = word;
		list = new ArrayList<>();
		list.add(lineNum);
		occurences = 1;
	}
	
	public void addOccurences(int lineNum){
		list.add(lineNum);
		occurences++;
	}
	
	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	
	public String toString(){
		return "Word:" + word + ", Number of Occurences: " + occurences + "Lines It Appears On:" + list;
	}
	
	
	
}
