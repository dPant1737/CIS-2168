package Assignments.Assignment_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CheatersHangman2 {
    public static int sizeOfSet = 0;
    public static int wordLen = 1;
    public static Character finalGuess;
    
    public static void main(String[] args) {
            String fileName = "words.txt";
            Set<String> setofWords = new HashSet<>();
            int largestWord = 0;
            //adding dictionary to setOfWords
            try{
                Scanner dict = new Scanner(new File(fileName));
                while (dict.hasNext()) {
                    String word = dict.next().toLowerCase();
                    int lengthOfWord = word.length();
                    if (lengthOfWord > largestWord) {
                        largestWord = lengthOfWord;
                    }
                    setofWords.add(word);
                    sizeOfSet++;
                } 
            } catch(FileNotFoundException e){
                e.printStackTrace();
                System.out.println(fileName + "cannot be found");
            }
        


            //Play game
            setofWords = wordSetSelection(setofWords, largestWord); //selecting all words of user chosen length
            int numGuesses;
            int correctguesses = 0;
            List<String> wordList = new ArrayList<>();
            Map<String, List<String>> wordFam = new HashMap<>();
            Set<Character> guessedLetters = new HashSet<>();
            String board = "";

            Scanner scanner = new Scanner(System.in);
            System.out.println("How many guesses do you want?");
            numGuesses = scanner.nextInt();

            for (String word : setofWords) {
                wordList.add(word);
            }
            for(int i = 0; i < wordLen; i++){
                board += "_";
            }

            while(numGuesses > 0 && !(win(wordList, board))){
                System.out.println("");
                System.out.println("Number of guesses:" + numGuesses);
                System.out.println(board);
                System.out.println("");
                System.out.println("Guessed letters:");
                System.out.println(guessedLetters);
                System.out.print("Guess a letter: ");
                

                String s = scanner.next();
                Character c = s.charAt(0);
                while (guessedLetters.contains(c)) {
                    System.out.print("Letter alredy guessed. Guess again:");
                    String str = scanner.next();
                    c = str.charAt(0);
                }
                guessedLetters.add(c);
                wordFam = genWordFam(guessedLetters, wordList);
                String chooseBestFamily = getBestWordFam(wordFam);
                wordList = wordFam.get(chooseBestFamily);
                
                if (board.equals(chooseBestFamily)) {
                    numGuesses--;
                if(numGuesses == 0){
                    finalGuess = c; 
                }
                } else {
                    correctguesses++; 
                }
                board = chooseBestFamily;
            }
            if((win(wordList,board))){
                System.out.println("You won");
            } else {
                System.out.println("Sorry you lost the word was: " + revealWord(wordList,finalGuess));
            }
        }
        
        
    

    public static Map<String, List<String>> genWordFam(Set<Character> guessedLetters, List<String> wordList){
        Map<String, List<String>> wordFam = new HashMap<>();
        for(String word: wordList){
            String family = "";
            for (int i = 0; i < word.length(); i++) {
                if(guessedLetters.contains(word.charAt(i))){
                    family += word.charAt(i);
                }else{
                    family += "_";
                }
            }
            if (wordFam.containsKey(family)) {
                List<String> list = wordFam.get(family);
                list.add(word);
                wordFam.put(family, list);
            } else{
                List<String> list = new ArrayList<>();
                list.add(word);
                wordFam.put(family, list);
            }
        }
        return wordFam;
    }


    public static String getBestWordFam(Map<String, List<String>> wordFam){
        int size = 0;
        String bestFamily = "";
        for (Map.Entry<String, List<String>> families : wordFam.entrySet()) {
            if (families.getValue().size() > size) {
                size = families.getValue().size();
                bestFamily = families.getKey();
            }
        }

        return bestFamily;
    }
    
    public static Set<String> wordSetSelection(Set<String> setOfWords, int largestWord){
        Set<String> set = new HashSet<>();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many letters do you want in your word?");
        wordLen = scanner.nextInt();
        while (wordLen <= 0 || wordLen >= largestWord) {
            System.out.println("There was no words of that length in dictionary. Select another word size.");
            wordLen = scanner.nextInt();
        }
        if (wordLen > 0 && wordLen <= largestWord) {
            sizeOfSet = 0;
            for (String word : setOfWords) {
                if(word.length() == wordLen){
                    set.add(word);
                    sizeOfSet++;
                }
            }
        }
        return set;
    }
    

    public static boolean win(List<String> list, String board){
        if(list.size() == 1 && list.get(0).equals(board)){
            return true;
        }else{
            return false; 
        }
    }
    
    public static String revealWord(List<String> list, Character h){
        for(String word : list){
            int counter = 0; 
            for (int i = 0; i < word.length(); i++) {
                if(word.charAt(i) != h){
                    counter++;
                }
            }
            if(counter == word.length()){
                return word; 
            }
        }
        return list.get(0); 
    }

}
