package language.arith;

import language.BinaryOperator;
import language.Operand;


/**
 * The {@code SubOperator} is an operator that performs subtraction on two
 * integers.
 * @author jcollard
 *
 */
public class SubOperator extends BinaryOperator<Integer> 
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operand<Integer> performOperation() 
	{
          if(op0 == null || op1 == null)
        	  throw new IllegalStateException("One of the two operands are not set and cannot perfrom subtraction");
          
          	Integer total = op0.getValue() - op1.getValue();
            return new Operand<Integer>(total);
	}
	
	public String toString()
	{
		return "-";
	}

}
