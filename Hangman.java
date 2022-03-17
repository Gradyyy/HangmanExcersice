import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;


public class Hangman{
	final String WORDLIST_FILENAME = "D:\\Binus Semester 4\\OOP\\HangmanAssignment\\src\\com\\packaged\\words.txt";

	public Hangman(){
		ArrayList<String> wordList = loadWords();
		String secretWord = chooseWord(wordList).toLowerCase();
		startGame(secretWord);
	}

	/**
	 * Returns a list of valid words. Words are strings of lowercase letters.
	 * Depending on the size of the word list, this function may
	 * take a while to finish.
	 */
	ArrayList<String> loadWords(){
		ArrayList<String> wordList = new ArrayList<>(55909);
		System.out.println("Loading word list from file...");
		try{
			Scanner input = new Scanner(new File(WORDLIST_FILENAME));
			while(input.hasNext()){
				wordList.add(input.next());
			}
		}
		catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
		System.out.println(wordList.size() + " " + "words loaded.");

		return wordList;
	}

	/**
	 * wordlist (list): list of words (strings)
	 * Returns a word from wordlist at random
	 */
	String chooseWord(ArrayList<String> wordList){
		//FILL IN YOUR CODE HERE...
		Random r = new Random();
		int randomitem = r.nextInt(55909);
		String randomElement = wordList.get(randomitem);
		return randomElement;
	}

	/**
	 * secretWord: string, the word the user is guessing
	 * lettersGuessed: list, what letters have been guessed so far
	 * returns: boolean, True if all the letters of secretWord are in lettersGuessed;
	 * False otherwise
	 */
	 boolean isWordGuessed(String secretWord, ArrayList<Character> lettersGuessed){
		 //FILL IN YOUR CODE HERE...
		for(int i=0;i<secretWord.length();i++){
			if(!lettersGuessed.contains(secretWord.charAt(i))){
				return false;
			}
		}
		return true;
	 }

	 /**
	  * secretWord: string, the word the user is guessing
	  * lettersGuessed: list, what letters have been guessed so far
	  * returns: string, comprised of letters and underscores that represents
	  * what letters in secretWord have been guessed so far.
      */
	 String getGuessedWord(String secretWord, ArrayList<Character> lettersGuessed){
		 //FILL IN YOUR CODE HERE...
		 String blank = "";
		 for(int i=0;i<secretWord.length();i++){
		 	blank = blank +"_";
		 }

		 for(int i=0;i<secretWord.length();i++){
		 	if(lettersGuessed.contains(secretWord.charAt(i))){
				blank = blank.substring(0, i) + secretWord.charAt(i) +blank.substring(i + 1);
			}
		 }
		 return blank;
	 }

	 /**
	  * lettersGuessed: list, what letters have been guessed so far
	  * returns: string, comprised of letters that represents what letters have not
	  * yet been guessed.
	  */
	 String getAvailableLetters(ArrayList<Character> lettersGuessed){
		 //FILL IN YOUR CODE HERE...
		 String alpha = "abcdefghijklmnopqrstuvwxyz";
		 if(lettersGuessed.size() == 0){
			 return alpha;
		 }
		 for(int i=0;i<lettersGuessed.size();i++){
			 for(int j=0;j<alpha.length();j++){
				 if(lettersGuessed.get(i) == alpha.charAt(j)){
					 alpha = alpha.substring(0, j) + alpha.substring(j + 1);
				 }
			 }
		 }
		 return alpha;
	 }

	/**
	 * secretWord: string, the secret word to guess.
	 *
	 * Starts up an interactive game of Hangman.
	 *
	 * At the start of the game, let the user know how many
	 * letters the secretWord contains.
	 *
	 * Ask the user to supply one guess (i.e. letter) per round.
	 *
	 * The user should receive feedback immediately after each guess
	 * about whether their guess appears in the computers word.
	 *
	 * After each round, you should also display to the user the
	 * partially guessed word so far, as well as letters that the
	 * user has not yet guessed.
	 *
	 * Follows the other limitations detailed in the problem write-up.
	 */
	 void startGame(String secretWord){
		 //FILL IN YOUR CODE HERE...
		 System.out.println("Welcome to the game, Hangman!");
		 System.out.println("I'm thinking of a word that is "+ secretWord.length() + " letters long");
		 System.out.println("_____________________________");

		 int counter = 8;
		 ArrayList<Character> lettersGuessed = new ArrayList<Character>();

		 while(counter!=0){
			 System.out.println("You have "+ counter + " guesses left");
			 System.out.println("Available letter: "+ getAvailableLetters(lettersGuessed));
			 System.out.print("Please guess a letter: ");
//			 Hanya ambil yang paling depan
			 Scanner scan = new Scanner(System.in);
			 char c = scan.next().charAt(0);

//			 check ada ga
			 boolean guessed = false;
			 for(int i=0;i<secretWord.length();i++){
			 	if(secretWord.charAt(i) == c){
			 		if(lettersGuessed.contains(c) == false){
			 			lettersGuessed.add(c);
					}
			 		guessed = true;
				}
			 }

			 if(guessed){
			 	System.out.println("Good Guess: "+ getGuessedWord(secretWord,lettersGuessed));

			 }
			 else if(lettersGuessed.contains(c)){
				 System.out.println("Oops! You've already guessed that letter: " + getGuessedWord(secretWord,lettersGuessed));
			 }
			 else{
				 System.out.println("OOPS That letter is not in my word: " + getGuessedWord(secretWord,lettersGuessed));
				 counter--;
			 }

			 if(isWordGuessed(secretWord,lettersGuessed)){
				 System.out.println("Congratulation you won!");
				 break;
			 }
			 System.out.println("_______________________");

		 }

		 if(!isWordGuessed(secretWord,lettersGuessed)){
			 System.out.println("You Lose");
		 }

	 }

	 public static void main(String[] args){
		Hangman hangman = new Hangman();
	 }
}
