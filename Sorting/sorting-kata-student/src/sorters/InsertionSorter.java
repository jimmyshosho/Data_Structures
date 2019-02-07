package sorters;

import java.util.Comparator;
import structures.SwapList;

public class InsertionSorter<T> extends AbstractSorter<T> 
{

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) 
	{
		super(list, comparator);
	}

	
	@Override
	public SwapList<T> sort() 
	{
		
		for(int i = 1; i<list.size();i++)
		{
			int temp  = i;
			int in= i;
			while( in>0 && list.compare(in-1, temp, comparator) > 0 )
				{
					list.swap(in, in-1);
					in--;
					temp--;
				}
		}
		
		return list;
	}

}
