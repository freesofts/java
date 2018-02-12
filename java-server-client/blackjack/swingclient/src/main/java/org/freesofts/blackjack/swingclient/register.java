package org.freesofts.blackjack.swingclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;
import java.io.*;
import java.net.*;

public class register extends JFrame
{
	//public static void main(String args[])
	//{
	//	register reg=new register();
	//	reg.show();
	//}
	public register()
	{

		setTitle("REGISTER");
		setSize(400,300);
//		c.hide();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
		    dispose();
			}
    	});

    	getContentPane().add(format());
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
			dispose();
			}
		});
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				String uname = text.getText();
				String pass1=pwd1.getText();
				String pass2=pwd2.getText();
				if(pass1.equals(pass2))
				{
					socketopen();
					try
							{
								out.println("reg"+uname+'$'+pass1);
								out.flush();
								line=in.readLine();
								if(line.equals("invalid"))
								{
									prin("LOGIN ALREADY EXISTS");
								}
								else
								{
									prin("LOGIN CREATED SUCESSFULLY");
									dispose();
								}
								System.out.println(line);
							}
							catch(Exception e)
							{
								System.out.println(e);
							}
					socketclose();


				}
				else
				prin("YOUR ENTERED PASSWORD AND CONFORM PASSWORD ARE NOT SAME");
			}
		});

	}
	public void prin(String str)
	{
		JOptionPane.showMessageDialog(this,str);
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
					prin("SERVER IS NOT RUNNING");
					dispose();
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
	public Box format()
	{
		Box p;
		p=Box.createVerticalBox();
		p.add(Box.createRigidArea(new Dimension(50,50)));
		p.add(getid());
		p.add(getpwd1());
		p.add(getpwd2());
		p.add(but());
		p.add(Box.createRigidArea(new Dimension(50,50)));
		return p;
	}
	public JPanel getid()
	{
		JPanel p = new JPanel();
		p.add(label1);
		p.add(text);
		return p;
	}
	public JPanel getpwd1()
	{
		JPanel p = new JPanel();
		p.add(label2);
		p.add(pwd1);
		return p;
	}
	public JPanel getpwd2()
		{
			JPanel p = new JPanel();
			p.add(label3);
			p.add(pwd2);
			return p;
	}
	public JPanel but()
	{
		JPanel p = new JPanel();
		p.add(submit);
		p.add(exit);
		return p;
	}
	private JLabel label1=new JLabel("UserID",JLabel.LEFT);
	private JTextField text = new JTextField("",20);
	private JLabel label2=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd1 = new JPasswordField("",20);
	private JLabel label3=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd2 = new JPasswordField("",20);
	private JButton submit = new JButton("SUBMIT");
	private JButton exit = new JButton("EXIT");
	private Socket s;
	private String line;
	BufferedReader in;
	PrintWriter out;
}