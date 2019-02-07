package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> 
{
	private LLNode<T> head;

	public LinkedStack()
	{
		this.head = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException 
	{
		if(this.isEmpty())
			throw new StackUnderflowException("There is no data to pop or remove!");

		else
		{
			LLNode<T> temp = new LLNode<T>(head.getData());
			head = head.getNext();
			return temp.getData();
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) 
	{
		LLNode<T> temp = new LLNode<T>(elem);
		
		if(head == null)
		{
			head = temp;
		}
		
		else 
		{
			temp.setNext(head);
			head = temp;
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException 
	{
		if(this.isEmpty())
			throw new StackUnderflowException("There is no node left in the list!");
		else 
		{
			return head.getData();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() 
	{
		if(head == null)
			return true;

		else return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() 
	{
		int count = 0;
		LLNode<T> temp =  head;
		while(temp != null)
		{
			count++;
			temp = temp.getNext();
		}

		return count;
	}

}
