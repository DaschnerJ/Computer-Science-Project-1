

public class Square implements Polygon
{
	
	public Square()
	{
		
	}

	//Formula for a square's area.
	@Override
	public double area(double w) 
	{
		return w*w;
	}

	//Formula for perimeter of a square.
	@Override
	public double perimeter(double d) 
	{
		return 4d;
	}

	//Returns 0 because this function should not be used here.
	@Override
	public double perimeter(double w, double l) 
	{
		return 0;
	}

	//Returns 0 because this function should not be used here.
	@Override
	public double area(double w, double l) {
		return 0;
	}

}
