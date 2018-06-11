import java.util.Scanner;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
/**
 * EvilHangmanDriver
 * 
 * @Bryan Mira
 * @5/11/2017
 */
public class EvilHangmanDriver 
{
    public static void main(String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to EVIL HANGMAN.");
        int wordSize;
        do {
            System.out.println("Choose size of word.");
            while (!keyboard.hasNextInt()) {
                System.out.println("That's not a number!");
                keyboard.next(); // this is important!
            }
            wordSize = keyboard.nextInt();
        } while (wordSize <= 0);

        int numGuess;
        do {
            System.out.println("Choose how many guesses.");
            while (!keyboard.hasNextInt()) {
                System.out.println("That's not a number!");
                keyboard.next(); // this is important!
            }
            numGuess = keyboard.nextInt();
        } while (numGuess <= 0);

        char wordsLeft;

        do{
            System.out.println("Show Number of Words Left? [y/n]");
            wordsLeft = keyboard.next().charAt(0);
        }
        while(!Character.toString(wordsLeft).matches("^[ny]*$"));

        boolean left;

        if (wordsLeft == 'n')
        {
            left = false;
        }
        else {left= true;}

        String dashed = "";
        for (int k=0; k<wordSize; k++)
        {
            dashed += "-";
        }
        System.out.println(dashed);
        Dictionary<String> dictionaryList = new Dictionary<String>();
        int countWords = 0;
        try
        {
            Scanner file = new Scanner(new File("dictionary.txt"));
            while(file.hasNextLine())
            {
                String word = file.next();
                if(word.length() == wordSize)
                {
                    dictionaryList.add(word,""); 
                    countWords++;
                }
            }
        }
        catch (Exception e)
        {

        }

        Iterator<String> kit = dictionaryList.getKeyIterator();
        Iterator<String> vit = dictionaryList.getValueIterator();

        System.out.println("Number of words: "+countWords);

        char letter;
        do{
            System.out.println("Enter a guess");
            letter = keyboard.next().charAt(0);
        }
        while(!Character.toString(letter).matches("^[a-z]*$"));

        kit = dictionaryList.getKeyIterator();
        String value = "";
        while (kit.hasNext())
        {
            String word2=kit.next();
            String index2 = "";
            for (int index = word2.indexOf(letter);index >= 0; index = word2.indexOf(letter, index + 1))
                index2 += String.valueOf(index);
            if (index2 == ""){index2="-1";}
            dictionaryList.add(word2,index2);
        }

        String mostFreq = dictionaryList.mostFrequent();
        kit = dictionaryList.getKeyIterator();
        vit = dictionaryList.getValueIterator();
        Dictionary<String> familyList = new Dictionary<String>();
        while (kit.hasNext())
        {
            String temp = kit.next();
            if (mostFreq.compareTo(dictionaryList.getValue(temp)) == 0)
                familyList.add(temp,""); 
        }
        System.out.println("Dashed: "+dashed);
        if (left)
            System.out.println("Words remaining: "+familyList.getSize());


        while (numGuess > 0 && dashed.contains("-"))
        {
            do{
                System.out.println("Enter a guess");
                letter = keyboard.next().charAt(0);
            }
            while(!Character.toString(letter).matches("^[a-z]*$"));
            wordFamilies(familyList, Character.toString(letter), dashed);
            dashed=displayDashed(Character.toString(letter),familyList.mostFrequent(),dashed);
            System.out.println("Dashed: "+dashed);
            numGuess--;
            System.out.println("Guess remaining: "+numGuess);
            if (left)
                System.out.println("Words remaining: "+familyList.getSize());

        }
    }

    //transfer ArrayList to new temp ArrayList
    //clear ArrayList
    //temp ArrayList to original ArrayList

    /* guess <e - 2,3,6>
     * frequency <e - 2,3,6 - 20>
     *  if highest store in variable max
     * 
     */

    public static void wordFamilies (Dictionary familyList, String letter, String dashed)
    {
        Iterator<String>kit = familyList.getKeyIterator();
        while (kit.hasNext())
        {
            String word2=kit.next();
            String index2 = "";
            for (int index = word2.indexOf(letter);index >= 0; index = word2.indexOf(letter, index + 1))
                index2 += String.valueOf(index)+",";
            if (index2 == ""){index2="-1";}
            familyList.add(word2,index2);
        }

        Iterator<String> newkit = familyList.getKeyIterator();
        Iterator<String> newvit = familyList.getValueIterator();

        newkit = familyList.getKeyIterator();
        newvit = familyList.getValueIterator();
        String mostFreq2 = familyList.mostFrequent();
        Dictionary<String> familyList2 = new Dictionary<String>();
        while (newkit.hasNext())
        {
            String temp = newkit.next();
            String temp2 = newvit.next();
            if (mostFreq2.compareTo(familyList.getValue(temp)) == 0)
                familyList2.add(temp, temp2);

        }

        Iterator<String> newkit2 = familyList2.getKeyIterator();
        Iterator<String> newvit2 = familyList2.getValueIterator();
        familyList.clear();
        while (newkit2.hasNext())
        {
            familyList.add(newkit2.next(),newvit2.next());
        }



    }

    //if value (or mostFrequent) is not -1 add letter to dashed.
    //ex 2-letterword value 0 letter e: e-
    //replace dash with letter in appropriate index
    public static String displayDashed (String letter, String values, String dashed)
    {
        if (values.compareTo("-1")!=0){
            StringTokenizer valuesToken = new StringTokenizer(values,",",false);
            StringBuilder newDashed = new StringBuilder(dashed);
            while (valuesToken.hasMoreTokens())
            {
                String indexS = valuesToken.nextToken();
                int indexNum = Integer.parseInt(indexS);
                newDashed.setCharAt(indexNum,letter.charAt(0));

            }
            dashed = newDashed.toString();}
        return dashed;
    }
}
