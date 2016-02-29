

public class EquilateralTriangle extends Square
{
	//EquilateralTriangle class.
	public EquilateralTriangle()
	{
		
	}
	
	//Formula for an area of an EquilateralTriangle.
	@Override
	public double area(double l)
	{
		return (Math.pow(3, 1/2)/4)*l*l;
	}
	
	//Formula for an area of an EquilateralTriangle.
	@Override
	public double perimeter(double d)
	{
		return 3*d;
	}
}
