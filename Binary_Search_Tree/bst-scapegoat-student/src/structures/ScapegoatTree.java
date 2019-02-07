package structures;

import java.util.Iterator;
import java.util.LinkedList;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> 
{
	private int upperBound = 0;


	@Override
	public void add(T t) 
	{
		upperBound++;
		super.add(t);
		BSTNode<T> nodeU = new BSTNode<T>(t,null,null);
		
		if( (double)this.height() > (Math.log(upperBound) / Math.log(1.5)) ) 
		{
			BSTNode<T> scapeGoat = findScapeGoat(this.getRoot(), nodeU);

		 	BSTNode<T> curr = this.getRoot();
		 	BSTNode<T> currParent = this.getRoot();

			while(curr.getData().compareTo(scapeGoat.getData()) != 0)
			{
				if( curr.getData().compareTo(scapeGoat.getData()) > 0)	
				{	
					currParent = curr;
					curr = curr.getLeft();
				}
				else
					currParent = curr;
					curr = curr.getRight();
			}// get us to scapeGoat
				
			BinarySearchTree<T> balanceCurr = new BinarySearchTree<T>();
			balanceCurr.root = curr;
			balanceCurr.balance(); // makes new tree and balances it
			
			if(currParent.getData().compareTo(balanceCurr.root.getData()) > 0)
				currParent.setLeft(balanceCurr.root);
			if(currParent.getData().compareTo(balanceCurr.root.getData()) < 0)
				currParent.setRight(balanceCurr.root);
			
		}

	}

	private BSTNode<T> findScapeGoat(BSTNode<T> head,BSTNode<T> NodeU)
	{

		BSTNode<T> temp = head;
		LinkedList<BSTNode<T>> list = new LinkedList<BSTNode<T>>();
		BSTNode<T> scapeGoat = null;

		while(temp !=null && temp.getData().compareTo(NodeU.getData()) != 0 )
		{
			if( temp.getData().compareTo(NodeU.getData()) > 0)
			{
				list.add(temp);
				temp = temp.getLeft();
			}
			else if(temp.getData().compareTo(NodeU.getData()) < 0)
			{
				list.add(temp);
				temp = temp.getRight();
			}

		} // method finds and stores nodes from the root to the newly added node U.
		
		for(int i = list.size() - 1; i > 0; i--)
		{
			if( (double) (list.size() - i) / (double)(list.size() - (i - 1)) > (2.0/3.0) )
				scapeGoat = list.get(i);
		}

		return scapeGoat;

	}


	@Override
	public boolean remove(T element) 
	{
		boolean check = super.remove(element);

		if(upperBound > size()*2)
		{
			this.balance();
			upperBound = size();
		}

		return check;
	}
}
