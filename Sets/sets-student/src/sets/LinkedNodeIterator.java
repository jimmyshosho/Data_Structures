package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> 
{
	LinkedNode<E> head;
	LinkedNode<E> position;
	LinkedNode<E> check;

	// Constructors
	public LinkedNodeIterator(LinkedNode<E> head) 
	{
		this.head = head;
		check = this.head;
		position = null;
	}

	@Override
	public boolean hasNext() 
	{
		if(check != null )
		{
				check = check.getNext();
				return true;	
		}

		return false;
	}

	@Override
	public E next() 
	{
		if(position == null)
		{
			position = head;
			return position.getData();
		}
		
		if(position == null)
			throw new NoSuchElementException();

		position = position.getNext();
		return position.getData();
	}

	@Override
	public void remove() 
	{
		// Nothing to change for this method
		throw new UnsupportedOperationException();
	}
}
