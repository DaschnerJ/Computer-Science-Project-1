

import java.util.Scanner;

public class A1_Q3 {

	//Create instances of each class to use.
	public static Square sq = new Square();
	public static Rectangle rec = new Rectangle();
	public static IsoscelesTriangle it = new IsoscelesTriangle();
	public static EquilateralTriangle et = new EquilateralTriangle();
	//Provide input
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] a) 
	{
	   //String to determine shape type.
	   String s;
	   System.out.println("Enter in 'square', 'rectangle', 'equilateral triangle', or 'isosceles triangle' for the shape you want to calculate:");
	   //Grab next line.
	   s = in.nextLine();
	   //If it is a square calculate for a square.
	   if(s.equalsIgnoreCase("square"))
	   {
		   //Square only needs one side, so get one side.
		   System.out.println("Enter the length of one of the sides: ");
		   double d = in.nextDouble();
		   //Output calculations.
		   System.out.println("The area is " + sq.area(d) + " and the perimeter is " + sq.perimeter(d));
	   }
	   //If it is a rectangle calculate for a rectangle.
	   else if(s.equalsIgnoreCase("rectangle"))
	   {
		   //Rectangle only requires length and width so get length.
		   System.out.println("Enter the length of one of the sides: ");
		   double d = in.nextDouble();
		   //And get width.
		   System.out.println("Enter the length of one of another side: ");
		   double b = in.nextDouble();
		   //Output calculations.
		   System.out.println("The area is " + rec.area(d, b) + " and the perimeter is " + rec.perimeter(d, b));
	   }
	   //If it is an equilateral triangle calculate for an equilateral triangle.
	   else if(s.equalsIgnoreCase("equilateral triangle"))
	   {
		   //Equilateral only needs one side so get one side.
		   System.out.println("Enter the length of one of the sides: ");
		   double d = in.nextDouble();
		   //Output calculations.
		   System.out.println("The area is " + it.area(d) + " and the perimeter is " + it.perimeter(d));
	   }
	   //If it is an isosceles triangle calculate for an isosceles triangle.
	   else if(s.equalsIgnoreCase("isosceles triangle"))
	   {
		   //Isosceles needs base, a side, and a height so get all three.
		   System.out.println("Enter the length of one of the sides that has two equal sides: ");
		   double d = in.nextDouble();
		   //Get base.
		   System.out.println("Enter the length of the base: ");
		   double b = in.nextDouble();
		   //Get height.
		   System.out.println("Enter the length of the height: ");
		   double h = in.nextDouble();
		   //Output calculations.
		   System.out.println("The area is " + it.area(d, b) + " and the perimeter is " + it.perimeter(b, h));
	   }
	   else
	   {
		   //If nothing matched, assume square.
		   System.out.println("Enter the length of one of the sides: ");
		   double d = in.nextDouble();
		   System.out.println("The area is " + sq.area(d) + " and the perimeter is " + sq.perimeter(d)); 
	   }
	}

}









