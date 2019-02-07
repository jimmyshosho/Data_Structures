package structures;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest 
{

	private ListInterface<String> list;

	@Before
	public void setup()
	{
		list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() 
	{
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}

	@Test (timeout = 500)
	public void testInsertLastRemoveLastRemoveFirstAndGetLast() 
	{
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert Last should return instance of self", list, list.insertLast("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("Last element should .equals \"hello\".", "hello", list.getLast());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertLast("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"world\".", "world", list.getFirst());
		assertEquals("Last element should .equals \"foo\".", "foo", list.getLast());

		assertEquals("Element returned should be foo", "foo", list.removeLast());
		assertEquals("Last element should .equals \"hello\"." , "hello" , list.getLast());
		assertEquals("Element returned should be world", "world", list.removeFirst());

		assertFalse("List should not be empty", list.isEmpty());

	}

	@Test (timeout = 500)
	public void testIndexOf() throws NullPointerException
	{

		try
		{
			list.indexOf(null);
		}
		catch(NullPointerException error )
		{
			assertNull("elem is null", null);
		}
		
		for(int i  = 0; i < 5;i++)
			list.insertLast(i + "");

		assertEquals("index of 0 should be 0", 0, list.indexOf("0"));
		assertEquals("index of 4 should be 4", 4, list.indexOf("4"));
		assertEquals("index of 10 should be -1  since isnt to be found", -1, list.indexOf("10"));

		assertEquals("size should be 5", 5, list.size());

		assertEquals("index of 2 should be 2", 2, list.indexOf("2"));
		list.removeFirst();
		assertEquals("index of 2 should be 1", 1, list.indexOf("2"));
		list.removeFirst();
		assertEquals("index of 2 should be 0", 0, list.indexOf("2"));
		list.removeFirst();

		assertEquals("Should return -1 since 2 isnt to be found", -1, list.indexOf("2"));

		assertEquals("Size should be 2",2,list.size());
	}
	
	@Test (timeout = 500)
	public void testRemove() throws NullPointerException
	{
		try
		{
			list.remove(null);
		}
		catch(NullPointerException error )
		{
			assertNull("elem is null", null);
		}
		
		for(int i  = 0; i < 3;i++)
			list.insertLast(i + "");
		
		assertEquals("2 will be removed", true, list.remove("2"));
		assertEquals("size should now be 2 [0,1]", 2, list.size());
		
		assertEquals("0 will be removed", true, list.remove("0"));
		assertEquals("size should now be 1 [1] ", 1, list.size());
		
		assertEquals("list wont change if you remove 5", false, list.remove("5"));
		
		list.insertFirst("0");
		list.insertLast("2");
		assertEquals("first element should be 0","0", list.getFirst());
		assertEquals("last element should be 2","2", list.getLast());
		assertEquals("list should be size 3", 3, list.size());
		
	}
	
	@Test (timeout = 500)
	public void testGet() throws IndexOutOfBoundsException
	{
		
		for(int i  = 0; i < 3;i++)
			list.insertLast(i + "");
		
		assertEquals("gets first element", "0", list.get(0));
		assertEquals("gets second element", "1", list.get(1));
		assertEquals("gets third element", "2", list.get(2));		
		
		list.remove("1"); // list is now [0,2]
		assertEquals("gets 2", "2", list.get(1));
		assertEquals("size is 2", 2, list.size());
		assertEquals("gets 0", "0", list.get(0));
		
	}
	
	
	@Test (timeout = 500)
	public void testRemoveAt() throws IndexOutOfBoundsException
	{
		
		for(int i  = 0; i < 3;i++)
			list.insertLast(i + "");
		
		assertEquals("removes first element", "0", list.removeAt(0));
		assertEquals("first node should be 1", "1", list.get(0));
		assertEquals("size should be 2", 2, list.size());
		
		
	}
	
	@Test (timeout = 500)
	public void testInsertAt() 
	{
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("insertAt should return instance of self", list, list.insertAt(0,"hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertAt(1,"world"); // ["hello","world"]
		assertEquals(2, list.size());
		assertEquals("Last element should be world","world",list.get(1));
		list.insertFirst("foo"); // ["foo","hello","world"]
		assertEquals(3, list.size());
		assertEquals("First element should be foo", "foo", list.get(0));
		assertEquals("Second element should be  hello", "hello",list.get(1));
		assertEquals("Third element should be world", "world", list.get(2));
		
		list.insertAt(1, "dog");
		assertEquals("First element should be foo", "foo", list.get(0));
		assertEquals("Second element should be  dog", "dog",list.get(1));
		assertEquals("Third element should be hello", "hello", list.get(2));
		
	}
	
	
	
}
