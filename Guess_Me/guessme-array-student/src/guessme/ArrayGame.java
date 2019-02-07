package guessme;

public class ArrayGame {

	// stores the next number to guess
	private int guess;
	private int numMatches;
	private boolean[] guessesLeft;
	private int[] priorGuesses;

	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.

	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/

	// ArrayGame constructor method
	public ArrayGame() 
	{
		guessesLeft = new boolean [10000];
		priorGuesses = new int[0];
		guess = 1000;
		numMatches = 0;

	}

	// Resets data members and game state so we can play again
	public void reset() 
	{
		guessesLeft = new boolean [10000];
		priorGuesses = new int[0];
		guess = 1000;
		numMatches = 0;
	}

	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) 
	{
		for(int i = 0; i<priorGuesses.length;i++)
		{
			if(priorGuesses[i] == n)
				return true;
		}

		return false;

	}

	// Returns the number of guesses so far.
	public int numGuesses() 
	{
		return priorGuesses.length;
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
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier

		int count = 0;
		for(int i = 1; i<=4; i++)
		{
			if(a%10 ==  b%10)
			{
				count ++;
			}
			a = a / 10;
			b = b / 10;
		}
		return count;

	}

	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() 
	{
		if(numMatches == 4)
		{
			return true;
		}

		return false;
	}

	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() 
	{
		for(int i = 1000; i<guessesLeft.length;i++)
		{
			if(guessesLeft[i] == false)// false means its a possible number
			{
				guess = i;
				break;
			}
		}

		int[] temp = new int[priorGuesses.length + 1];
		for(int i = 0; i<priorGuesses.length;i++)
		{
			temp[i] = priorGuesses[i];	
		}
		priorGuesses = temp;
		priorGuesses[priorGuesses.length-1] = guess;

		return guess;
	}

	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) 
	{	
		numMatches = nmatches;

		for(int i = 1000; i<guessesLeft.length;i++)
		{
			if(guessesLeft[i] == false) 
			{
				if (numMatches(guess, i) != nmatches)
				{
					guessesLeft[i] = true; // not possible number anymore	
				}
			}
		}

		int count = 1000;
		for(int i = 1000; i<guessesLeft.length;i++)
		{
			if(guessesLeft[i])
			{
				count++; // loops through and increases count, if count is equal to all guesses possible, then there is an error so we return false
			}
		}

		if(count == guessesLeft.length)
			return false;

		return true;
	}


	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() 
	{
		if(priorGuesses.length == 0)
			return null;

		return priorGuesses;

	}
}
