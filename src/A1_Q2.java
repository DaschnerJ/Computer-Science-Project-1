import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A1_Q2 
{

	//Lists to store data
	//Normal list
	private static List<String> sList = new ArrayList<String>();
	//Reversed list
	private static List<String> rList = new ArrayList<String>();
	//Get inputs
	private static Scanner in = new Scanner(System.in);
	//String builder to help with constructing strings
	StringBuilder stringBuilder = new StringBuilder();
	
	public static void main(String[] args) 
	{
		//Collect user's information.
		collectInformation();
		//Reverse each line.
		reverseEachLine();
		//Output each reversed line.
		outPutInfo();
	}
	
	//Output each line from the reverse list.
	private static void outPutInfo()
	{
		//Iterate through each line in the list backwards.
		for(int i = rList.size(); i > 0; i--)
		{
			//Print it out.
			System.out.println(rList.get(i-1));
		}
	}
	
	//Collect the user's information.
	private static void collectInformation()
	{
		//Boolean to trip collection.
		boolean trip = false;
		//Loop for collecting information.
		while(!trip)
		{ 		 
			//String that user enters
			  String s;
			  //Prompt user.
		      System.out.println("Enter a string or 'stop123' to stop:");
		      //
		      s = in.nextLine();
		      if(s.equalsIgnoreCase("stop123"))
		      {
		    	  trip = true;
		      }
		      else
		      {
		    	  sList.add(s);
		      }
		      System.out.println("Entered:"+s);
		}
	}
	
	private static void reverseEachLine()
	{
		for(String s : sList)
		{
			rList.add(reverseLine(s));
		}
	}
	
	private static String reverseLine(String line)
	{
		char[] charLine = line.toCharArray();
		
		for (int i = 0, j = charLine.length - 1; i < j; i++, j--) 
		{
	        char swap = charLine[i];
	        charLine[i] = charLine[j];
	        charLine[j] = swap;
	    }
		
	    return new String(charLine);
		
	}

}
