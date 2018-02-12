package org.freesofts.blackjack.swingclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class table extends JFrame implements Runnable
{
	JTextArea text1 = new JTextArea(30,15);
	JScrollPane scroll1 = new JScrollPane(text1);
	JButton exit = new JButton("EXIT");
	Socket s;
	Thread t;
	BufferedReader in;
	PrintWriter out;
	String pl;
	int card,cardcount,amount,betamount=0;
	boolean blackjack=false;
	public table(String name,int amount)
	{
		setTitle(name);
		setSize(800,600);
		this.pl=name;
		this.amount=amount;
		t=new Thread(this);
		socketopen();
		t.start();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				logout(pl);
				dispose();
			    System.exit(0);
		    }
    	});

		getContentPane().add(getlay());
	 	exit.addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent evt)
			 {
				logout(pl);
				dispose();
			    System.exit(0);
			 }
		});
	}
	public void run()
	{
		System.out.println("hello");
		//while(1)
		{
			//in.ReadLine();
		}
	}
	final public void logout(String p)
	{
		out.println("logout"+p+' '+amount);
		out.flush();
		socketclose();
	}
	public JPanel getlay()
	{
		JPanel p=new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		game g = new game(pl,amount);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		p.add(g, c);

		JTextArea text2 = new JTextArea(5,50);
		JScrollPane scroll2 = new JScrollPane(text2);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		p.add(scroll2, c);

		JTextField text3 = new JTextField(43);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		p.add(text3, c);

		JButton send = new JButton("SEND");
		c.gridx=1;
		c.gridy=2;
		c.anchor = GridBagConstraints.LINE_END;
		p.add(send,c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		p.add(scroll1, c);
		return p;
	}
	public void socketopen()
	{
		try
		{
			s = new Socket("127.0.0.1",8739);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
		}
		catch(ConnectException e)
		{
			JOptionPane.showMessageDialog(this,"SERVER NOT STARTED");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void socketclose()
		{
			try
			{
				s.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}	
}