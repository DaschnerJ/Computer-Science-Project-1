

public class IsoscelesTriangle extends EquilateralTriangle
{
	//IsoscelesTriangle class
	public IsoscelesTriangle()
	{
		
	}
	
	//Formula for an area of an IsoscelesTriangle.
	@Override
	public double area(double d, double l)
	{
		return (1/2)*d*l;
	}
	
	//Formula for a perimeter of an IsoscelesTriangle.
	@Override
	public double perimeter(double l, double w)
	{
		return 2l+w;
	}
	
	
	
}
