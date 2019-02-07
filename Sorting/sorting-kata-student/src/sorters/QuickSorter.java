package sorters;

import java.util.Comparator;
import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> 
{

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) 
	{
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() 
	{
		recursiveQuickSort(0 , list.size()-1);
		return list;
	}
	
	private int partition(int low, int high)
	{
		int pivot = (high + low) / 2;
		int s = low;
		
		list.swap(pivot, high);
		pivot = high;
		
		for(int j = low; j<=high-1; j++)
		{	
			if(list.compare(j, pivot, comparator) <= 0 )
			{
				list.swap(j, s);
				s++;
			}	
		}
		
		list.swap(pivot, s);
		return s;
		
	}
	
	private void recursiveQuickSort(int low, int high)
	{
		if(low < high)
		{
			int p = partition(low,high);
			recursiveQuickSort(low,p-1);
			recursiveQuickSort(p+1,high);	
		}
	
	}

}
