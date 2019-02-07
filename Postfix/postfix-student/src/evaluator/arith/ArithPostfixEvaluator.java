package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator()
	{
		stack = new LinkedStack<Operand<Integer>>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException 
	{
		
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) 
		{
			Type type = token.getType();	
			switch(type)
			{
			case OPERAND:
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				if(token.getOperator().toString() == "+")
				{
					if(stack.size() < 2)
						throw new IllegalPostfixExpressionException("Something went wrong, not enough operands");
					
					token.getOperator().setOperand(0, stack.pop());
					token.getOperator().setOperand(1, stack.pop());				
					stack.push(token.getOperator().performOperation());
					
				}

				if(token.getOperator().toString() == "-")
				{
					if(stack.size() < 2)
						throw new IllegalPostfixExpressionException("Something went wrong");
					
					token.getOperator().setOperand(1, stack.pop());
					token.getOperator().setOperand(0, stack.pop());
					stack.push(token.getOperator().performOperation());
					
				}

				if(token.getOperator().toString() == "*")
				{
					if(stack.size() < 2)
						throw new IllegalPostfixExpressionException("Something went wrong");
					
					token.getOperator().setOperand(0, stack.pop());		
					token.getOperator().setOperand(1, stack.pop());
					stack.push(token.getOperator().performOperation());
					
				}
				if(token.getOperator().toString() == "/")
				{
					if(stack.size() < 2)
						throw new IllegalPostfixExpressionException("Something went wrong");
					
					token.getOperator().setOperand(1, stack.pop());				
					token.getOperator().setOperand(0, stack.pop());
					stack.push(token.getOperator().performOperation());
					
				}
				if(token.getOperator().toString() == "!")				
				{
					if(stack.size() < 1)
						throw new IllegalPostfixExpressionException("Something went wrong");
					
					token.getOperator().setOperand(0, stack.pop());
					stack.push(token.getOperator().performOperation());
					
				}
				break;
				
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);

			}	// end of switch

		} //end of for-each loop


		if(stack.size() != 1)
			throw new IllegalPostfixExpressionException("Something went wrong");
			
		
			return stack.pop().getValue(); 
		
	}// end of method

}
