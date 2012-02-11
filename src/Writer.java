import java.net.*;
import java.io.*;

public class Writer{

	private Socket writerSocket;
	private PrintStream out;
	private BufferedReader in;
	
	public Writer(InetAddress a, int port){
	    try {
	    	writerSocket = new Socket(a, port);
	    	out = new PrintStream(writerSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(writerSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void report(String details) {
		out.print(details + "\n");
	}
	
	
}
