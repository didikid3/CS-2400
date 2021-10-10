
public class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode;
	private int numOfItems;
	
	public LinkedBag()
	{
		firstNode = null;
		numOfItems = 0;
	}
	
	//Public Methods

	public boolean add(T item)
	{
		Node newNode = new Node(item);
		newNode.setNextNode(firstNode);
		firstNode = newNode;
		numOfItems++;
		
		return true;
	}
	
	public T remove()
	{
		T result = null;
		
		if(firstNode != null)
		{
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numOfItems--;
		}
		
		return result;
	}
	
	public boolean remove(T item)
	{
		boolean removed = false;
		Node nodeLocation = getReferenceTo(item);
		
		if(nodeLocation != null)
		{
			nodeLocation.setData(firstNode.getData());
			firstNode = firstNode.getNextNode();
			numOfItems--;
			removed = true;
		}
		
		return removed;
	}
	
	public boolean isEmpty()
	{
		return numOfItems == 0;
	}
	
	public int getCurrentSize()
	{
		return numOfItems;
	}
	
	public void clear()
	{
		while(!isEmpty())
		{
			remove();
		}
	}
	
	public int getFrequencyOf(T item)
	{
		int count = 0;
		
		int index = 0;
		
		Node currentLocation = firstNode;
		while(index < numOfItems && currentLocation != null)
		{
			if(item.equals(currentLocation.getData()))
			{
				count++;
			}
			
			currentLocation = currentLocation.getNextNode();
			index++;
		}
		
		return count;
	}
	
	public boolean contains(T item)
	{
		 boolean found = false;
		 
		 Node currentLocation = firstNode;
		 
		 while(!found && currentLocation!=null)
		 {
			 if(item.equals(currentLocation.getData()))
			 {
				 found = true;
			 }
			 else
			 {
				 currentLocation = currentLocation.getNextNode();
			 }
		 }
		 
		 return found;
	}
	
	public T[] toArray()
	{
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numOfItems];
		
		int index = 0;
		Node currentLocation = firstNode;
		
		while(index < numOfItems && currentLocation!=null)
		{
			result[index] = currentLocation.getData();
			
			index++;
			currentLocation = currentLocation.getNextNode();
		}
		
		return result;
	}
	
	//Project 1 Methods
	
	
	//Make a copy of current Bag. Add reference bag data to copy bag
	public BagInterface<T> union(BagInterface<T> item)
	{
		//Getting data from reference bag
		T[] content = item.toArray();
		
		//Making copy bag
		T[] thisArray = toArray();
		BagInterface<T> result = new LinkedBag<T>();
		result.copyElements(thisArray);
		
		//Adding data to copy bag
		for(int i=0;i<content.length;i++)
		{
			result.add(content[i]);
		}
		
		return result;
	}
	
	//Creates a new bag. Iterates through reference bag data
	//and sees if that is inside of current bag and not already in new bag.
	//If it is add the data the amount of times it intersects
	public BagInterface<T> intersection(BagInterface<T> item)
	{
		//Data of reference bag
		T[] content = item.toArray();
		
		//New bag
		BagInterface<T> result = new LinkedBag<T>();
		
		//Iterates through reference data
		int count = 0;
		for(int i=0;i<content.length;i++)
		{
			//If reference data is in current bag
			//and data is not already in new bag
			if(contains(content[i]) && !result.contains(content[i]))
			{
				//How many times does the data intersect
				count = Math.min(getFrequencyOf(content[i]), item.getFrequencyOf(content[i]));
				
				//adding data to new bag
				for(int j=0; j<count; j++)
				{
					result.add(content[i]);
				}
			}
		}
		
		return result;
	}
	
	//Makes a copy of current bag. If reference bag data is found
	//Inside of copy bag, remove it from copy bag.
	public BagInterface<T> difference(BagInterface<T> item)
	{
		//Data of reference bag
		T[] content = item.toArray();
		
		//Copy of current bag
		T[] thisArray = toArray();
		BagInterface<T> result = new LinkedBag<T>();
		result.copyElements(thisArray);
		
		//Iterates through reference bag
		for(int i=0;i<content.length;i++)
		{
			//Checking if copy bad has reference bag data
			if(result.contains(content[i]))
			{
				//Removing data the amount of times needed
				int count = Math.min(getFrequencyOf(content[i]), item.getFrequencyOf(content[i]));
				for(int j=0;j<count;j++)
					result.remove(content[i]);
			}
				
		}
		
		return result;
	}
	
	//Method is used to copy data from current bag to a new bag.
	public void copyElements(T[] object)
	{
		for(int i=0; i< object.length;i++)
		{
			add(object[i]);
		}
	}
	
	//Private Methods
	
	private Node getReferenceTo(T item)
	{
		Node currentNode = firstNode;
		boolean found = false;
		
		while(!found && currentNode!= null)
		{
			if(item.equals(currentNode.getData()))
			{
				found = true;
			}
			else
			{
				currentNode = currentNode.getNextNode();
			}
		}
		
		return currentNode;
	
	}
	
	private class Node
	{
		private T data;
		private Node next;
		
		private Node(T data)
		{
			this(data,null);
		}
		
		private Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
		
		private T getData()
		{
			return data;
		}
		
		private void setData(T item)
		{
			data = item;
		}
		
		private Node getNextNode()
		{
			return next;
		}
		
		private void setNextNode(Node item)
		{
			next = item;
		}
	}//End Node Class

}//End LinkedBag Class
