import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client
{
	public static void displayMenu()
	{
	    System.out.println();
	    System.out.println("1. Display the names of all known users.");
	    System.out.println("2. Display the names of all currently connected users.");
	    System.out.println("3. Send a text message to a particular user.");
	    System.out.println("4. Send a text message to all currently connected users.");
	    System.out.println("5. Send a text message to all known users.");
	    System.out.println("6. Get my Messages.");
	    System.out.println("7. Exit.");
	    //System.out.println("//////////////////////////////");

	}
    public static void main(String args[]) throws IOException
    {
	int port = Integer.parseInt(args[1]);//3010;
	String addr = args[0]; //"cs1.utdallas.edu";
	System.out.println();
	System.out.println("Connecting to "+args[0]+":"+args[1]);
	Socket client = new Socket(addr, port);
	InputStreamReader inputStream = new InputStreamReader(client.getInputStream());

	BufferedReader input = new BufferedReader(inputStream);
	PrintStream print = new PrintStream(client.getOutputStream());
	String nextLine = input.readLine();
	//System.out.println(nextLine);
	boolean doName = true;
	Scanner scan = new Scanner(System.in);
	String name = "";
	while(doName)
	{
	    System.out.println();
	    System.out.print("Enter your name: ");
	    
	    name = scan.nextLine();
	    print.println(name);
	    print.flush();
	    doName = Boolean.parseBoolean(input.readLine());
	    if (doName)
	    {
		System.out.println();
		System.out.println(name+" is already connected. Choose another name.");
	    }
		
	}
	boolean exit = false;
	label:
	while(!exit)
	{
	    //System.out.println("//////////////////////////////");
	    //System.out.println("//The Following numbers are selectable Menu Choices");
	    displayMenu();
	    System.out.print("Enter your choice: ");
	    String choiceStr = scan.nextLine();
	    print.println(choiceStr);
	    print.flush();
	    System.out.println();
	   int choice;

	   while (true)
	   {
	   	try
	    	{
	    		choice = Integer.parseInt(choiceStr);
			break;
	    	}
	    	catch(NumberFormatException e)
	   	{
			System.out.println("Error: Menu choice is not a digit, please try again");
			//print.println("ERROR: User did not enter a digit for menu choice");
			displayMenu();
			System.out.println("Enter your choice");
			choiceStr = scan.nextLine();
		}
		print.println(choiceStr);
		print.flush();
		System.out.println();
	    }

	    switch(choice)
	    {
	    case 1:
		String count = input.readLine();
		int c = Integer.parseInt(count);
		ArrayList<String> knownUsers = new ArrayList<String>();
		for (int i = 0; i<c; i++)
		{
		    knownUsers.add(input.readLine());
		}
		System.out.println("Known users:");
		for (int i=0; i<c; i++)
		{
		    System.out.println("\t"+(i+1)+".\t"+knownUsers.get(i));
		}
		System.out.println();
		break;
	    case 2:
		String count2 = input.readLine();
		int c2 = Integer.parseInt(count2);
		ArrayList<String> connectedUsers = new ArrayList<String>();
		for (int i = 0; i<c2; i++)
		{
		    connectedUsers.add(input.readLine());
		}
		System.out.println("Connected users:");
		for (int i=0; i<c2; i++)
		{
		    System.out.println("\t"+(i+1)+".\t"+connectedUsers.get(i));
		}
		System.out.println();
		break;
	    case 3:
		System.out.print("Enter recipient's name: ");
		String reciever = scan.nextLine();
		print.println(reciever);
		print.flush();
		System.out.print("Enter a message: ");
		String message = scan.nextLine();
		print.println(message);
		print.flush();
		
		break;
	    case 4:
		System.out.print("Enter a message: ");
		String message2 = scan.nextLine();
		print.println(message2);
		print.flush();
		break;
	    case 5:
		System.out.print("Enter a message: ");
		String message3 = scan.nextLine();
	        print.println(message3);
		print.flush();
		break;
	    case 6:
		String count6 = input.readLine();
		int c6 = Integer.parseInt(count6);
		ArrayList<String> messages = new ArrayList<String>();
		for (int i = 0; i<c6; i++)
		{
		    messages.add(input.readLine());
		}
		System.out.println("Your Messages:");
		for (int i=0; i<c6; i++)
		{
		    System.out.println("\t"+(i+1)+".\t"+messages.get(i));
		}
		System.out.println();
		break;
	    case 7:
		exit = true;
		break;
	    default:
		System.out.println("ERROR: Enter in only Menu numbers 1-7.");
		break;
	    }
	}
	client.close();
    }
}
