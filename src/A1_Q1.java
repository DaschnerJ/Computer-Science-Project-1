import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class A1_Q1 {

	//List to store each word to.
	private static List<String> sList = new ArrayList<String>();
	//List to store each word with a count that it appears in the list.
	private static HashMap<String, Integer> sSplit = new HashMap<String, Integer>();
	//For input.
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		//Collect user's information.
		collectInformation();
		//Split the words up and count.
		splitLines(sList);
		//Output the information.
		outPutInfo();
	}
	
	//Output the info.
	private static void outPutInfo()
	{
		//For every counted key set, output the info with amount per line and a space.
		for(String s : sSplit.keySet())
		{
			System.out.println(s + " " + sSplit.get(s));
		}
	}
	
	//Grab the user's words.
	private static void collectInformation()
	{
		//Trip to stop grabbing words.
		boolean trip = false;
		//Loop for the grabbing words.
		while(!trip)
		{ 	
			  //String that the user enters.
			  String s;
			  //Prompt user.
		      System.out.println("Enter a string or 'stop123' to stop:");
		      //Grab that line!
		      s = in.nextLine();
		      //If this is typed, this is to stop and set off the trip.
		      if(s.equalsIgnoreCase("stop123"))
		      {
		    	  trip = true;
		      }
		      //Else it has not been tripped and should be added to the list.
		      else
		      {
		    	  sList.add(s);
		      }
		      //Repeats back what was typed in.
		      System.out.println("Entered:"+s);
		}
	}
	
	//Splits the lines and stores and counts them in the hash map.
	private static void splitLines(List<String> list)
	{
		//Go through each line.
		for(String s : sList)
		{
			splitLine(s);
		}
	}
	
	//Splits the white spaces and counts the words.
	private static void splitLine(String line)
	{
		//Split white spaces.
		String[] sNewSplit = line.split(" ");
		//Iterate through each.
		for(int i = 0; i < sNewSplit.length; i++)
		{
			//If it exists, increase count in hashmap.
			if(sSplit.containsKey(sNewSplit[i]))
			{
				sSplit.put(sNewSplit[i], sSplit.get(sNewSplit[i])+1);
			}
			//If it doesn't, add it to the hashmap.
			else
			{
				sSplit.put(sNewSplit[i], 1);
			}
		}
	}

}
