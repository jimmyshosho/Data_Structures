package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
* The {@code MultOperator} is an operator that performs multiplication on two
* integers.
* @author jcollard
*
*/
public class MultOperator extends BinaryOperator<Integer> {

    /**
        * {@inheritDoc}
    */
    
    @Override
    public Operand<Integer> performOperation() 
    {
            if(op0 == null || op1 == null)
            	throw new IllegalStateException("Cannot multiply because one or more operands are null");
            
            Integer product = op0.getValue() * op1.getValue();
            
            return new Operand<Integer>(product);
    }
    
    public String toString()
    {
    	return "*";
    }
}
