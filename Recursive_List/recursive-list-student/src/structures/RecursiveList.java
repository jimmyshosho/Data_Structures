package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> 
{
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private int index;

	public RecursiveList ()
	{
		this.head = null;
		this.tail = null;
		size = 0;
		index = 0;
	}

	@Override
	public Iterator<T> iterator() 
	{
		ListIterator<T> iterate = new ListIterator<T>(this);
		return iterate;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) throws NullPointerException
	{
		if(elem == null)
			throw new NullPointerException();

		if(head == null)
		{
			head = new Node<T>(elem,null,null);
			tail = head;
			size++;
		}
		else
		{
			head = new Node<T>(elem,head,null);
			head.getNext().setPrev(head);
			size++;
		}

		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) throws NullPointerException
	{
		if(elem == null)
			throw new NullPointerException();

		if(head == null)
		{
			head = new Node<T>(elem,null,null);
			tail = head;
			size++;
		}

		else
		{
			tail.setNext(new Node<T>(elem,null,null));
			tail.getNext().setPrev(tail);
			tail = tail.getNext();
			size++;
		}

		return this;
	}

	@Override
	public ListInterface<T> insertAt(int i, T elem) throws IndexOutOfBoundsException,NullPointerException
	{
		if(elem == null)
			throw new NullPointerException();
		
		if(i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		
		if(isEmpty() || i == 0)
		{
			insertFirst(elem);
			return this;
		}
		if(i == size())
		{
			insertLast(elem);
			return this;
		}
			
		
		index = 0;
		Node<T> Head = this.getFirstNode();
		
		return insertAtRecursive(i,elem,Head);
	}
	
	private ListInterface<T> insertAtRecursive(int i, T elem, Node<T> currNode)// helper method
	{
		
		if(index == i)
		{
			Node<T> temp = new Node<T>(elem, currNode,currNode.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			size++;
		}
		else
		{
			index++;
			insertAtRecursive(i,elem, currNode.getNext());
		}
		
		return this;
	}

	@Override
	public T removeFirst() throws IllegalStateException
	{
		if(isEmpty())
			throw new IllegalStateException();

		Node<T> temp = new Node<T>(head.getData(),null,null);

		if(head.getNext() == null)
		{
			head = null;
			size--;
		}

		else
		{
			head = head.getNext();
			head.setPrev(null);
			size--;
		}

		return temp.getData();
	}

	@Override
	public T removeLast() throws IllegalStateException
	{
		if(isEmpty())
			throw new IllegalStateException();

		Node<T> temp = new Node<T>(tail.getData(),null,null);

		if(head.getNext() == null)
		{
			head = null;
			size --;
		}

		else
		{
			tail = tail.getPrev();
			tail.getNext().setPrev(null);
			tail.setNext(null);
			size--;

		}
		return temp.getData();
	}

	@Override
	public T removeAt(int i) throws IndexOutOfBoundsException
	{
		if(i < 0 || i>=this.size()) 
			throw new IndexOutOfBoundsException();
		
		T element = get(i);
		remove(element);
		
		return element;
	}

	@Override
	public T getFirst() throws IllegalStateException
	{
		if(isEmpty())
			throw new IllegalStateException();

		return head.getData();
	}

	public Node<T> getFirstNode() throws IllegalStateException
	{
		if(isEmpty())
			throw new IllegalStateException();

		return head;

	}

	@Override
	public T getLast() throws IllegalStateException
	{
		if(isEmpty())
			throw new IllegalStateException();

		return tail.getData();
	}

	@Override
	public T get(int i) throws IndexOutOfBoundsException
	{
		if(i < 0 || i>=this.size()) 
			throw new IndexOutOfBoundsException();

		Node<T> Head = this.getFirstNode();
		
			return getElementAt(i,0, Head);
	}
	
	private T getElementAt(int index, int count,Node<T> currNode)// helper method
	{
	
		if(count == index)
			return currNode.getData();
		
		else
			return getElementAt(index,count+1,currNode.getNext());
	
	}

	@Override
	public boolean remove(T elem) throws NullPointerException
	{
		if(elem == null)
			throw new NullPointerException();

		if(isEmpty())
			return false;

		if(head.getData().equals(elem))
		{
			this.removeFirst();
			return true;
		}


		Node<T> Head = this.getFirstNode();
		return removeRecursion(elem, Head);
	}

	private boolean removeRecursion(T element, Node<T> currNode)// helper method
	{
		if(currNode == null ) 
			return false;

		if(currNode.getData().equals(element))
		{
			currNode.getPrev().setNext(currNode.getNext());
			if(currNode.getNext() !=null)
			{
				currNode.getNext().setPrev(currNode.getPrev());
				currNode = null;
				size--;
				return true;	
			}

			else
			{
				currNode = null;
				size--;
				return true;
			}
		}

		return	removeRecursion(element, currNode.getNext());

	}

	@Override
	public int indexOf(T elem) throws NullPointerException
	{
		if(elem == null)
			throw new NullPointerException();
		index = 0;

		Node<T> Head = this.getFirstNode();
		return findIndex(elem, index, Head);
	}

	private int findIndex(T element, int indexNow, Node<T> currNode) // helper method
	{
		if(currNode == null)
			return -1;

		if(currNode.getData().equals(element))
			return indexNow;

		else
		{
			index++;
			return findIndex(element, index, currNode.getNext());
		}

	}

	@Override
	public boolean isEmpty() 
	{
		if(size == 0)
			return true;

		return false;
	}


}
