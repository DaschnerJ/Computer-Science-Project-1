

public class Rectangle extends Square
{
	//Rectangle's class
	public Rectangle()
	{
		
	}
	
	//Adjusted formula for a perimeter of a rectangle.
	@Override
	public double perimeter(double d, double l) 
	{
		return 2d+2l;
	}
	
	//Adjusted formula for an area of a rectangle.
	@Override
	public double area(double w, double l) 
	{
		return w*l;
	}
	
}
