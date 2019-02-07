package structures;
import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> 
{
	RecursiveList<T> list;
	Node<T> Head;
	
	public ListIterator(RecursiveList<T> list)
	{
		this.list = list;
		Head = this.list.getFirstNode();
	}

	@Override
	public boolean hasNext() 
	{
		if(Head != null)
			return true;
		
		return false;
	}

	@Override
	public T next() 
	{
		T element = Head.getData();
		Head = Head.getNext();
		
		return element;
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
	

}
