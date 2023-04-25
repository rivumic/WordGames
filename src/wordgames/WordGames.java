package wordgames;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Rama Nicholson
 *Date 30/08//2020
 * This program presents two word games to the user. One game involves the
 * searching the words in a dictionary for a given letter and reporting
 * to the user where in each word the letter appears, as a prefix, infix or
 * suffix. The second game calculates a point score for each word in a
 * based on a point value for each letter of the alphabet; the total scores for
 * each word is reported to the user.
 */
public class WordGames {
    
    static Scanner keyboard = new Scanner(System.in);
    static File dictionary = new File("dictionary.txt");
    static int menuSelection = 0;/*stores the user's choice at menu,
    menuSelection is initialised outside main() so that it is accessible from
    both main() and getSelection()
    */
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * The main method checks that an appropriate dictionary is available,
     * calls the getSelection() method, and upon receiving the user's selection
     * calls the appropriate method or ends the program.
     */
    public static void main(String[] args) throws FileNotFoundException {
        if(!dictionary.isFile()){ // checks for dictionary existence
            System.out.println("Dictionary file not present\nClosing...");
            System.exit(0);//ends program if dictionary is not available
        }
        while (menuSelection != 3){
            menuSelection = getSelection();
            switch (menuSelection) {
                case 1:
                    substringProblem();
                    break;
                case 2:
                    pointsProblem();
                    break;
                case 3:
                    System.out.println("Thanks for playing!");
                    break;
            }
        }
    }
    public static int getSelection() {
        System.out.println("Welcome to the Word Games program menu.\n"
            + "Select from one of the following options.\n"
            + "1. Substring problem.\n"
            + "2. Points problem.\n"
            + "3. Exit.\n"
            + "Please enter your selection:");//prints menu
        
            menuSelection=0;/* resets menuSelection value. Otherwise the wrong
            value will be stored when multiple cycles of each game are played
            */
            while(menuSelection!=1&&menuSelection!=2&&menuSelection!=3){
             //loop will repeat until a valid menu selection is acquired
                if (keyboard.hasNextInt()){//checks that input is of type int
                    menuSelection=keyboard.nextInt();
                    if (menuSelection!=1&&menuSelection!=2&&menuSelection!=3){
                //checks for invalid inputs that are of type int e.g. 4, 5 etc.
                        System.out.println("Invalid selection,"
                                + " please try again:");
                    }
                }
                else{
                    keyboard.next();/*
                    moves scanner onto next token so that the non-int input that
                    triggered the "else" doesn't cause an infinite loop
                    */
                    System.out.println("Invalid selection, please try again:");
                }
            } 
        return menuSelection;
    }
    /*
    @throws java.io.FileNotFoundException
    This method executes the "substring" game. It receives a user input, finds
    which words of the dictionary the character appears in, and its location in
    the word. All words of the dictionary are then printed; if the user's input
    is a "fix" of a word then each applicable "fix" is printed next to the word,
    otherwise, " - not found" is printed next to the word.
    */
    public static void substringProblem() throws FileNotFoundException{
        Scanner fileScanner = new Scanner(dictionary);//facilitates file input
        System.out.println("Enter a character:");//prompts user input
        char userChoice = keyboard.next().charAt(0);
        /*gets user input, if multiple characters are entered,
        the first is taken */
        while (fileScanner.hasNext()){//loops through each word in dictionary
            String fixTestee = fileScanner.nextLine();//gets word to be tested
            String prefix = "";
            String infix = "";
            int infixCounter=0;//holds instances of user input in infix position
            String suffix = "";//resets value after each "while" cycle
            if (userChoice==fixTestee.charAt(0)){//adds "prefix" to output
                prefix = "- prefix ";
            }
            //this "for" checks chars between first and last for user's input
            for (int i=fixTestee.length()-2;i>0;i--){
                if (fixTestee.charAt(i)==userChoice){
                    infixCounter++;
                }
            }
            if (infixCounter>0){//adds "infix" to output
            infix="- infix ";
            }
            if (userChoice==fixTestee.charAt(fixTestee.length()-1)){//as above
                suffix = "- suffix ";
            }
            if (prefix.equals("- prefix ")||infix.equals("- infix ")||
                    suffix.equals("- suffix ")){
                System.out.println(fixTestee+" "+prefix+infix+suffix);
            }
            else {
                System.out.println(fixTestee+" - not found");
            }
        }
    }
    
    public static void pointsProblem() throws FileNotFoundException{
        Scanner fileScanner = new Scanner(dictionary);//facilitates file input
        while (fileScanner.hasNext()){//loops through every word in dictionary
            int pointValue = 0;//resets value for each word
            String currentWord = fileScanner.nextLine();//gets word for calc
            for (int i = currentWord.length()-1;i>-1;i--){
                //the "for" loops through each character of the word
                switch (currentWord.charAt(i)){
                //the "switch" designates the point for each letter
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'l':    
                    case 'n':
                    case 'o':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                        pointValue++;//letters worth 1 point
                        break;
                    case 'd':
                    case 'g':
                        pointValue = pointValue +2;//leters worth 2 points
                        break;
                    case 'b':
                    case 'c':
                    case 'm':
                    case 'p':
                        pointValue = pointValue +3;//letters worth 3 points
                        break;
                    case 'f':
                    case 'h':
                    case 'v':
                    case 'w':
                    case 'y':
                        pointValue = pointValue +4;//letters worth 4 points
                        break;
                    case 'k':
                        pointValue = pointValue +5;//letters worth 5 points
                        break;
                    case 'j':
                    case 'x':
                        pointValue = pointValue +8;//letters worth 8 points
                        break;
                    case 'q':
                    case 'z':
                        pointValue = pointValue +10;//letters worth 10 points
                        break;
                }
            }
            System.out.println(currentWord+" is worth "+pointValue+" points.");
            //prints the word and its score
        }
    }
}
