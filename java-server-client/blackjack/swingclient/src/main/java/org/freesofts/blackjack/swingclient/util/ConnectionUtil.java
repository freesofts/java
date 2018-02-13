package org.freesofts.blackjack.swingclient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectionUtil {
	private static Socket s;
	private static BufferedReader reader;
	private static PrintWriter writer;
	
	static { 
		try {
			s = new Socket("127.0.0.1",8739);
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new PrintWriter(s.getOutputStream());
		}
		catch(ConnectException e) {
			e.printStackTrace();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized String sendAndReceive(String msg) {		
		try {
			writer.println(msg);
			writer.flush();
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static void closeAll() {
		try {
			reader.close();
			writer.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
