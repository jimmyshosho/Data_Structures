package structures;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> 
{
	protected BSTNode<T> root;

	public boolean isEmpty() 
	{
		return root == null;
	}

	public int size() 
	{
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) 
	{
		if (node == null) 
		{
			return 0;
		} 
		else 
		{
			return 1 + subtreeSize(node.getLeft())
			+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) 
	{
		if(t == null)
			throw new NullPointerException();

		if(get(t) == null)
			return false;

		return true;
	}

	public boolean remove(T t) 
	{
		if (t == null) 
			throw new NullPointerException();

		boolean result = contains(t);

		if (result) 
			root = removeFromSubtree(root, t);

		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) 
	{
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) 
		{
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} 

		else if (result > 0) 
		{
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} 
		else { // result == 0
			if (node.getLeft() == null) 
			{
				return node.getRight();
			} 
			else if (node.getRight() == null) 
			{
				return node.getLeft();
			} 
			else 
			{ // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) 
	{
		// node must not be null
		if (node.getRight() == null) 
		{
			return node.getData();
		}
		else 
		{
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node)
	{
		// node must not be null
		if (node.getRight() == null) 
		{
			return node.getLeft();
		} 
		else 
		{
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) 
	{
		if(t == null)
			throw new NullPointerException();

		BSTNode<T> curr = root;
	
		while(curr !=null)
		{
			if(curr.getData().compareTo(t) == 0)
				return curr.getData();
			else if(curr != null && curr.getData().compareTo(t) > 0)
				curr = curr.getLeft();
			else if(curr != null && curr.getData().compareTo(t) < 0)
				curr = curr.getRight();
		}
		return null;

	}


	public void add(T t) 
	{
		if (t == null) 
			throw new NullPointerException();

		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) 
	{
		if (node == null) 
			return toAdd;

		int result = toAdd.getData().compareTo(node.getData());

		if (result <= 0) 
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		else 
			node.setRight(addToSubtree(node.getRight(), toAdd));

		return node;
	}

	@Override
	public T getMinimum() 
	{
		BSTNode<T> curr = root;
		if(curr == null)
			return null;

		T minimum = null;

		while(curr != null)
		{
			if(curr.getLeft() == null)
				minimum = curr.getData();
			curr = curr.getLeft();
		}

		return minimum;
	}


	@Override
	public T getMaximum() 
	{
		BSTNode<T> curr = root;
		if(curr == null)
			return null;
		T max = null;

		while(curr != null)
		{
			if(curr.getRight() == null)
				max = curr.getData();
			curr = curr.getRight();
		}

		return max;
	}


	@Override
	public int height() 
	{
		if(this.root == null)
			return -1;

		BSTNode<T> curr = root;

		return heightRecursive(curr) - 1;
	}

	private int heightRecursive(BSTNode<T> curr)
	{
		if (curr == null)
			return 0;
		else
		{
			int leftSize = heightRecursive(curr.getLeft());
			int rightSize= heightRecursive(curr.getRight());

			if(leftSize >= rightSize)
				return leftSize + 1;
			else
				return rightSize + 1;
		}
	}



	public Iterator<T> preorderIterator() 
	{
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node)
	{
		if(node !=null)
		{
			queue.add(node.getData());
			preorderTraverse(queue,node.getLeft());
			preorderTraverse(queue,node.getRight());

		}

	}

	public Iterator<T> inorderIterator() 
	{
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node)
	{
		if (node != null) 
		{
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() 
	{
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue,root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node)
	{
		if(node !=null)
		{
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}

	}


	@Override
	public boolean equals(BSTInterface<T> other) 
	{
		if(other == null)
			throw new NullPointerException();

		if(this.size() != other.size())
			return false;
		if(this.height() != other.height())
			return false;

		return equalsCheck(root,other.getRoot());
	}



	private boolean equalsCheck(BSTNode<T> thisCurr, BSTNode<T>thatCurr)
	{

		if( thisCurr == null && thatCurr == null)
			return true;
		if(thisCurr.getData().compareTo(thatCurr.getData()) != 0 )
			return false;
		
		return equalsCheck(thisCurr.getLeft(), thatCurr.getLeft()) && equalsCheck(thisCurr.getRight(),thatCurr.getRight());

	}


	@Override
	public boolean sameValues(BSTInterface<T> other) 
	{
		if(other == null)
			throw new NullPointerException();
		
		if(this.size() != other.size())
			return false;
		
		Iterator<T> iterateThis = this.inorderIterator();
		Iterator<T> iterateOther = other.inorderIterator();
		Queue<T> thisList = new LinkedList<T>();
		Queue<T> otherList = new LinkedList<T>();
		
		while(iterateThis.hasNext() || iterateOther.hasNext())
		{
			thisList.add(iterateThis.next());
			otherList.add(iterateOther.next());
			
			if(thisList.remove().compareTo(otherList.remove()) != 0)
				return false;
		}
		
		
		return true;
	}

	@Override
	public boolean isBalanced() 
	{
		if(root == null)
			return true;
		
		int n = this.size();
		int h = this.height();
		// balanced if 2^h <= n < 2^(h+1)		
		int total = 1;
		
		for(int i = 0; i< h; i++)
			total = total * 2;
		
		if((total <= n) && (n < total*2))
			return true;
		
		return false;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public void balance() 
	{
		Iterator<T> iterate = this.inorderIterator();
		T[] list = (T[]) new Comparable[this.size()];
		
		int i = 0;
		while(iterate.hasNext())
		{
			list[i] = iterate.next();
			i++;
		}
		
		root = arrayToTree(list,0,list.length-1);
	
	}
	
	private BSTNode<T> arrayToTree(T[] list, int lower, int upper)
	{
		if(lower > upper)
			return null;
		int mid = (lower + upper)/2;
		BSTNode<T> currNode = new BSTNode<T>(list[mid],null,null);
		currNode.setLeft(arrayToTree(list, lower, mid-1));
		currNode.setRight(arrayToTree(list,mid+1, upper));
		return currNode;
		
	}

	@Override
	public BSTNode<T> getRoot() 
	{
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) 
	{
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;

		while (!queue.isEmpty()) 
		{
			cursor = queue.remove();
			if (cursor.getLeft() != null) 
			{
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} 
			else 
			{
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) 
			{
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} 
			else 
			{
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) 
	{
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) 
		{
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) 
			{
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) 
			{
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) 
			{
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) 
			{
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext())
			{
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) 
		{
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}