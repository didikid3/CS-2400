
public class LinkedStack<T> implements StackInterface<T> {

	private Node topNode;
	
	public LinkedStack()
	{
		topNode = null;
	}
	
	//Main method to convert infix to postfix
	//Uses similar logic as that of our textbook.
	//Though instead of using the switch statement, I used if statements
	public static String convertToPostfix(String infix)
	{
		String postfix = "";
		StackInterface<Character> operatorStack = new LinkedStack<Character>();
		char position;
		int size = infix.length();
			
		for(int i = 0; i<size; i++)
		{
			position = infix.charAt(i);
				
			switch(position)
			{
				
			//Char is a variable
			case 'a':case 'b':case 'c':case 'd':
			case 'e':case 'f':case 'g':case 'h':
			case 'i':case 'j':case 'k':case 'l':
			case 'm':case 'n':case 'o':case 'p':
			case 'q':case 'r':case 's':case 't':
			case 'u':case 'v':case 'w':case 'x':
			case 'y':case 'z':
				postfix += position;
				break;
				
				
			case '^':
				operatorStack.push(position);
				break;
					
					
					
			//If the current char is a operator
			case '*': case '/': case '%':	case '+':case '-':
				//Checks if operator stack is empty, if so then
				//If the precedence of the top item is greater than the current char
				//Add it to the postfix
				while(!operatorStack.isEmpty() && pemdas(operatorStack.peek()) >= pemdas(position))
				{
					postfix += operatorStack.pop();
				}
					
				operatorStack.push(position);
				break;
				
				
					
			//Remove everything from stack until we see open parenthesis
			case ')':
				char temp = operatorStack.pop();
				while(temp != '(')
				{
					postfix += temp;
					temp = operatorStack.pop();
				}
				break;
				
					
			//Just add open parenthesis into stack
			case '(':
				operatorStack.push(position);
				break;
				
			}//END SWITCH
		}//END FOR LOOP
			

		//Add the remaining operations in the stack to the postfix string
		while(!operatorStack.isEmpty())
		{
			postfix += operatorStack.pop();
		}
			
		return postfix;
	}//End Method
		
		
	//Getting the correct order of operations
	//This is the precedence, higher int is higher precedence
	public static int pemdas(char letter)
	{
		switch(letter)
		{
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
		case '%':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}	
		
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}

	public T pop() {
		T top = peek();
		topNode = topNode.getNext();
		
		return top;
	}

	public T peek() {
		if(!isEmpty())
			return topNode.getData();
		return null;
	}

	public boolean isEmpty() {
		return topNode == null;
	}

	public void clear() {
		topNode = null;
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
		
		private Node getNext()
		{
			return next;
		}
		
		private void setNext(Node item)
		{
			next = item;
		}
	}//End Node Class

}
