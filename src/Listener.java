import java.net.*;
import java.io.*;


public class Listener implements Runnable {
	private int listenPort;
	ServerSocket listenServerSocket;
	
	public Listener(int newPort){
		listenPort=newPort;
	}
	
	public void run() {
		
		
		try {
			listenServerSocket = new ServerSocket(listenPort);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
		{
			try {
				//System.out.println("I'm listening on: " + listenPort);
				Socket listenSocket = listenServerSocket.accept();	
				BufferedReader in = new BufferedReader(
					    new InputStreamReader(
					    listenSocket.getInputStream()));
				String input;
				if ((input=in.readLine())!=null)
				{
					System.out.println("Cashier: " + parseCompleteOrder(input));
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String parseCompleteOrder(String input){
		String orderCompleted = "Order ";
		orderCompleted = orderCompleted+ input.substring(0,getNextStarPos(input));//order ID
		input = input.substring((getNextStarPos(input)+1),input.length());
		orderCompleted = orderCompleted + " was placed at " + input.substring(0,getNextStarPos(input));// Time Placed
		input = input.substring((getNextStarPos(input)+1),input.length()); 
		String timeCooked =  input.substring(0,getNextStarPos(input));//Time Cooked
		input = input.substring((getNextStarPos(input)+1),input.length());
		orderCompleted = orderCompleted + " by Cashier "+ input.substring(0,getNextStarPos(input));//Cashier
		input = input.substring((getNextStarPos(input)+1),input.length());
		orderCompleted = orderCompleted + " it was completed by Cook " + input;//Cook
		orderCompleted = orderCompleted + " at " + timeCooked;
		return orderCompleted;
	}
	

	public int getNextStarPos(String input){
		for (int i = 0; i<input.length(); i++){
   		 if(input.charAt(i) == '*'){
   			 return i;
   		 }
   	 }
		return 0;
	}
}
