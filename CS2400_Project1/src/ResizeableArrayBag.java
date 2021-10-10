import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T> {

	
	private T[] bag;
	private int numOfItems;
	private static final int DEFAULT_CAPACITY = 20;
	
	public ResizeableArrayBag()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ResizeableArrayBag(int size)
	{
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[size];
		bag = tempBag;
	}
	
	//Public Methods
	public int getCurrentSize()
	{
		return numOfItems;
	}
	
	public boolean isEmpty()
	{
		return numOfItems == 0;
	}
	
	public boolean add(T item)
	{
		boolean result = true;
		if(isFull())
			doubleCapacity();
		
		bag[numOfItems] = item;
		numOfItems++;
		
		return result;		
	}
	
	public T remove()
	{
		return removeEntry(numOfItems -1);
	}
	
	public boolean remove(T item)
	{
		T result = removeEntry( getIndexOf(item) );
		if(result.equals(item))
			return true;
		return false;
	}
	
	public void clear()
	{
		while(!isEmpty())
			remove();
	}
	
	public int getFrequencyOf(T item)
	{
		int count = 0;
		for(int i=0;i<numOfItems;i++)
		{
			if(bag[i].equals(item))
				count++;
		}
		
		return count;
	}
	
	public boolean contains(T item)
	{
		if(getIndexOf(item) >= 0)
			return true;
		return false;
	}
	
	public T[] toArray()
	{
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[numOfItems];
		for(int i=0;i<numOfItems;i++)
		{
			tempBag[i]=bag[i];
		}
		return tempBag;
	}

	
	
	//Project 1 New Methods
	
	//Create a copy of the current bag. Add reference bag data
	//to copy bag.
	public BagInterface<T> union(BagInterface<T> item)
	{
		T[] content = item.toArray();	//First getting data from other bag
		
		//Copying current bag data to a new bag
		T[] thisArray = toArray();
		BagInterface<T> copyThis= new ResizeableArrayBag<T>();
		copyThis.copyElements(thisArray);
		
		//Combining both bags together
		for(int i=0;i<content.length;i++)
		{
			copyThis.add(content[i]);
		}
	
		return copyThis;
	}
	
	//Creates a new bag. Iterates through reference bag
	//to find common data in the current bag. If this data
	//is not already in the new bag, begin adding data to new bag.
	//Add the data multiple times based on the amount of times
	//the data intersects.
	public BagInterface<T> intersection(BagInterface<T> item)
	{
		T[] content = item.toArray();	//First getting data from other bag
		
		//Bag will store data that is in common
		BagInterface<T> copyThis= new ResizeableArrayBag<T>();
		
		int count = 0;	//How many times a certain data is in intersection
		
		
		//Looking at every item in other bag
		for(int i=0;i<content.length;i++)
		{
			//If this bag and other bag have the data in common
			//But data has not yet been stored in intersection bag
			if(contains(content[i]) && !copyThis.contains(content[i]))
			{
				//Maximum amount of times data intersects
				count = Math.min(getFrequencyOf(content[i]), item.getFrequencyOf(content[i]));
				
				//Add data to intersection bag "count" times
				for(int j=0;j<count;j++)
				{
					copyThis.add(content[i]);
				}
			}
		}
		
		return copyThis;
	}
	
	//Create a copy of the current bag. If the reference bag
	//data is found in the copy bag, remove it from the
	//copy bag.
	public BagInterface<T> difference(BagInterface<T> item)
	{
		T[] content = item.toArray();	//First getting data from other bag
		
		//Copying current bag data to a new bag
		T[] thisArray = toArray();
		BagInterface<T> copyThis= new ResizeableArrayBag<T>();
		copyThis.copyElements(thisArray);
		
		//Looks at every data in other bag
		for(int i=0;i<content.length;i++)
		{
			//If a data from other bag is in current bag
			//Remove the data from the copy bag
			if(copyThis.contains(content[i])) {
				//Checks how many times I need to remove item
				int count = Math.min(getFrequencyOf(content[i]), item.getFrequencyOf(content[i]));
				for(int j=0;j<count;j++)
					copyThis.remove(content[i]);
			}
		}
		
		return copyThis;
	}
	
	//Given the data from a bag copy the data to the current object
	public void copyElements(T[] thisArray)
	{
		for(int i=0;i<thisArray.length;i++)
		{
			add(thisArray[i]);
		}
	}
	
	
	
	
	//Private Methods
	private boolean isFull()
	{
		return numOfItems == bag.length;
	}
	
	private int getIndexOf(T item)
	{
		int location = -1;
		int index = 0;
		boolean found = false;
		while(!found && index< numOfItems)
		{
			if(bag[index].equals(item))
			{
				found = true;
				location = index;
			}
			else
				index++;
		}
		return location;
	}
	
	private T removeEntry(int index)
	{
		T result = null;
		
		if(!isEmpty() && index >=0)
		{
			result = bag[index];
			bag[index] = bag[numOfItems - 1];
			bag[numOfItems -1] = null;
			numOfItems--;
		}
		
		return result;
	}
	
	private void doubleCapacity()
	{
		int size = 2 * bag.length;
		bag = Arrays.copyOf(bag,size);
	}
}//END Class
