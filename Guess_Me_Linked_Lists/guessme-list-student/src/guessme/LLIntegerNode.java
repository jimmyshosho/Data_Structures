package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode 
{
	private int info;
	private LLIntegerNode link;
	public LLIntegerNode(int a)
	{
		info = a;
		this.link = null;
	}
	
	public int getInfo()
	{
		return info;
	}
	
	public void setInfo(int a)
	{
		info = a;
	}
	
	public LLIntegerNode getLink()
	{
		return link;
	}
	public void setLink(LLIntegerNode a)
	{
		link = a;
	}
	


}

