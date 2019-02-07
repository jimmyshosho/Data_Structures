package language.arith;

import language.Operand;
import language.arith.UnaryOperator;

/**
 * The {@code NegateOperator} is an operator that performs negation on a single integer
 * @author jcollard
 *
 */

public class NegateOperator extends UnaryOperator<Integer> 
{

	public Operand<Integer> performOperation() 
	{
		if(op0 == null)
			throw new IllegalStateException("Cannot perform operation without operand");
		
		Integer negate = op0.getValue() * -1;
		return new Operand<Integer>(negate);
	}
	
	public String toString()
	{
		return "!";
	}

}
