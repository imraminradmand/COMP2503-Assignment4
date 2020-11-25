import java.util.Scanner;
import java.util.Iterator;

/** 
 * COMP 2503 Fall 2019 Assignment 4 
 * 
 * This program reads a text file and compiles a list of the 
 * words together with how many times each word appears. 
 * 
 * Words are converted to all lowercase.
 * Punctuation and numbers are skipped.
 *
 * It uses Binary Search Trees to store the Word objects.
 * It uses iterator objects to access the data in the BST.
 * 
 * Last updated by @author Maryam Elahi
 * @date Fall 2019
 * 
 */

public class A4 
{
   /* The lists (trees) of words. 
    * List (tree) of unique words, ordered alphabetically
    * List (tree) of keywords, ordered by the keyword comparator
    * List (tree) of longwords, ordered by the longword comparator 
    * */
   private BST<Word> words = new BST<Word>();
   private BST<Word> keywordsByFreqDesc = new BST<Word>(Word.CompFreqDesc);
   private BST<Word> longwordsByLengthDesc = new BST<Word>(Word.CompLengthDesc);
   private int averageLengthLongWords = 0;
   private int averageFreqKeywords = 0;

   private Scanner input = new Scanner(System.in);

   public static void main(String[ ] args) // got help from soren on this class
   {	
      A4 a4 = new A4();
      a4.run();
   }

   
   /** 
   Print the list of keywords. Use the implemented iterator to traverse the tree in-order
   */
   private void printKeywords() 
   { 
	Word word;
   Iterator it = keywordsByFreqDesc.iterator();
   while (it.hasNext())
   {
       word = (Word) it.next();
       System.out.println(word);
   }
   }

   /** 
   Print the list of longwords. Use the implemented iterator to traverse the tree in order
   */
   private void printLongwords() 
   {
	   Word word;
       Iterator it = longwordsByLengthDesc.iterator();
       while (it.hasNext())
	   {
	       word = (Word) it.next();
	       System.out.println(word);
	   }
   }

   private void printResults() 
   {
       System.out.println( "Number of Unique Words: " + words.size()); 
       System.out.println( "Number of Keywords: " + keywordsByFreqDesc.size());
       printKeywords();
       System.out.println( "Number of Longwords: " + longwordsByLengthDesc.size());
       printLongwords();
       
       System.out.println("The average keyword frequency is " + avgFreq());
       
       System.out.println("The average longword length is " + avgLength());
       
       System.out.println("Height of the unique words tree is : " + words.height()
    		   			+ " (Optimal height for this tree is : " + optHeight(words.size()) + ")");
       System.out.println("Height of the keywords tree is : " + keywordsByFreqDesc.height()
						+ " (Optimal height for this tree is : " + optHeight(keywordsByFreqDesc.size()) + ")");
       System.out.println("Height of the longwords tree is : " + longwordsByLengthDesc.height()
       					+ " (Optimal height for this tree is : " + optHeight(longwordsByLengthDesc.size()) + ")");
   }
   
   
   /* Read the file and add words to the tree. 
    */
   private void readFile() 
   {
      while (input.hasNext()) 
      {
         // get the next word, convert to lower case, strip out blanks and non 
         // alphabetic characters.
         String word = input.next().toLowerCase().trim().replaceAll("[^a-z]","");

         if (word.length() > 0) 
         {         	 
        	 Word newWord = new Word(word);
    	     if (words.contains(newWord))
    		     {
    			 words.find(newWord).incrCount();
    			 
    		     }
    	     else
    		     {
    			 newWord.incrCount();
    			 words.add(newWord);
    		     }
         }
      }
   }
   
   /* Create the keywords and longwords trees. */
   private void createSortedTrees()
   {
      for (Word w: words)
      {
          if (w.getCount() >= 2 && w.getWord().length() > 3) {
        	  averageFreqKeywords += w.getCount();
         	 keywordsByFreqDesc.add(w);
          }
          
          if (w.getWord().length() >= 8) {
        	  averageLengthLongWords += w.getWord().length();
         	 longwordsByLengthDesc.add(w);
          }
      }    
   }   


   /* Calculate the average frequency of keywords. */
   private int avgFreq()  // worked with soren on coming up with the formula for this 
   {
      double avg = 0;
      if (averageFreqKeywords > 0)
	   {
	       avg = averageFreqKeywords / keywordsByFreqDesc.size();
	   }
      return (int)avg;
   }

   /* Calculate the average length of longwords. */
   private int avgLength() 
   {
      double avg = 0;
      if (averageLengthLongWords > 0)
	   {
	       avg = averageLengthLongWords / longwordsByLengthDesc.size();
	   }
      return (int)avg;
   }

   /* Calculate the optimal height for a tree of size n. 
      Round to an int. 
    */
   private int optHeight(int n) 
   {
	   double h = -1;
	   	if (n > 0){
		      h = Math.ceil(log2(n + 1));
	   	}
	   	return (int) h;
   }

   /* Run the program. */
   private void run() 
   {
      readFile();
      createSortedTrees();
      printResults();
   }
   
   private int log2(int n)  // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/log
   {
	return (int) (Math.log(n) / Math.log(2));
   }
}
