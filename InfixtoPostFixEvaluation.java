package infixPostfixCalculation;

import java.util.*;

public class InfixtoPostFixEvaluation {

	public static void main(String[] args) {
		Scanner sn = new Scanner(System.in);
		System.out.print("Enter the Mathematical Expression : ");
		String expression = sn.nextLine();
		System.out.println("The Postfix Expression is: " +performInfixToPostfix(expression));
		System.out.println("Postfix evaluation: " + calculatePostFixExpression(expression));
	}

	
	 static String performInfixToPostfix(String expression) 
	    { 
	    
	        String result = new String(""); 
	        Stack<Character> operatorStack = new Stack<>(); 
	          
	        for (int i = 0; i<expression.length(); ++i) 
	        { 
	            char current = expression.charAt(i); 
	              
	            
	            if (Character.isLetterOrDigit(current)) 
	                result += current; 
	               
	            else if (current == '(') 
	            	operatorStack.push(current); 
	              
	
	            else if (current == ')') 
	            { 
	                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') 
	                    result += operatorStack.pop(); 
	                  
	                if (!operatorStack.isEmpty() && operatorStack.peek() != '(') 
	                    return "Invalid Expression";                
	                else
	                	operatorStack.pop(); 
	            } 
	            else 
	            { 
	                while (!operatorStack.isEmpty() && SupportUtil.operators(current) <= SupportUtil.operators(operatorStack.peek())){ 
	                    if(operatorStack.peek() == '(') 
	                        return "Invalid Expression"; 
	                    result += operatorStack.pop(); 
	             } 
	                operatorStack.push(current); 
	            } 
	       
	        } 
	        
	        while (!operatorStack.isEmpty()){ 
	            if(operatorStack.peek() == '(') 
	                return "Invalid Expression"; 
	            result += operatorStack.pop(); 
	         } 
	        return result; 
	    } 
	 
	 static long calculatePostFixExpression(String expression) {

			Stack<Long> operandStack = new Stack<>();
			Stack<Character> operatorStack = new Stack<>();

			if (!SupportUtil.isValidExpression(expression)) {
				System.out.println("Invalid expression");
				return 0;
			}

			int i = 0;
			String currentInteger = null;
			char indexValue = expression.charAt(i);
			while (i < expression.length()) {

				if (indexValue >= '0' && indexValue <= '9') {

					currentInteger = expression.charAt(i) + "";
					i++;
					while (i != expression.length() && (indexValue >= '0' && indexValue<= '9')) {
						currentInteger = currentInteger + indexValue;
						i++;
					}

					operandStack.push(Long.parseLong(currentInteger));
				} else {

					if (expression.charAt(i) == ')') {

						while (operatorStack.peek() != '(') {
							SupportUtil.performArithmeticOperation(operandStack, operatorStack);
						}
						operatorStack.pop();
					} else {

						Character currentOperator = expression.charAt(i);
						Character last = (operatorStack.isEmpty() ? null : operatorStack.peek());

						if (last != null && SupportUtil.checkPrecedence(currentOperator, last)) {
							SupportUtil.performArithmeticOperation(operandStack, operatorStack);
						}
						operatorStack.push(indexValue);

					}
					i++;
				}

			}

			while (!operatorStack.isEmpty()) {
				SupportUtil.performArithmeticOperation(operandStack, operatorStack);
			}

			return operandStack.pop();

		}


}

