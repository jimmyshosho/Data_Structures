package language.arith;

import language.Operand;
import language.Operator;

public abstract class UnaryOperator<T> implements Operator<T>
{
	protected Operand<T> op0;

	public final int getNumberOfArguments()
	{
		return 1;
	}

	public void setOperand(int i, Operand<T> operand)
	{
		if(operand == null)
			throw new NullPointerException("Operand is null, cannot set to anything");
		if( i > 0)
			throw new IllegalArgumentException("Unary Operator only accepts 1 but recieved " + i);
		else 
		{
			if(op0 !=null)
				throw new IllegalStateException("Operand at position " + i + " is already set ");
			
			op0 = operand;
		}


	}

}
