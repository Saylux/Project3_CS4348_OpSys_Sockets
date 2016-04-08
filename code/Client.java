import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client
{
    public static void main(String args[]) throws IOException
    {
	int port = Integer.parseInt(args[1]);//3010;
	String addr = args[0]; //"cs1.utdallas.edu";
	Socket client = new Socket(addr, port);
	InputStreamReader inputStream = new InputStreamReader(client.getInputStream());

	BufferedReader input = new BufferedReader(inputStream);
	String nextLine = input.readLine();
	//System.out.println(nextLine);
	System.out.print("Enter in your name >> ");
	Scanner scan = new Scanner(System.in);
	String name = scan.nextLine();
	PrintStream print = new PrintStream(client.getOutputStream());

	print.println(name);
	print.flush();
	boolean exit = false;
	while(!exit)
	{
	    System.out.println("//////////////////////////////");
	    System.out.println("//The Following numbers are selectable Menu Choices");
	    System.out.println("1 : Display the names of all known users");
	    System.out.println("2 : Display the names of all currently connected users");
	    System.out.println("3 : Send a text message to a particular user");
	    System.out.println("4 : Send a text message to all currently connected users");
	    System.out.println("5 : Send a text message to all known users");
	    System.out.println("6 : Get my Messages");
	    System.out.println("7 : Exit");
	    System.out.println("//////////////////////////////");

	    System.out.print("Enter in a number above >> ");
	    String choiceStr = scan.nextLine();
	    print.println(choiceStr);
	    print.flush();
	    int choice = Integer.parseInt(choiceStr);
	    switch(choice)
	    {
	    case 1:
		break;
	    case 2:
		break;
	    case 3:
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