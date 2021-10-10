import java.io.*;
import java.util.Scanner;

public class testHeap {

	
	public static void main(String[] args) throws IOException
	{
		File inputFile = new File("data.txt");
		Scanner scanner = new Scanner(inputFile);
		
		File outputFile = new File("output.txt");
		FileWriter fw = new FileWriter(outputFile);
		PrintWriter pw = new PrintWriter(fw);

		
		//Data to be used for both heaps
		Integer[] data = new Integer[100];
		for(int i=0;i<100;i++)
		{
			data[i] = Integer.parseInt(scanner.nextLine());
		}
		scanner.close();
		
		
		pw.println("=====================================================================");


		//Creation of Sequential Heap
		heap<Integer> SequentialHeap = new heap<Integer>(1,data);
		
		
		//Displaying Heap and Number of Swaps
		pw.print("Heap built using sequential insertions: ");
		pw.print(SequentialHeap.getFirst10Items()+"...\n");
	
		pw.println("Number of swaps in the heap creation: "+
				SequentialHeap.getNumberofSwaps());
		
		
		//10 Removal Methods
		for(int i=0; i<10; i++)
		{
			SequentialHeap.removeMax();
		}
		
		//Displaying Heap After Removal
		pw.print("Heap after 10 removals: ");
		pw.print(SequentialHeap.getFirst10Items()+"...\n\n");

		
		
		//Creation of  Optimal Heap
		heap<Integer> OptimalHeap = new heap<Integer>(2,data);
		
		
		//Displaying Heap and Number of Swaps
		pw.print("Heap built using optimal methods: ");
		pw.print(OptimalHeap.getFirst10Items()+"...\n");
	
		pw.println("Number of swaps in the heap creation: "+
				OptimalHeap.getNumberofSwaps());
		
		
		//10 Removal Methods
		for(int i=0; i<10; i++)
		{
			OptimalHeap.removeMax();
		}
		
		//Displaying Heap After Removal
		pw.print("Heap after 10 removals: ");
		pw.print(OptimalHeap.getFirst10Items()+"...\n");
		
		
		pw.print("=====================================================================");
		pw.close();
		
	}
}
