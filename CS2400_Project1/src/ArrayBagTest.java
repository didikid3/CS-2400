
public class ArrayBagTest {
	
	public static void main(String[] args)
	{
		
		//Setting Up Bag 1 and 2
		BagInterface<String> bag1 = new ResizeableArrayBag<String>();
		BagInterface<String> bag2 = new ResizeableArrayBag<String>();
		
		String[] b1 = {"a","b","c"};
		String[] b2 = {"b","b","d","e"};
		for(int i=0;i<b1.length;i++)
		{
			bag1.add(b1[i]);
		}
		for(int i=0;i<b2.length;i++)
		{
			bag2.add(b2[i]);
		}
		
		System.out.println("Bag 1:");
		for(int i=0;i<b1.length;i++)
		{
			System.out.print(b1[i] + " ");
		}
		
		System.out.println("\nBag 2:");
		for(int i=0;i<b2.length;i++)
		{
			System.out.print(b2[i] + " ");
		}
		
		
		
		//Testing Functions
		BagInterface<String> union = bag1.union(bag2);
		
		Object[] result_Union = union.toArray();
		
		System.out.println("\nUnion:");
		for(int i=0;i<result_Union.length;i++)
		{
			String letter = (String)result_Union[i];
			System.out.print(letter + " ");
		}
		
		BagInterface<String> intersection = bag1.intersection(bag2);
		
		Object[] result_Intersection = intersection.toArray();
		
		System.out.println("\nIntersection:");
		for(int i=0;i<result_Intersection.length;i++)
		{
			String letter = (String)result_Intersection[i];
			System.out.print(letter + " ");
		}
		
		BagInterface<String> difference12 = bag1.difference(bag2);
		
		Object[] result_1Difference2 = difference12.toArray();
		
		System.out.println("\nDifference of Bag 1 - Bag 2:");
		for(int i=0;i<result_1Difference2.length;i++)
		{
			String letter = (String)result_1Difference2[i];
			System.out.print(letter + " ");
		}
		
		BagInterface<String> difference21 = bag2.difference(bag1);
		
		Object[] result_2Difference1 = difference21.toArray();
		
		System.out.println("\nDifference of Bag 2 - Bag 1:");
		for(int i=0;i<result_2Difference1.length;i++)
		{
			String letter = (String)result_2Difference1[i];
			System.out.print(letter + " ");
		}
	}
}
