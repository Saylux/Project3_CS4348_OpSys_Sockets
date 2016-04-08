import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class Server
{
    public static ArrayList connectedUsers = new ArrayList();
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
			System.out.println("ERROR: User gave bad entry (Not 1-7)");
			break;
		    }
		}
		//localClient.close();
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