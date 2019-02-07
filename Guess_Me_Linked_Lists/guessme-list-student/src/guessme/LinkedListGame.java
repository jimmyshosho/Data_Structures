package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame 
{
	LLIntegerNode candidateList;
	LLIntegerNode priorGuesses;
	LLIntegerNode headOfPriorGuesses;
	private int guess;
	private int rounds;
	private int magicNumber;

	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 

	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/

	// LinkedListGame constructor method
	public LinkedListGame() 
	{
		candidateList = null;
		priorGuesses = null;
		headOfPriorGuesses = null;
		magicNumber = 0;
		rounds = 0;
		guess = 0;
		resetCandidates();

	}

	// Resets data members and game state so we can play again
	public void reset() 
	{
		candidateList = null;
		priorGuesses = null;
		headOfPriorGuesses = null;
		magicNumber = 0;
		rounds = 0;
		guess = 0;
		resetCandidates();
	}

	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) 
	{
		LLIntegerNode tempPriorGuesses = headOfPriorGuesses;

		while(tempPriorGuesses !=null)
		{
			if(tempPriorGuesses.getInfo() == n)
				return true;

			tempPriorGuesses = tempPriorGuesses.getLink();
		}
		return false;

	}

	// Returns the number of guesses so far.
	public int numGuesses() 
	{
		return rounds;
	}

	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) 
	{
		int numMatches = 0;
		for(int i = 1; i<=4; i++)
		{
			if(a%10 ==  b%10)
				numMatches ++;

			a = a / 10;
			b = b / 10;
		}
		return numMatches;
	}

	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() 
	{
		if(magicNumber == 4)
			return true;

		return false;
	}

	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() 
	{

		rounds ++;
		guess = candidateList.getInfo();

		LLIntegerNode newNode = new LLIntegerNode(guess);
		if(priorGuesses == null)
		{
			priorGuesses = newNode;
			headOfPriorGuesses = priorGuesses; // makes headOfPriorGuesses point to the start of the priorGuesses Node List
		}

		else 
		{
			priorGuesses.setLink(newNode);
			priorGuesses = priorGuesses.getLink();
		}
		return guess;
	}// end of getGuess() method

	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) 
	{
		magicNumber = nmatches;
		LLIntegerNode newList = null;
		LLIntegerNode node = candidateList;
		LLIntegerNode tail = null;
		LLIntegerNode tail2 = null;

		while( node != null )
		{
			if( numMatches(guess,node.getInfo()) == nmatches)
			{
				if(newList == null)
				{
					newList = new LLIntegerNode(node.getInfo());
					tail = newList;
				}

				else 
				{
					tail2 = new LLIntegerNode(node.getInfo());
					tail.setLink(tail2);
					tail = tail2;
				}

			}
			node = node.getLink();
		}
		candidateList = newList;

		if(candidateList == null)
			return false;

		return true;
	} // end of updateGuess() method

	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() 
	{
		return headOfPriorGuesses;
	}

	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() 
	{
		String guesses = "";

		LLIntegerNode tempPriorGuesses = headOfPriorGuesses; // we do not want to alter the head so we will make temp variable

		while(tempPriorGuesses !=null)
		{
			if(tempPriorGuesses.getLink()!= null)
				guesses = guesses + tempPriorGuesses.getInfo() + ", ";
			else
				guesses = guesses + tempPriorGuesses.getInfo();

			tempPriorGuesses = tempPriorGuesses.getLink();
		}

		return guesses;
	}// end of priorGuessesString() method

	public void resetCandidates()
	{
		LLIntegerNode head = null;

		for(int i = 1000; i<=9999;i++)
		{
			LLIntegerNode newNode = new LLIntegerNode(i);

			if(candidateList == null)
			{
				candidateList = new LLIntegerNode(1000);
				head = candidateList;
			}
			else
			{
				candidateList.setLink(newNode);
				candidateList = candidateList.getLink();
			}

		}	
		candidateList = head;

	}// end of resetCandidates() method

}// end of class
