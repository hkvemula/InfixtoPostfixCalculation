package infixPostfixCalculation;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SupportUtil {

	
	 static void performArithmeticOperation(Stack<Long> operandStack, Stack<Character> operatorStack) {
			try {
				long value1 = operandStack.pop();
				long value2 = operandStack.pop();
				char operator = operatorStack.pop();

				long intermediateResult = resultSet(value1, value2, operator);
				operandStack.push(intermediateResult);
			} catch (EmptyStackException e) {
				System.out.println("Not a valid expression to evaluate");
				throw e;
			}
		}
	 
	 static long resultSet(long val2, long val1, Character op) {

			long result;
			switch (op) {

			case '+':
				result = val1 + val2;
				break;

			case '-':
				result = val1 - val2;
				break;

			case '*':
				result = val1 * val2;
				break;

			case '/':
				result = val1 / val2;
				break;

			case '%':
				result = val1 % val2;
				break;
			default:
				result = val1 + val2;

			}
			return result;
		}

	 static boolean checkPrecedence(Character operator1, Character operator2) {

			List<Character> opList = new ArrayList<>();
			opList.add('(');
			opList.add(')');
			opList.add('/');
			opList.add('*');
			opList.add('%');
			opList.add('+');
			opList.add('-');

			if (operator2 == '(') {
				return false;
			}

			if (opList.indexOf(operator1) > opList.indexOf(operator2)) {
				return true;
			} else {
				return false;
			}

		}

	 static boolean isValidExpression(String expression) {

			if ((!Character.isDigit(expression.charAt(0)) && !(expression.charAt(0) == '('))
					|| (!Character.isDigit(expression.charAt(expression.length() - 1))
							&& !(expression.charAt(expression.length() - 1) == ')'))) {
				return false;
			}

			HashSet<Character> validChar = new HashSet<>();
			validChar.add('*');
			validChar.add('+');
			validChar.add('-');
			validChar.add('/');
			validChar.add('%');
			validChar.add('(');
			validChar.add(')');

			Stack<Character> validParenthesisCheck = new Stack<>();

			for (int i = 0; i < expression.length(); i++) {

				if (!Character.isDigit(expression.charAt(i)) && !validChar.contains(expression.charAt(i))) {
					return false;
				}

				if (expression.charAt(i) == '(') {
					validParenthesisCheck.push(expression.charAt(i));
				}

				if (expression.charAt(i) == ')') {

					if (validParenthesisCheck.isEmpty()) {
						return false;
					}
					validParenthesisCheck.pop();
				}
			}

			if (validParenthesisCheck.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

	 static int operators(char ch) 
	    { 
	        switch (ch) 
	        { 
	        case '+': 
	        case '-': 
	            return 1; 
	       
	        case '*': 
	        case '/': 
	            return 2; 
	       
	        case '^': 
	            return 3; 
	        } 
	        return -1; 
	    } 

}
