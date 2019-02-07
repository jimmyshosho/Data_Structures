package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */
/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> 
{
	private LinkedNode<E> head;

	// Constructors
	public LinkedSet() 
	{
		head = null;
	}

	public LinkedSet(E e) 
	{
		this.head = new LinkedNode<E>(e, null);

	}

	private LinkedSet(LinkedNode<E> head) 
	{
		this.head = head;
	}

	@Override
	public int size() 
	{
		int count  = 0;

		for(E a: this)
			count++;
		
		return count;
	}

	@Override
	public boolean isEmpty() 
	{
		return (head == null);
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new LinkedNodeIterator<E>(this.head);
	}

	@Override
	public boolean contains(Object o) 
	{
		for(E a: this)
		{
			if(a.equals(o))
				return true;
		}

		return false;
	}

	@Override
	public boolean isSubset(Set<E> that) 
	{
		for(E a: this)
		{
			if( !that.contains(a))
				return false;
		}
		return true;
	}

	@Override
	public boolean isSuperset(Set<E> that) 
	{
		return that.isSubset(this);
	}

	@Override
	public Set<E> adjoin(E e) 
	{
		if(this.contains(e))
		{
			return this;
		}

		LinkedNode<E> tempNode = new LinkedNode<E>(e,head);
		LinkedSet<E> set = new LinkedSet<E>(tempNode);	

		return set;

	}

	@Override
	public Set<E> union(Set<E> that) 
	{
		Set<E> set = new LinkedSet<E>();

		for(E a : that)
		{
			set = set.adjoin(a);
		}

		for(E a : this)
		{
			set = set.adjoin(a);
		}


		return set;
	}

	@Override
	public Set<E> intersect(Set<E> that) 
	{
		Set<E> set = new LinkedSet<E>();

		for(E a: this)
		{
			if(that.contains(a))
			{
				set = set.adjoin(a);
			}
		}

		return set;
	}

	@Override
	public Set<E> subtract(Set<E> that) 
	{
		Set<E> set = new LinkedSet<E>();
		
		for(E a: this)
		{
			if(!that.contains(a))
				set = set.adjoin(a);
		}
		
		return set;
	}

	@Override
	public Set<E> remove(E e) 
	{
		Set<E> set = new LinkedSet<E>();
		
		for(E a: this)
		{
			if(!a.equals(e))
				set = set.adjoin(a);
		}
		
		return set;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) 
	{
		if (! (o instanceof Set)) {
			return false;
		}
		Set<E> that = (Set<E>)o;
		return this.isSubset(that) && that.isSubset(this);
	}

	@Override
	public int hashCode() 
	{
		int result = 0;
		for (E e : this) {
			result += e.hashCode();
		}
		return result;
	}
}
