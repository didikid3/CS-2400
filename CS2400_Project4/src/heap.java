import java.util.Arrays;

public class heap<T extends Comparable<T>> {

	private T[] heap;
	private int lastIndex;
	int numberOfSwaps;
	private static final int DEFAULT_CAPACITY = 25;
	
	public heap()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public heap(int size) {
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[size + 1];
		heap = tempHeap;
		lastIndex = 0;
		numberOfSwaps = 0;
	}
	
	//Int method will specify which implementation to use
	//1 will denote sequential
	//2 will denote optimal
	public heap(int method, T[] entries)
	{
		this(entries.length);
		
		if(method == 1)
		{
			for(int i=0;i<entries.length;i++)
			{
				add(entries[i]);
			}
		}
		else if(method == 2)
		{
			for(int i=0;i<entries.length;i++)
			{
				heap[i+1] = entries[i];
				lastIndex++;
			}
			for(int rootIndex=lastIndex/2; rootIndex>0; rootIndex--)
			{
				reheap(rootIndex);
			}
		}
	}
	
	public void add(T newEntry)
	{
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex/2;
		
		while( parentIndex>0 && newEntry.compareTo(heap[parentIndex])>0 )
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex/2;
			numberOfSwaps++;
		}
		
		heap[newIndex] = newEntry;
		lastIndex++;
		
		ensureCapacity();
	}
	
	public T removeMax()
	{
		T root = null;
		
		if(!isEmpty())
		{
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		
		return root;
	}
	
	private void reheap(int rootIndex)
	{
		boolean complete = false;
		
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while(!complete && leftChildIndex <= lastIndex)
		{
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			
			if( rightChildIndex <= lastIndex &&
					heap[rightChildIndex].compareTo(heap[largerChildIndex]) >0) {
				largerChildIndex = rightChildIndex;
			}
			
			if(orphan.compareTo(heap[largerChildIndex])<0)
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = rootIndex * 2;
				numberOfSwaps++;
			}
			else
			{
				complete = true;
			}
		}
		
		heap[rootIndex] = orphan;
	}
	
	public T getMax()
	{
		T root = null;
		
		if(!isEmpty())
		{
			root = heap[1];
		}
		return root;
	}
	
	public boolean isEmpty()
	{
		return lastIndex < 1;
	}
	
	public int getSize()
	{
		return lastIndex;
	}
	
	public void clear()
	{
		while(!isEmpty()) {
			heap[lastIndex] = null;
			lastIndex --;
		}
		
		lastIndex = 0;
		numberOfSwaps = 0;
	}
	
	public int getNumberofSwaps()
	{
		return numberOfSwaps;
	}
	
	public String getFirst10Items()
	{
		String text = "";
		
		for(int i=1; i<11; i++)
		{
			text += heap[i] + ",";
		}
		return text;
	}
	private void ensureCapacity()
	{
		if(lastIndex+1 == heap.length)
		{
			heap = Arrays.copyOf(heap, heap.length*2);
		}
	}
}
