OBJS = Client.class Server.class;

#run: compile
#	java Server
#	java Client

compile: Client.class Server.class

s: compile
	java Server 3010

c: compile
	java Client cs1 3010

Client.class: Client.java
	javac Client.java

Server.class: Server.java
	javac Server.java

clean:
	rm -f *.class
