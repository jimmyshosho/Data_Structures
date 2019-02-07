package structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

public class PublicBinarySearchTreeTest
{

	private BSTInterface<Integer> emptyTree;
	private BSTInterface<String> oneNodeTree;
	private static final String FOO = "foo";

	@Rule
    public Timeout timeout = new Timeout(1L, TimeUnit.SECONDS);
	
	@Before
	public void before()
	{
		emptyTree = new BinarySearchTree<Integer>();
		oneNodeTree = new BinarySearchTree<String>();
		oneNodeTree.add(FOO);
	}
	
	@Test
	public void testEmpty() 
	{
		assertTrue(emptyTree.isEmpty());
	}

	@Test
	public void testNotEmpty() 
	{
		assertFalse(oneNodeTree.isEmpty());
	}

	@Test
	public void testSize() 
	{
		assertEquals(0, emptyTree.size());
		assertEquals(1, oneNodeTree.size());
	}
	
	@Test
	public void testContains()
	{
		assertTrue(oneNodeTree.contains(FOO));
	}
	
	@Test
	public void testContainsFurther()
	{
		assertTrue(oneNodeTree.contains(FOO));
		oneNodeTree.remove(FOO);
		assertFalse(oneNodeTree.contains(FOO));
		
		oneNodeTree.add("Foo");
		oneNodeTree.add("Boo");
		oneNodeTree.add("Clue");
		
		assertTrue(oneNodeTree.contains("Boo"));
		assertFalse(oneNodeTree.contains("Zoo"));
		oneNodeTree.add("Zoo");
		assertTrue(oneNodeTree.contains("Zoo"));
	}
	
	@Test
    public void testRemove()
	{
		assertFalse(emptyTree.remove(0));
	}
	
	@Test
	public void testGet() 
	{
		assertEquals(FOO, oneNodeTree.get(FOO));
	}
	

	@Test
	public void testGetFurther() 
	{
		assertEquals(FOO, oneNodeTree.get(FOO));
		oneNodeTree.remove(FOO);
		assertEquals(null, oneNodeTree.get(FOO));
		
		oneNodeTree.add("Foo");
		oneNodeTree.add("Bloo");
		oneNodeTree.add("Clue");
		oneNodeTree.add("Zoo");
		oneNodeTree.add("Moo");
		oneNodeTree.add("Poo");
		
		assertEquals("Foo", oneNodeTree.get("Foo"));
		assertEquals("Clue", oneNodeTree.get("Clue"));
		assertEquals("Poo", oneNodeTree.get("Poo"));
		
		
		
		assertEquals(null, oneNodeTree.get("Noo"));
		oneNodeTree.remove("Zoo");
		assertEquals(null, oneNodeTree.get("Zoo"));
		
		
	}
	
	@Test
	public void testAdd()
	{
		emptyTree.add(1);
		assertFalse(emptyTree.isEmpty());
		assertEquals(1, emptyTree.size());
	}
	
	@Test
	public void testGetMinimum()
	{
		assertEquals(null, emptyTree.getMinimum());
	}
	
	@Test
	public void testGetMinimumFurther()
	{
		assertEquals(null, emptyTree.getMinimum());
		assertTrue(oneNodeTree.contains(FOO));
		oneNodeTree.remove(FOO);
		assertFalse(oneNodeTree.contains(FOO));
		
		oneNodeTree.add("Foo");
		oneNodeTree.add("Bloo");
		oneNodeTree.add("Clue");
		oneNodeTree.add("Zoo");
		oneNodeTree.add("Moo");
		oneNodeTree.add("Poo");
		
		assertEquals("Bloo", oneNodeTree.getMinimum());
	}

	@Test
	public void testGetMaximum() 
	{
		assertEquals(FOO, oneNodeTree.getMaximum());
	}
	
	@Test
	public void testGetMaximumFurther() 
	{
		assertEquals(FOO, oneNodeTree.getMaximum());
		assertTrue(oneNodeTree.contains(FOO));
		oneNodeTree.remove(FOO);
		assertFalse(oneNodeTree.contains(FOO));
		
		oneNodeTree.add("Foo");
		oneNodeTree.add("Bloo");
		oneNodeTree.add("Clue");
		oneNodeTree.add("Zoo");
		oneNodeTree.add("Moo");
		oneNodeTree.add("Poo");
		
		assertEquals("Zoo", oneNodeTree.getMaximum());
	}

	@Test
	public void testHeight() 
	{
		assertEquals(-1, emptyTree.height());
		assertEquals(0, oneNodeTree.height());
	}
	
	@Test
	public void testHeightFurther() 
	{
		oneNodeTree.remove(FOO);
		oneNodeTree.add("5");
		oneNodeTree.add("1");
		oneNodeTree.add("3");
		oneNodeTree.add("2");
		oneNodeTree.add("6");
		oneNodeTree.add("4");
		
		assertEquals(3, oneNodeTree.height());

	}
	
	
	@Test
	public void testPreorderIterator() // always works
	{
		Iterator<String> i = oneNodeTree.preorderIterator();
		while (i.hasNext()) 
		{
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testInorderIterator() // always works
	{
		Iterator<String> i = oneNodeTree.inorderIterator();
		while (i.hasNext()) {
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testPostorderIterator() // always works
	{
		Iterator<Integer> i = emptyTree.postorderIterator();
		assertFalse(i.hasNext());
	}
	
	
	@Test
	public void testEquals() 
	{
		BSTInterface<String> tree = new BinarySearchTree<String>();
		assertFalse(oneNodeTree.equals(tree));
		tree.add(new String("foo"));
		assertTrue(oneNodeTree.equals(tree));
	}
	
	@Test
	public void testEqualsFurther() 
	{
		BSTInterface<String> tree1 = new BinarySearchTree<String>();
		BSTInterface<String> tree2 = new BinarySearchTree<String>();
		BSTInterface<String> tree3 = new BinarySearchTree<String>();
		
		tree1.add("1");
		tree1.add("2");
		tree1.add("3");
		tree1.add("4");
		tree1.add("5");
		
		tree2.add("1");
		tree2.add("2");
		tree2.add("3");
		tree2.add("4");
		tree2.add("5");
		
		tree3.add("3");
		tree3.add("4");
		tree3.add("1");
		tree3.add("2");
		tree3.add("5");
		
		assertTrue(tree1.equals(tree2));
		tree2.add("6");
		assertFalse(tree1.equals(tree2));
		assertFalse(tree1.equals(tree3));
		
	}
	
	@Test
	public void testSameValues() 
	{
		BSTInterface<Integer> tree = new BinarySearchTree<Integer>();
		assertTrue(emptyTree.sameValues(tree));
		
		emptyTree.add(1);
		emptyTree.add(2);
		
		tree.add(2);
		tree.add(1);
		
		assertTrue(emptyTree.sameValues(tree));
	}
	
	@Test
	public void testSameValuesFurther() 
	{
		BSTInterface<Integer> tree1 = new BinarySearchTree<Integer>();
		BSTInterface<Integer> tree2 = new BinarySearchTree<Integer>();
		BSTInterface<Integer> tree3 = new BinarySearchTree<Integer>();
		
		tree1.add(1);
		tree1.add(2);
		tree1.add(3);
		tree1.add(4);
		tree1.add(5);
		
		tree2.add(1);
		tree2.add(2);
		tree2.add(3);
		tree2.add(4);
		tree2.add(5);
		
		tree3.add(3);
		tree3.add(4);
		tree3.add(1);
		tree3.add(5);
		tree3.add(2);
		
		assertTrue(tree1.sameValues(tree2));
		
		tree2.remove(3);
		assertFalse(tree1.sameValues(tree2));
		
		assertTrue(tree1.sameValues(tree3));
	}
	
	
	
	
	@Test 
	public void testIsBalanced() 
	{
		// disabled due to late change of isBalanced() specification
		// assertTrue(emptyTree.isBalanced());
		emptyTree.add(1);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(2);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
	}
	
	@Test 
	public void testIsBalancedFurther() // if this works then balance() works since this relies on balance to work in order to pass ;)
	{
		BSTInterface<Integer> tree1 = new BinarySearchTree<Integer>();
		
		tree1.add(1);
		tree1.add(2);
		tree1.add(3);
		tree1.add(4);
		tree1.add(5);
		tree1.add(6);
		tree1.add(7);
		tree1.add(8);
		tree1.add(9);
		tree1.add(10);
		tree1.add(11);
		tree1.add(12);
		tree1.add(13);
		tree1.add(14);
		tree1.add(15);
		
		assertFalse(tree1.isBalanced());
		tree1.balance();
		assertTrue(tree1.isBalanced());
	}
	@Test 
	public void testBalance() 
	{
		emptyTree.add(1);
		emptyTree.add(2);
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
		emptyTree.balance();
		assertTrue(emptyTree.isBalanced());
	
	}
}
