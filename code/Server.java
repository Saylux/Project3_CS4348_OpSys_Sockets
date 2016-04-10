import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Server
{
    public static Date date = new Date();
    public static SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yy, h:mm a, ");
    public static ArrayList<String> knownUsers = new ArrayList<String>();
    public static ArrayList<String> connectedUsers = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
    public static Semaphore sem_add_message = new Semaphore(1, true);
    public class smallServ implements Runnable
    {
	Socket localClient;
	smallServ(Socket cli)
	{
	    localClient = cli;
	}
	
	public void addMessage(String name, String message)
	{
	    try{sem_add_message.acquire();}
	    catch(Exception e){}
	    ////////////////////
	    if(knownUsers.indexOf(name) != -1)
	    {
		getMessageList(name).add(message);
	    }
	    ////////////////////
	    try{sem_add_message.release();}
	    catch(Exception e){}
	}

	public ArrayList<String> getMessageList(String name)
	{
	    return messages.get(knownUsers.indexOf(name));
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
		boolean doName = true;
		String name = "";
		while(doName)
		{
		    doName = false;
		    name = input.readLine();
		    
		    
		    //System.out.println(connectedUsers.indexOf(name));
		    if(connectedUsers.indexOf(name) != -1)
			doName = true;
		    else
		    {
			if(knownUsers.indexOf(name) > -1)
			    System.out.println(ft.format(date)+"Connection by known user "+name + ".");
			else
			    System.out.println(ft.format(date)+"Connection by unknown user "+name + ".");
		    }
		    if(knownUsers.indexOf(name) == -1)
		    {
			messages.add(new ArrayList<String>());
			knownUsers.add(name);
		    }
		    print.println(doName);
		    print.flush();
		    connectedUsers.add(name);
		}
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
			if(knownUsers.indexOf(reciever) == -1)
			{
			    messages.add(new ArrayList<String>());
			    knownUsers.add(reciever);
			}
			String message = input.readLine();
			String fullMessage = "From "+name+", "+ft.format(date)+message;
			
			addMessage(reciever, fullMessage);
			System.out.println(ft.format(date)+name+" posts a message for "+reciever+".");
			break;
		    case 4:
			String message2 = input.readLine();
			String fullMessage2 = "From "+name+", "+ft.format(date)+message2;
			
			for(int i = 0; i< connectedUsers.size(); i++)
			{
			    addMessage(connectedUsers.get(i), fullMessage2);    
			}
			
			System.out.println(ft.format(date)+name+" posts a message for all connected users.");
			break;
		    case 5:
			String message3 = input.readLine();
			String fullMessage3 = "From "+name+", "+ft.format(date)+message3;
			
			for(int i = 0; i< knownUsers.size(); i++)
			{
			    addMessage(knownUsers.get(i), fullMessage3);    
			}
			System.out.println(ft.format(date)+name+" posts a message for all known users.");
			break;
		    case 6:
			//System.out.println("Your Messages:");
			print.println(getMessageList(name).size());
			print.flush();
			
			try{sem_add_message.acquire();}
			catch(Exception e){}

			for (int i = 0; i<getMessageList(name).size(); i++)
			{
			    print.println(getMessageList(name).get(i));
			    print.flush();
			}
			for (int i = getMessageList(name).size()-1; i >= 0; i--)
			{
			    getMessageList(name).remove(i);
			}
			
			try{sem_add_message.release();}
			catch(Exception e){}
			
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