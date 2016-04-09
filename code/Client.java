import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client
{
    public static void main(String args[]) throws IOException
    {
	int port = Integer.parseInt(args[1]);//3010;
	String addr = args[0]; //"cs1.utdallas.edu";
	System.out.println();
	System.out.println("Connecting to "+args[0]+":"+args[1]);
	Socket client = new Socket(addr, port);
	InputStreamReader inputStream = new InputStreamReader(client.getInputStream());

	BufferedReader input = new BufferedReader(inputStream);
	String nextLine = input.readLine();
	//System.out.println(nextLine);
	System.out.println();
	System.out.print("Enter your name: ");
	Scanner scan = new Scanner(System.in);
	String name = scan.nextLine();
	PrintStream print = new PrintStream(client.getOutputStream());

	print.println(name);
	print.flush();
	boolean exit = false;
	while(!exit)
	{
	    //System.out.println("//////////////////////////////");
	    //System.out.println("//The Following numbers are selectable Menu Choices");
	    System.out.println();
	    System.out.println("1. Display the names of all known users.");
	    System.out.println("2. Display the names of all currently connected users.");
	    System.out.println("3. Send a text message to a particular user.");
	    System.out.println("4. Send a text message to all currently connected users.");
	    System.out.println("5. Send a text message to all known users.");
	    System.out.println("6. Get my Messages.");
	    System.out.println("7. Exit.");
	    //System.out.println("//////////////////////////////");

	    System.out.print("Enter your choice: ");
	    String choiceStr = scan.nextLine();
	    print.println(choiceStr);
	    print.flush();
	    System.out.println();
	    int choice = Integer.parseInt(choiceStr);
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
		System.out.println("Enter recipient's name: ");
		String reciever = scan.nextLine();
		print.println(reciever);
		print.flush();
		System.out.println("Enter a message: ");
		String message = scan.nextLine();
		print.println(message);
		print.flush();
		
		break;
	    case 4:
		break;
	    case 5:
		break;
	    case 6:
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