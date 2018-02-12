package org.freesofts.blackjack.swingclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
public class game extends JFrame implements Runnable
{
	public static void main (String[] args)
	{
		game game1 = new game("gopi",1000);
		game1.show();
	}

	public game(String player,int amount)
	{

		setTitle(player);
		setSize(600,600);
		addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent ev){
					      dispose();
					      System.exit(0);
    		}
    		});
		hitbutton = new JButton("HIT");
		standbutton = new JButton("STAND");
		doublebutton = new JButton("DOUBLE");
		clear = new JButton("CLEAR");
		exit = new JButton("EXIT");
		bet = new JButton("BET");
		r10 = new JButton("$10");
		r50 = new JButton("$50");
		r100 = new JButton("$100");
		text = new JTextField("0",5);
		hitbutton.isEnabled(false);
		//doublebutton.isEnabled(false);
		//clearbutton.isEnabled(false);
		//r10.isEnabled(false);
		//r50.isEnabled(false);
		//r100.isEnabled(false);
		//text.isEnabled(false);
		this.player=player;
		this.amount=amount;
		//getContentPane().setLayout( new BorderLayout());
		h1.setindex(1);
		h2.setindex(2);
		h3.setindex(3);
		h4.setindex(4);
		getContentPane().add(getdeck(),BorderLayout.NORTH);
		getContentPane().add(getplay(),BorderLayout.CENTER);
		getContentPane().add(getbut(),BorderLayout.SOUTH);
		try
		{
		s = new Socket("127.0.0.1",8739);
		//Thread t=new ThreadEcho(s,this);
		start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		r10.addActionListener(new ActionListener()
		     {
				 public void actionPerformed(ActionEvent evt)
				 {
					int i;
					i=Integer.parseInt(text.getText());
					i=i+10;
					text.setText(String.valueOf(i));
			    }
	 		});
	 	r50.addActionListener(new ActionListener()
	 		     {
	 				 public void actionPerformed(ActionEvent evt)
	 				 {
	 					int i;
	 					i=Integer.parseInt(text.getText());
	 					i=i+50;
	 					text.setText(String.valueOf(i));
	 			    }
	 			});
	 	r100.addActionListener(new ActionListener()
						     {
								 public void actionPerformed(ActionEvent evt)
								 {
									int i;
									i=Integer.parseInt(text.getText());
									i=i+100;
									text.setText(String.valueOf(i));
							    }
		});
	 clear.addActionListener(new ActionListener()
	 		     {
	 				 public void actionPerformed(ActionEvent evt)
	 				 {

	 					text.setText("0");
	 			    }
	 });
	 bet.addActionListener(new ActionListener()
	 	 		     {
	 	 				 public void actionPerformed(ActionEvent evt)
	 	 				 {
							abet();

	 	 			    }
	 });
	 exit.addActionListener(new ActionListener()
	 	 	 		     {
	 	 	 				 public void actionPerformed(ActionEvent evt)
	 	 	 				 {
							try{
								stop();
								s.close();
								}
								catch(Exception e)
								{
								}
								dispose();
					      		System.exit(0);
	 	 	 			    }
	 });


	}


public void abet()
{
	int bet=Integer.parseInt(text.getText());
	amount=amount-bet;
	if(bet!=0)
	{
	h.betamount(amount,bet);
	}
}
	public JPanel getbut()
	{
		JPanel p=new JPanel();
		p.add(clear);
		p.add(r10);
		p.add(r50);
		p.add(r100);
		p.add(text);
		p.add(bet);
		p.add(standbutton);
		p.add(hitbutton);
		p.add(standbutton);
		p.add(doublebutton);
		p.add(exit);
		return p;
	}
	public void start()
	{
		runner = new Thread(this);
		runner.start();
	}
	public void stop()
	{
		runner.stop();
	}
	public JPanel getdeck()
	{
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,2,3,3));
		p.add(d1);
		p.add(d2);
		return p;
	}
	public Box getplay()
	{
		Box p;
		p=Box.createHorizontalBox();
		p.add(Box.createRigidArea(new Dimension(50,50)));
		p.add(h1);
		return p;

	}
	public String getplayer()
	{
		return player;
	}
	public int getamount()
	{
		return amount;
	}

	public void run()
		{
			try
				{
					in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out = new PrintWriter(s.getOutputStream());
					while(true)
					{
						line = in.readLine();
						System.out.println(line);
						if (line.startsWith("disable"))
						{
							index=Integer.parseInt(line.substring(7));
							disablesit(index);
						}
						if (line.startsWith("enable"))
						{
							index=Integer.parseInt(line.substring(6));
							enablesit(index);
						}
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}

	}
	private JButton hitbutton;
	private JButton standbutton;
	private JButton doublebutton;
	private JButton bet;
	private JButton clear;
	private JButton exit;
	private JButton max;
	private JButton r50;
	private JButton r10;
	private JButton r100;
	private JLabel image;
	private JLabel space;
	private Icon icon;
	private JTextField text;
	private Thread runner;
	BufferedReader in;
	PrintWriter out;
	String line;
	int index;
	String player;
	int amount;
	int x;
	Socket s;
	deckpanel d1 = new deckpanel();
	dealer d2 = new dealer();
	HandPanel h = new HandPanel(this);
	HandPanel h1 = new HandPanel(this);
	HandPanel h2 = new HandPanel(this);
	HandPanel h3 = new HandPanel(this);
	HandPanel h4 = new HandPanel(this);
	private boolean status=false;
}