import java.util.Comparator;

/** 
 * COMP 2503 Assignment 1
 * Fall 2019
 *
 * Last updated by @author Maryam Elahi
 * 
 * A Word object represents a word (string) and how many times it 
 * has occurred in a given text. 
*/

public class Word implements Comparable<Word> {

   // The word!
   private String word;
   // How many times it has occured
   private int count; 
   
   /** Constructor. Set the string and initialize the count to 0.
    *   @param w the string representing this word.
   */
   public Word(String w) 
   {
      this.word = w;
      this.count = 0;
   }

   /* Comparator to sort from high to low frequency (count). In case of ties, alphabetically.
    */
   public static Comparator<Word> CompFreqDesc = new Comparator<Word>() 
   {
      public int compare( Word w1, Word w2) 
      {
         int f1 = w1.getCount();
         int f2 = w2.getCount();
         if (f2-f1 == 0) 
        	 return w1.compareTo(w2);
         else
        	 return f2 - f1; 
      }
   };

   /* Comparator to sort from in the following order:
    * longest to shortest, from high frequency to low, alphabetically. 
    */
   public static Comparator<Word> CompLengthDesc = new Comparator<Word>() 
   {
      public int compare(Word w1, Word w2) 
      {
    	 int l1 = w1.getWord().length();
    	 int l2 = w2.getWord().length();
         int f1 = w1.getCount();
         int f2 = w2.getCount();
         if (l2 - l1 == 0) {
	         if (f2 - f1 == 0) 
	            return w1.compareTo(w2);
	         else
	            return f2 - f1; 
         }
         else
        	 return l2 - l1;
      }
   };

   public String getWord() 
   { 
      return word;
   }

   public int getCount() 
   { 
      return count;
   }

   public void incrCount() 
   { 
      count++; 
   }

   public String toString() 
   { 
      return getWord() + " : " + getCount();
   }

   /** A word is equal to another word if the 
    *  string word is equal. 
    */
   public boolean equals(Object other) 
   {
      if (other == this) return true;
      if (other == null) return false;
      if (this.getClass() != other.getClass()) return false;
      Word p = (Word)other;
      return this.getWord().equals(p.getWord());
   }
  
   /** Compare two words. This will order 
    *  Words alphabetically. 
    */
   public int compareTo(Word o) 
   {
      if (this.equals(o))
         return 0;
      else
         return this.getWord().compareTo(o.getWord()); 
   }
}
