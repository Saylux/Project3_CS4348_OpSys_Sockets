import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Server
{
    public static Date date = new Date();
    public static SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yy, h:mm a, ");
    public static ArrayList<String> knownUsers = new ArrayList<String>();
    public static ArrayList<String> connectedUsers = new ArrayList<String>();
    public static ArrayList<String[]> messages = new ArrayList<String[]>();
    public class smallServ implements Runnable
    {
	Socket localClient;
	smallServ(Socket cli)
	{
	    localClient = cli;
	}
	
	public void run()
	{
	    try
	    {
		InputStreamReader inputStream = new InputStreamReader(localClient.getInputStream());
		BufferedReader input = new BufferedReader(inputStream);
		PrintStream print = new PrintStream(localClient.getOutputStream());
	
		print.println("Server is Responding");
		print.flush();
	
		String name = input.readLine();
		if(knownUsers.indexOf(name) > -1)
		    System.out.println(ft.format(date)+"Connection by known user "+name + ".");
		else
		    System.out.println(ft.format(date)+"Connection by unknown user "+name + ".");
		knownUsers.add(name);
		connectedUsers.add(name);
		boolean exit = false;
		while(!exit)
		{
		    int choice = 0;
		    try
		    {
			choice = Integer.parseInt(input.readLine().trim());
		    }
		    catch(NumberFormatException e){}
		    //System.out.println(choice);
		    switch (choice)
		    {
		    case 1:
			System.out.println(ft.format(date)+name+" displays all known users.");
			print.println(knownUsers.size());
			print.flush();
			for(int i = 0; i<knownUsers.size(); i++)
			{
			    print.println(knownUsers.get(i));
			}
			break;
		    case 2:
			System.out.println(ft.format(date)+name+" displays all connected users.");
			print.println(connectedUsers.size());
			print.flush();
			for(int i = 0; i<connectedUsers.size(); i++)
			{
			    print.println(connectedUsers.get(i));
			}
			break;
		    case 3:
			String reciever = input.readLine();
			String message = input.readLine();
			String fullMessage = "From "+name+", "+ft.format(date)+message;
			System.out.println(ft.format(date)+name+" posts a message for "+reciever+".");
			break;
		    case 4:
			System.out.println(ft.format(date)+name+" posts a message for all connected users.");
			break;
		    case 5:
			System.out.println(ft.format(date)+name+" posts a message for all known users.");
			break;
		    case 6:
			System.out.println(ft.format(date)+name+" gets messages.");
			break;
		    case 7:
			System.out.println(ft.format(date)+name+" exits");
			exit = true;
			break;
		    default:
			System.out.println("ERROR: User gave bad entry (Not 1-7)");
			break;
		    }
		}
		//localClient.close();
		connectedUsers.remove(name);
	    }
	    catch(IOException e){}
	    
	}
	
    }
    public static void main(String args[]) throws IOException
    {
	int port = Integer.parseInt(args[0]);
	//String addr = "cs1.utdallas.edu";
	//String name = "";
	Server s = new Server();
	ServerSocket server = new ServerSocket(port);
	while(true)
	{
	    Socket client = server.accept();
	
	    smallServ serverInstance = s.new smallServ(client);
	    Thread localServ = new Thread(serverInstance);
	    localServ.start();
	}
	    /*InputStreamReader inputStream = new InputStreamReader(client.getInputStream());
	BufferedReader input = new BufferedReader(inputStream);
	PrintStream print = new PrintStream(client.getOutputStream());
	
	print.println("Server is Responding");
	print.flush();
	
	name = input.readLine();
       	
	String choice = input.readLine();
	System.out.println(choice);
	client.close();
	*/
    }
}