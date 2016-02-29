import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A1_Q4 {

	private static Scanner in = new Scanner(System.in);
	
	private static boolean parenthesis, exponents, multiplication, division, addition, subtraction = false;
	
	private static Queue<String> ProblemList = new LinkedList<String>();
	
	public static void main(String[] args) 
	{
		File f = new File("q4SampleInput");
		try 
		{
			readFile(f);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for(String s : ProblemList)
		{
		  String a = s.replaceAll("**", "^");
		  while(parenthesis != true || exponents != true || multiplication != true || division != true || addition != true || subtraction != true)
		  {
			parenthesis(a);
		  }
		  parenthesis = false;
		  exponents = false;
		  multiplication = false;
		  division = false;
		  addition = false;
		  subtraction = false;
		}
		
	}
	
	private static void readFile(File f) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(f));
	 
		String line = null;
		while ((line = br.readLine()) != null) 
		{
			System.out.println("Adding line " + line);
			ProblemList.add(line);
		}
	 
		br.close();
	}
	
	//Solves (b) first before a(b)-c^(d)*(e);
	private static String parenthesis(String s)
	{
		//Checks if there is a parenthesis expression first.
		if(s.contains(")"))
		{
			//If there is, time to check where this parenthesis first started.
			int index1 = s.indexOf(")");
			int index2 = index1;
			boolean trip = false;
			boolean found = false;
			int count = 0;
			while(!trip)
			{
				//Makes sure it doesn't go into negative indices.
				count++;
				if(index1 - count >= 0)
				{
					//Goes down an index first.
					
					//Checks that character to see if it the starting parenthesis.
					if(s.charAt(index1 - count) == '(')
					{
						found = true;
						trip = true;
					}
					//If it finds nothing it keeps going down an index.
				}
				//If it reaches 0 and finds nothing, go ahead and trip the equation.
				else
				{
					trip = true;
				}
			}
			//If the loop tripped before finding the opening parenthesis then it
			//is an invalid expression and there are too many ")".
			if(found == false)
			{
				System.out.println("Invalid expression.");
				parenthesis = true;
				exponents = true; 
				multiplication = true; 
				division = true; 
				addition = true;
				subtraction = true;
				return null;
			}
			//Else it must have found an opening parenthesis and it solves the equation
			//inside the parenthesis first and then inserts it back into the equation
			//to be solved again.
			else
			{
				count--;
				//a + (b) + c is a general parenthesis equation.
				String a = s.substring(0, index1-count-1);
				String b = exponents(s.substring(index1-count, index2));
				String c = s.substring(index2+1);
				//Makes sure that a #*b exists so it isn't #b.
				if(a.length() != 0)
				if(checkIsInteger(a, a.length()-1))
				{
					a = a + "*";
				}
				//Makes sure that a )*b exists so it isn't )b.
				if(a.length() != 0)
				if(checkIsParenthesis(a, a.length()-1))
				{
					a = a + "*";
				}
				//Makes sure that a b*# exists so it isn't b#.
				if(c.length() != 0)
				if(checkIsInteger(c, c.length()-1))
				{
					c = "*" + c;
				}
				//Makes sure that a b*( exists so it isn't b(.
				if(c.length() != 0)
				if(checkIsParenthesis(c, c.length()-1))
				{
					c = "*" + c;
				}
				//Creates the new equation with the modifications.
				String newEquation =  a + b + c;
				//Continues to check for more parenthesis equations.
				return parenthesis(newEquation);
			}
		}
		//If there isn't, go straight to doing exponents.
		else
		{
			parenthesis = true;
			String finalEquation = exponents(s);
			System.out.println("Final answer is " + s + " = " + finalEquation);
			return finalEquation;
		}
	}
	
	private static String exponents(String s)
	{
		//Checks if there is an exponent function.
		if(s.contains("^"))
		{
			int index1 = s.indexOf("^");
			int index2 = s.indexOf("^");
			boolean trip = false;
			boolean found = false;
			int count = 0;
			//check if previous numbers are digits or if it a messed up '+' if it is, remove it.
			while(!trip)
			{
				//Checks to make sure there isn't negative indices.
				count++;
				if(index1 - count >= 0)
				{
					//Decreases the index before checking.
					//Checks if the previous number is an integer.
					if(checkIsInteger(s, index1 - count))
					{
						//If it is, the '+' is valid but it does not trip to check if it
						//a double digit or has more than 1 character, example 8888+3.
						found = true;
					}
					else
					{
						//If it isn't a digit, then if it has been found, it must be the end
						//of the function or there are two functions next to each other
						//if it is not found but must be tripped.
						trip = true;
					}
				}
				//If there isn't, it didn't find an integer and must be an invalid '+'.
				else
				{
					trip = true;
				}
			}
			//Removes the invalid '+' if it is invalid and continues looking for more '+'.
			if(trip == true && found == false)
			{
				return exponents(s.substring(0, index2) + s.substring(index2+1));
			}
			//Else it must be a valid '+' so far to the left, time to check if it is valid
			//to the right.
			else
			{
				int index3 = index2;
				boolean trip2 = false;
				boolean found2 = false;
				int count2 = 0;
				//check if next numbers are digits or if it a messed up '+' if it is, remove it.
				while(!trip2)
				{
					//Makes sure the function does not exceed the string's length.
					count2++;
					if(index3 + count2 <= s.length()-1)
					{
						//Checks if next character is an integer.
						if(checkIsInteger(s, index3+count2))
						{
							//If it is, it must be a valid '+' but does not trip the equation
							//since it could be multiple digits, example 1+88888;
							found2 = true;
						}
						else
						{
							//It trips the loop if the trailing index is not an integer
							//because '+' is either invalid or is the end of the
							//exponent function.
							trip2 = true;
						}
					}
					//If it does, it trips the loop because it must be at the end of the string.
					else
					{
						trip2 = true;
					}
				}
				//If the loop was tripped and nothing was found then it must be an invalid '+'
				//so it must be removed and continues looking for exponential functions.
				if(trip2 == true && found2 == false)
				{
					return exponents(s.substring(0, index2) + s.substring(index2+1));
				}
				//Else it must be a valid '+' with valid numbers and does the exponential function.
				else
				{
					//Readjust Count.
					count--;
					//The formula is a+b = c so get the integers for the equation.
					double a = convertSubstringToInt(s, index1-count, index2);
					double b = convertSubstringToInt(s, index2+1, index3+count2);
					double c = a;
					for(int i = 1; i < b; i++)
					{
						c = c * a;
					}
					
					//a + (b) + c is a general parenthesis equation.
					String d = s.substring(0, index1-count);
					String e = String.valueOf(c);
					String f = s.substring(index3+count2);
					
					//Makes sure that a #*e exists so it isn't #e.
					if(d.length() != 0)
					if(checkIsInteger(d, d.length()-1))
					{
						d = d + "*";
					}
					//Makes sure that a e*# exists so it isn't e#.
					if(f.length() != 0)
					if(checkIsInteger(f, f.length()-1))
					{
						f = "*" + f;
					}
					//Creates the new equation with the modifications.
					String newEquation =  d + e + f;
					//Continues to check for more multiplication equations.
					return exponents(newEquation);
				}
			}	
		}
		//If there isn't go straight to multiplication.
		else
		{
			exponents = true;
			return multiplication(s);
		}
	}
	
	private static String multiplication(String s)
	{
		//Checks if the function has a multiplication function.
		if(s.contains("*"))
		{
			int index1 = s.indexOf("*");
			int index2 = s.indexOf("*");
			boolean trip = false;
			boolean found = false;
			boolean negativeNum = false;
			int count = 0;
			//check if previous numbers are digits or if it a messed up '+' if it is, remove it.
			while(!trip)
			{
				//Checks to make sure there isn't negative indices.
				count++;
				if(index1 - count >= 0)
				{
					//Decreases the index before checking.
					//Checks if the previous number is an integer.
					if(checkIsInteger(s, index1 - count))
					{
						//If it is, the '+' is valid but it does not trip to check if it
						//a double digit or has more than 1 character, example 8888+3.
						found = true;
					}
					else
					{
						//If it isn't a digit, then if it has been found, it must be the end
						//of the function or there are two functions next to each other
						//if it is not found but must be tripped.
						if(index1 - count - 1 >= 0)
						{
							//Checks *-
							if(s.charAt(index1-count) == '-' && checkIsOperator(s, index1-count-1))
							{
								negativeNum = true;
							}
						}
						else if(index1 - count - 1 < 0)
						{
							if(s.charAt(index1-count) == '-')
							{
								negativeNum = true;
							}
						}
						trip = true;
					}
				}
				//If there isn't, it didn't find an integer and must be an invalid '+'.
				else
				{
					trip = true;
				}
			}
			//Removes the invalid '+' if it is invalid and continues looking for more '+'.
			if(trip == true && found == false)
			{
				return exponents(s.substring(0, index2) + s.substring(index2+1));
			}
			//Else it must be a valid '+' so far to the left, time to check if it is valid
			//to the right.
			else
			{
				int index3 = index2;
				boolean trip2 = false;
				boolean found2 = false;
				boolean firstRight = false;
				int count2 = 0;
				//check if next numbers are digits or if it a messed up '+' if it is, remove it.
				while(!trip2)
				{
					//Makes sure the function does not exceed the string's length.
					count2++;
					if(index3 + count2 <= s.length()-1)
					{
						//Checks if next character is an integer.
						if(checkIsInteger(s, index3+count2) || (s.charAt(index3+count2) == '-' && firstRight == false))
						{
							if(s.charAt(index3+count2) == '-' && firstRight == false)
							{
								firstRight = true;

							}
							else
							{
								firstRight = true;
								found2 = true;
							}
							//If it is, it must be a valid '+' but does not trip the equation
							//since it could be multiple digits, example 1+88888;
						}
						else
						{
							//It trips the loop if the trailing index is not an integer
							//because '+' is either invalid or is the end of the
							//exponent function.
							trip2 = true;
						}
					}
					//If it does, it trips the loop because it must be at the end of the string.
					else
					{
						trip2 = true;
					}
				}
				//If the loop was tripped and nothing was found then it must be an invalid '+'
				//so it must be removed and continues looking for exponential functions.
				if(trip2 == true && found2 == false)
				{
					return exponents(s.substring(0, index2) + s.substring(index2+1));
				}
				//Else it must be a valid '+' with valid numbers and does the exponential function.
				else
				{
					//Readjust Count.
					if(!negativeNum)
					count--;
					//The formula is a+b = c so get the integers for the equation.
					double a = convertSubstringToInt(s, index1-count, index2);
					double b = convertSubstringToInt(s, index2+1, index3+count2);
					double c = a*b;
					//a + (b) + c is a general parenthesis equation.
					String d = s.substring(0, index1-count);
					String e = String.valueOf(c);
					String f = s.substring(index3+count2);
					
					//Makes sure that a #*e exists so it isn't #e.
					if(d.length() != 0)
					if(checkIsInteger(d, d.length()-1))
					{
						d = d + "*";
					}
					//Makes sure that a e*# exists so it isn't e#.
					if(f.length() != 0)
					if(checkIsInteger(f, f.length()-1))
					{
						f = "*" + f;
					}
					//Creates the new equation with the modifications.
					String newEquation =  d + e + f;
					//Continues to check for more multiplication equations.
					return multiplication(newEquation);
				}
			}	
		}
		//If it doesn't go straight to division.
		else
		{
			multiplication = true;
			return division(s);
		}

	}
	
	private static String division(String s)
	{
		//Checks if the function has a division function.
				if(s.contains("/"))
				{
					int index1 = s.indexOf("/");
					int index2 = s.indexOf("/");
					boolean trip = false;
					boolean found = false;
					int count = 0;
					//check if previous numbers are digits or if it a messed up '+' if it is, remove it.
					while(!trip)
					{
						//Checks to make sure there isn't negative indices.
						count++;
						if(index1 - count >= 0)
						{
							//Decreases the index before checking.
							//Checks if the previous number is an integer.
							if(checkIsInteger(s, index1 - count))
							{
								//If it is, the '+' is valid but it does not trip to check if it
								//a double digit or has more than 1 character, example 8888+3.
								found = true;
							}
							else
							{
								//If it isn't a digit, then if it has been found, it must be the end
								//of the function or there are two functions next to each other
								//if it is not found but must be tripped.
								trip = true;
							}
						}
						//If there isn't, it didn't find an integer and must be an invalid '+'.
						else
						{
							trip = true;
						}
					}
					//Removes the invalid '+' if it is invalid and continues looking for more '+'.
					if(trip == true && found == false)
					{
						return exponents(s.substring(0, index2) + s.substring(index2+1));
					}
					//Else it must be a valid '+' so far to the left, time to check if it is valid
					//to the right.
					else
					{
						int index3 = index2;
						boolean trip2 = false;
						boolean found2 = false;
						int count2 = 0;
						//check if next numbers are digits or if it a messed up '+' if it is, remove it.
						while(!trip2)
						{
							//Makes sure the function does not exceed the string's length.
							count2++;
							if(index3 + count2 <= s.length()-1)
							{
								//Checks if next character is an integer.
								if(checkIsInteger(s, index3+count2))
								{
									//If it is, it must be a valid '+' but does not trip the equation
									//since it could be multiple digits, example 1+88888;
									found2 = true;
								}
								else
								{
									//It trips the loop if the trailing index is not an integer
									//because '+' is either invalid or is the end of the
									//exponent function.
									trip2 = true;
								}
							}
							//If it does, it trips the loop because it must be at the end of the string.
							else
							{
								trip2 = true;
							}
						}
						//If the loop was tripped and nothing was found then it must be an invalid '+'
						//so it must be removed and continues looking for exponential functions.
						if(trip2 == true && found2 == false)
						{
							return exponents(s.substring(0, index2) + s.substring(index2+1));
						}
						//Else it must be a valid '+' with valid numbers and does the exponential function.
						else
						{
							//Readjust Count.
							count--;
							//The formula is a+b = c so get the integers for the equation.
							double a = convertSubstringToInt(s, index1-count, index2);
							double b = convertSubstringToInt(s, index2+1, index3+count2);
							double c = a/b;
							//a + (b) + c is a general parenthesis equation.
							String d = s.substring(0, index1-count);
							String e = String.valueOf(c);
							String f = s.substring(index3+count2);
							
							//Makes sure that a #*e exists so it isn't #e.
							if(d.length() != 0)
							if(checkIsInteger(d, d.length()-1) && d.charAt(index1-count) != '-')
							{
								d = d + "*";
							}
							//Makes sure that a e*# exists so it isn't e#.
							if(f.length() != 0)
							if(checkIsInteger(f, f.length()-1) && f.charAt(0) != '-')
							{
								f = "*" + f;
							}
							//Creates the new equation with the modifications.
							String newEquation =  d + e + f;
							//Continues to check for more multiplication equations.
							return multiplication(newEquation);
						}
					}	
				}
				//If it doesn't go straight to addition.
				else
				{
					division = true;
					return addition(s);
				}
		
	}
	
	private static String addition(String s)
	{
		//Checks if the function has an addition function.
				if(s.contains("+"))
				{
					int index1 = s.indexOf("+");
					int index2 = s.indexOf("+");
					boolean trip = false;
					boolean found = false;
					int count = 0;
					//check if previous numbers are digits or if it a messed up '+' if it is, remove it.
					while(!trip)
					{
						//Checks to make sure there isn't negative indices.
						count++;
						if(index1 - count >= 0)
						{
							//Decreases the index before checking.
							//Checks if the previous number is an integer.
							if(checkIsInteger(s, index1 - count))
							{
								//If it is, the '+' is valid but it does not trip to check if it
								//a double digit or has more than 1 character, example 8888+3.
								found = true;
							}
							else
							{
								//If it isn't a digit, then if it has been found, it must be the end
								//of the function or there are two functions next to each other
								//if it is not found but must be tripped.
								trip = true;
							}
						}
						//If there isn't, it didn't find an integer and must be an invalid '+'.
						else
						{
							trip = true;
						}
					}
					//Removes the invalid '+' if it is invalid and continues looking for more '+'.
					if(trip == true && found == false)
					{
						return exponents(s.substring(0, index2) + s.substring(index2+1));
					}
					//Else it must be a valid '+' so far to the left, time to check if it is valid
					//to the right.
					else
					{
						int index3 = index2;
						boolean trip2 = false;
						boolean found2 = false;
						int count2 = 0;
						//check if next numbers are digits or if it a messed up '+' if it is, remove it.
						while(!trip2)
						{
							//Makes sure the function does not exceed the string's length.
							count2++;
							if(index3 + count2 <= s.length()-1)
							{
								//Checks if next character is an integer.
								if(checkIsInteger(s, index3+count2))
								{
									//If it is, it must be a valid '+' but does not trip the equation
									//since it could be multiple digits, example 1+88888;
									found2 = true;
								}
								else
								{
									//It trips the loop if the trailing index is not an integer
									//because '+' is either invalid or is the end of the
									//exponent function.
									trip2 = true;
								}
							}
							//If it does, it trips the loop because it must be at the end of the string.
							else
							{
								trip2 = true;
							}
						}
						//If the loop was tripped and nothing was found then it must be an invalid '+'
						//so it must be removed and continues looking for exponential functions.
						if(trip2 == true && found2 == false)
						{
							return exponents(s.substring(0, index2) + s.substring(index2+1));
						}
						//Else it must be a valid '+' with valid numbers and does the exponential function.
						else
						{
							//Readjust Count.
							count--;
							//The formula is a+b = c so get the integers for the equation.
							double a = convertSubstringToInt(s, index1-count, index2);
							double b = convertSubstringToInt(s, index2+1, index3+count2);
							double c = a+b;
							//a + (b) + c is a general parenthesis equation.
							String d = s.substring(0, index1-count);
							String e = String.valueOf(c);
							String f = s.substring(index3+count2);
							
							//Makes sure that a #*e exists so it isn't #e.
							if(d.length() != 0)
							if(checkIsInteger(d, d.length()-1))
							{
								d = d + "*";
							}
							//Makes sure that a e*# exists so it isn't e#.
							if(f.length() != 0)
							if(checkIsInteger(f, f.length()-1))
							{
								f = "*" + f;
							}
							//Creates the new equation with the modifications.
							String newEquation =  d + e + f;
							//Continues to check for more multiplication equations.
							return multiplication(newEquation);
						}
					}	
				}
				//If it doesn't go straight to subtraction.
				else
				{
					addition = true;
					return subtraction(s);
				}
		
	}
	
	private static String subtraction(String s)
	{
		//Checks if the function has a multiplication function.
				if(s.contains("-") && s.indexOf("-") != 0)
				{
					int index1 = s.indexOf("-");
					int index2 = s.indexOf("-");
					boolean trip = false;
					boolean found = false;
					boolean negativeNum = false;
					int count = 0;
					//check if previous numbers are digits or if it a messed up '-' if it is, remove it.
					while(!trip)
					{
						//Checks to make sure there isn't negative indices.
						count++;
						if(index1 - count >= 0)
						{
							//Checks if the previous number is an integer.
							if(checkIsInteger(s, index1 - count))
							{
								//If it is, the '+' is valid but it does not trip to check if it
								//a double digit or has more than 1 character, example 8888-3.
								found = true;
							}
							else
							{
								//If it isn't a digit, then if it has been found, it must be the end
								//of the function or there are two functions next to each other
								//if it is not found but must be tripped.
								if(index1 - count - 1 >= 0)
								{
									if(checkIsOperator(s, index1 - count -1))
									{
										negativeNum = true;
									}
								}
								else if(index1 - count - 1 < 0)
								{
									negativeNum = true;
								}
								trip = true;
							}
						}
						//If there isn't, it didn't find an integer and must be an invalid '-'.
						else
						{
							trip = true;
						}
					}
					//Removes the invalid '-' if it is invalid and continues looking for more '-'.
					if(trip == true && found == false && negativeNum == false)
					{
						if(checkIsInteger(s, index1 - count))
						{
							//If it is, the '+' is valid but it does not trip to check if it
							//a double digit or has more than 1 character, example 8888-3.
							found = true;
						}
						return subtraction(s.substring(0, index2) + s.substring(index2+1));
					}
					//Else it must be a valid '-' so far to the left, time to check if it is valid
					//to the right.
					else
					{
						int index3 = index2;
						boolean trip2 = false;
						boolean found2 = false;
						int count2 = 0;
						//check if next numbers are digits or if it a messed up '-' if it is, remove it.
						while(!trip2)
						{
							//Makes sure the function does not exceed the string's length.
							count2++;
							if(index3 + count2 <= s.length()-1)
							{
								//Checks if next character is an integer.
								if(checkIsInteger(s, index3+count2))
								{
									//If it is, it must be a valid '-' but does not trip the equation
									//since it could be multiple digits, example 1-88888;
									found2 = true;
								}
								else
								{
									//It trips the loop if the trailing index is not an integer
									//because '-' is either invalid or is the end of the
									//exponent function.
									trip2 = true;
								}
							}
							//If it does, it trips the loop because it must be at the end of the string.
							else
							{
								trip2 = true;
							}
						}
						//If the loop was tripped and nothing was found then it must be an invalid '-'
						//so it must be removed and continues looking for exponential functions.
						if(trip2 == true && found2 == false)
						{
							return addition(s.substring(0, index2) + s.substring(index2+1));
						}
						//Else it must be a valid '-' with valid numbers and does the exponential function.
						else
						{
							//Readjust Count.
							count--;
							//The formula is a+b = c so get the integers for the equation.
							double a = convertSubstringToInt(s, index1-count, index2);
							double b = convertSubstringToInt(s, index2+1, index3+count2);
							double c = a-b;
							//a + (b) + c is a general parenthesis equation.
							String d = s.substring(0, index1-count);
							String e = String.valueOf(c);
							String f = s.substring(index3+count2);
							
							//Makes sure that a #*e exists so it isn't #e.
							if(d.length() != 0)
							if(checkIsInteger(d, d.length()-1))
							{
								d = d + "*";
							}
							//Makes sure that a e*# exists so it isn't e#.
							if(f.length() != 0)
							if(checkIsInteger(f, f.length()-1))
							{
								f = "*" + f;
							}
							//Creates the new equation with the modifications.
							String newEquation =  d + e + f;
							//Continues to check for more multiplication equations.
							return multiplication(newEquation);
						}
					}		
				}
				//If it doesn't return final value.
				else
				{
					subtraction = true;
					return s;
				}
		
	}
	
	private static boolean checkIsInteger(String s, int index)
	{
		if(s.charAt(index) == '0' || s.charAt(index) == '1' || s.charAt(index) == '2' || s.charAt(index) == '3' || s.charAt(index) == '4' || s.charAt(index) == '5' || s.charAt(index) == '6' || s.charAt(index) == '6' || s.charAt(index) == '7' || s.charAt(index) == '8' || s.charAt(index) == '9' || s.charAt(index) == '.')
			return true;
		else
			return false;
	}
	
	private static boolean checkIsOperator(String s, int index)
	{
		if (s.charAt(index) == '+' || s.charAt(index) == '-' || s.charAt(index) == '*' || s.charAt(index) == '/') 
		{
		    return true;
		}
		else
		{
			return false;
		}
	}
	
	private static boolean checkIsParenthesis(String s, int index)
	{
		if(s.charAt(index) == '(' || s.charAt(index) == ')')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static double convertSubstringToInt(String s, int index1, int index2)
	{
		return Double.parseDouble(s.substring(index1, index2));
	}
	
	
	
	

}
