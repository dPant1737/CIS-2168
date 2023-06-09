package Assignments.Assignment_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CheatersHangman {
    private static final String FILE_NAME = "words.txt";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner dict = new Scanner(new File(FILE_NAME));
        final Set<Character> guessedCharacters = new HashSet<>();
        final Map<Integer, Character> correctCharacters = new HashMap<>();

        //User Input
        List<String> wordFam = null;
        int userWordCount = 0;

        while (wordFam == null) {
            userWordCount = intQ("How many letters do you want in your word?");
            wordFam = getWordsToCheatWith(dict, userWordCount);

            if (wordFam.size() == 0) {
                System.out.println("No words in dictionary with " + userWordCount+ " letters");
                dict = new Scanner(new File(FILE_NAME));
                wordFam = null;
            }

        }
        int maxGuesses = intQ("How many guesses would you like?");

        while((correctCharacters.size() != userWordCount) && (maxGuesses- (guessedCharacters.size() - correctCharacters.size()) > 0)){
            StepA(correctCharacters, guessedCharacters, userWordCount, maxGuesses);
            char guessedCharacter = StepB(guessedCharacters);
            boolean correctlyGuessed = StepC(guessedCharacter, wordFam);
            if (correctlyGuessed) {
                String chosenWord = wordFam.get(0);

                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == guessedCharacter) {
                        correctCharacters.put(i, guessedCharacter);
                    }
                }
            }
        }
        if ((maxGuesses - (guessedCharacters.size() - correctCharacters.size())) > 0) {
            System.out.println("You got the word correct");
            System.out.println("The word is: " + wordFam.get(0));
        } else{
            System.out.println("You got the word incorrect");
            System.out.println("The word is: " + wordFam.get(0));
        }
        
    }

    public static void StepA(Map<Integer, Character> correctCharacters, Set<Character> guessedCharacters, int userWordCount,int maxGuesses){
        StringBuilder revealedLetterOutput = new StringBuilder();

        for (int i = 0; i < userWordCount; i++) {
            if (correctCharacters.containsKey(i)) {
                revealedLetterOutput.append(correctCharacters.get(i)).append(" ");
            } else{
                revealedLetterOutput.append("_ ");
            }
        }
        System.out.println(revealedLetterOutput.toString()); //reveal letter
        System.out.println("Wrong Guesses: " + (guessedCharacters.size() - correctCharacters.size()));
        System.out.println("Attempts left: " + (maxGuesses - (guessedCharacters.size() - correctCharacters.size())));
    }

    public static char StepB(Set<Character> guessedCharacters){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Next guess: ");
            String letterCon = scanner.next().toUpperCase();
            if(letterCon.length() != 1){
                System.out.println("Only enter one  letter");
            } else{
                char letter = letterCon.charAt(0);
                if (!guessedCharacters.add(letter)) {
                    System.out.println("letter already guessed");
                } else{
                    return letter;
                }
            }
        }
    }

    public static boolean StepC(char guessedCharacters, List<String> wordFam){
        for (int i = 0; i < wordFam.size(); i++) {
            String currString = wordFam.get(i);
            if(wordFam.size() <= 1){
                return true;
            }

            if(currString.indexOf(guessedCharacters) != -1){
                wordFam.remove(currString);
                i--;
            }
        }
        return false;
    }

    public static List<String> getWordsToCheatWith(Scanner wordList, int requiredLen) {
		List<String> wordFam = new LinkedList<>();

		while (wordList.hasNext()) {
			String curr = wordList.next();

			if (curr.length() == requiredLen)
				wordFam.add(curr.toUpperCase());
		}

		return wordFam;
	}

    public static int intQ(String output) {
	Scanner myScanner = new Scanner(System.in);

		while(true)
			try {
				System.out.print(output + " ");
				return myScanner.nextInt();
			} catch (Exception e) {
				System.out.println("This is not a valid number, only enter integers");
				myScanner.next(); // Move past this incorrect token
			}
	}
}