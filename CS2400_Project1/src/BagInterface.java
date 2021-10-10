
public interface BagInterface<T> {

	//Basic Bag DS Functions
	public int getCurrentSize();
	public boolean isEmpty();
	public boolean add(T item);
	public T remove();
	public boolean remove(T item);
	public void clear();
	public int getFrequencyOf(T item);
	public boolean contains(T item);
	public T[] toArray();
	
	//Project 1 Functions
	public BagInterface<T> union(BagInterface<T> item);
	public BagInterface<T> intersection(BagInterface<T> item);
	public BagInterface<T> difference(BagInterface<T> item);
	public void copyElements(T[] object);
}
