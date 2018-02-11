package org.freesofts.blackjack.tcpserver;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	public static void main(String[] args)
	{
		System.out.println("SERVER STARTED (PRESS CTRL+C TO STOP SERVER)");
		System.out.println("SERVER IS RUNNING...");
		try
		{
			ServerSocket s = new ServerSocket(8739);
			while(true)
			{
				Socket incoming = s.accept( );
				Thread t=new ThreadEcho(incoming);
				t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class ThreadEcho extends Thread
{
	public ThreadEcho(Socket i)
	{
		this.incoming = i;
	}
	public void run()
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out = new PrintWriter(incoming.getOutputStream(),true);
			Deck d= new Deck();
			while(true){
				line = in.readLine();
				//System.out.println(line);
				if(line==null)
				break;
				if (line.startsWith("userid"))
				{
					login = line.substring(6,line.indexOf(' '));
					pwd = line.substring(line.indexOf(' ')+1);
					int b = u.loginDB(login,pwd);
					if(b==0)
					{
						int amount=u.getAmount(login);
						out.println("success"+amount);
						System.out.println("USER "+login+" LOGGED IN");
					}
					else
						if(b==2)
							out.println("exists");
						else
							if(b==3)
								out.println("password");
							else
							{
								out.println("invalid");
								//System.out.println(b);
							}
				}
				if (line.startsWith("reg"))
				{
					login = line.substring(3,line.indexOf('$'));
					//System.out.println(login);
					pwd = line.substring(line.indexOf('$')+1);
					boolean b = u.register(login,pwd);
					if(b==true)
					{
						out.println("success");
						System.out.println("LOGIN "+login+" SUCCESSFULLY CREATED");
					}
					else
					{
						out.println("invalid");
						System.out.println(b);
					}
				}
				if(line.startsWith("logout"))
				{
					login=line.substring(6,line.indexOf(' '));
					int amount = Integer.parseInt(line.substring(line.indexOf(' ')+1));
					System.out.println("USER "+login+" LOGGED OUT");
					u.setAmount(login,amount);
					u.logout(login);
				}
				if(line.startsWith("startgame"))
				{
					if(d.getCount()<10)
						d.setCount();
					out.println("start"+d.getCount());
				}
				if(line.startsWith("getcard"))
				{
					out.println("card"+d.getCard());
				}
			}
		incoming.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	Socket incoming;
	String line="black";
	String login;
	String pwd;
	Vector v;
	User u=new User();
}