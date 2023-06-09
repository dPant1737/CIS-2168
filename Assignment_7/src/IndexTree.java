package Assignments.Assignment_7.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

	// This is your root 
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	
	// Make your constructor
	// It doesn't need to do anything
	public IndexTree(){
		root = null;
	}
	// complete the methods below
	
	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){
		root = add(root, word, lineNumber);
	}
	
	
	
	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
		
		if(root == null){
            return root = new IndexNode(word, lineNumber);
        } else{
			int comparison = word.compareTo(root.word);
			if (comparison == 0) {
				root.addOccurences(lineNumber);
				return root;
			} else if(comparison < 0){
				root.left = add(root.left, word, lineNumber);
				return root;
			} else{
				root.right = add(root.right, word, lineNumber);
				return root;
			}
		}
	}
	
	
	
	
	// returns true if the word is in the index
	public boolean contains(String word){
		return contains(root,word);
	}
	private boolean contains(IndexNode root, String word){
		if (root == null) {
			return false;
		}
		int comparison = word.compareTo(root.word);
		if (comparison == 0) {
			return true;
		} else if(comparison < 0){
			return contains(root.left, word);
		} else{
			return contains(root.right, word);
		}
	}
	// call your recursive method
	// use book as guide
	public void delete(String word){
		root = delete(root, word);
	}
	
	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
		if(root == null){
            return null;
        }
        int comparison = word.compareTo(root.word);
        if (comparison < 0) {
            root.left = delete(root.left, word);
            return root;
        } else if(comparison > 0){
            root.right = delete(root.right, word);
			return root;
        } else{
            if (root.left == null && root.right == null) {
                return null;
            } else if(root.left != null && root.right == null){
				IndexNode curr = root.left;
				while (curr.right != null) {
					curr = curr.right;
				}
				root.word = curr.word;
				root.occurences = curr.occurences;
				root.list = curr.list;
				root.left = delete(root.left, root.word);
                return root;
            } else{
				if (root.left != null) {
					return root.left;
				} else{
					return root.right;
				}
			} 
        } 

	}
	
	
	// prints all the words in the index in inorder order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line
	public void printIndex(){
		printIndex(root);
	}
	
	private void printIndex(IndexNode root){
		if (root != null) {
			printIndex(root.left);
			System.out.println(root.toString());
			printIndex(root.right);
		}
	}
	public static void main(String[] args){
		IndexTree index = new IndexTree();
		
		// add all the words to the tree
		String fileName = "pg100.txt";
		try {
			Scanner scanner = new Scanner(new File(fileName));
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				lineNum++;
				String line = scanner.nextLine();
				String[] words = line.split("\\s+");
				for(String word : words){
					word = word.replaceAll("\\p{Punct}", "");
					index.add(word, lineNum); 
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// test removing a word from the index
		index.delete("zodiac");
		index.delete("zounds");
		index.printIndex();
	}
}














