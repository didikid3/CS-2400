import java.util.Arrays;

public class ResizeableArrayStack<T> implements StackInterface<T> {

	private T[] stack;
	private int topIndex;
	private static final int DEFAULT_CAPACITY = 20;
	
	public ResizeableArrayStack()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ResizeableArrayStack(int size)
	{
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[size];
		stack = tempBag;
		topIndex = -1;
	}
	
	//Given a string, calculate the postfix expression
	public static double evaluatePostfix(String postfix)
	{
		StackInterface<Double> valueStack = new ResizeableArrayStack<Double>();
		
		int size = postfix.length();
		char nextChar;
		for(int i=0;i<size;i++)
		{
			nextChar = postfix.charAt(i);
			
			//Assigning Variables Values
			if(nextChar == 'a')
				nextChar = '2';
			else if(nextChar == 'b')
				nextChar = '3';
			else if(nextChar == 'c')
				nextChar = '4';
			else if(nextChar == 'd')
				nextChar = '5';
			else if(nextChar == 'e')
				nextChar = '6';
			
			
			//Determine what to do with current char
			switch(nextChar)
			{
			//If it is a variable
			case '0':case '1':case '2':
			case '3':case '4':case '5':
			case '6':case '7':case '8':
			case '9':
				//Convert the char to a double
				//Since it is ascii, we have to subtract the ascii
				//Value of 0 to get the true "numerical value"
				valueStack.push(Double.valueOf(nextChar)-48.0);
				break;
				
			//If it is a operator
			case '+':case '-':case '*':
			case '/':case '^':case '%':
				double var2 = valueStack.pop();
				double var1 = valueStack.pop();
				double result;
				
				if(nextChar == '+')
				{
					result = var1+var2;
				}
				else if(nextChar == '-')
				{
					result = var1-var2;
				}
				else if(nextChar == '*')
				{
					result = var1*var2;
				}
				else if(nextChar == '/')
				{
					result = var1/var2;
				}
				else if(nextChar == '^')
				{
					result = Math.pow(var1, var2);
				}
				else
				{
					result = var1%var2;
				}
				
				valueStack.push(result);
				break;
				
			}//END SWITCH
		}//END FOR
		
		return valueStack.peek();
	}//END METHOD
	
	public void push(T newEntry) {
		checkSpace();
		stack[topIndex+1] = newEntry;
		topIndex++;
	}


	public T pop() {
		if(!isEmpty())
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
		return null;
	}


	public T peek() {
		if(!isEmpty())
			return stack[topIndex];
		return null;
	}


	public boolean isEmpty() {
		return topIndex == -1;
	}


	public void clear() {
		while(!isEmpty())
		{
			pop();
		}
	}
	
	
	//Private Methods
	private void checkSpace()
	{
		if(topIndex >= stack.length -1)
		{
			stack = Arrays.copyOf(stack, stack.length * 2);
		}
	}

}
