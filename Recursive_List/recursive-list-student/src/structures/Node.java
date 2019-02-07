package structures;

public class Node<T> 
{
	private Node<T> prev;
	private Node<T> next;
	private T data;
	
	public Node (T data, Node<T> next, Node<T> prev)
	{
		this.data = data;
		this.prev = prev;
		this.next = next;	
	}
	
	public Node<T> getPrev()
	{
		return prev;
	}
	
	public  Node<T> getNext()
	{
		return next;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void setPrev(Node<T> newPrev)
	{
		this.prev = newPrev;
	}
	
	public void setNext(Node<T> newLink)
	{
		this.next = newLink;
	}
	
	public void setData(T newData)
	{
		this.data = newData;
	}
	
}
