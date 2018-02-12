package org.freesofts.blackjack.swingclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;
import java.io.*;
import java.net.*;

public class client extends JFrame
{
	public static void main(String args[])
	{
		client client1=new client();
		client1.show();


	}
	public client()
	{

		setTitle("BLACK JACK");
		setSize(400,300);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
		    dispose();
		    System.exit(0);
			}
    	});

    	getContentPane().add(format());//design  of client window
    	Image img = tk.getImage("./images/B.gif");
    	setIconImage(img);

		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
			dispose();
			System.exit(0);
			}
		});
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				String uname = text.getText();
				String upwd = pwd.getText();
				socketopen();
				if((uname.length()!=0)&&(upwd.length()!=0))
				login(uname,upwd);
			}
		});
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{

				r.show();
			}
		});

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
	public Box format()
	{
		Box p;
		p=Box.createVerticalBox();
		p.add(Box.createRigidArea(new Dimension(50,50)));
		p.add(getid());
		p.add(getpwd());
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
	public JPanel getpwd()
	{
		JPanel p = new JPanel();
		p.add(label2);
		p.add(pwd);
		return p;
	}
	public JPanel but()
	{
		JPanel p = new JPanel();
		p.add(login);
		p.add(register);
		p.add(exit);
		return p;
	}
	public void login(String uname,String upwd)
		{
			uname=uname;
			upwd=upwd;
			try
			{
				out.println("userid"+uname+' '+upwd);
				out.flush();
				line=in.readLine();
				if (line.startsWith("success"))
				{
					int amount = Integer.parseInt(line.substring(7));
					g = new table(uname,amount);
					socketclose();
					this.hide();
					g.show();
				}
				else {
					JOptionPane.showMessageDialog(this,"Invalid Username/Password");
					socketclose();
				}

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
	private JLabel label1=new JLabel("UserID",JLabel.LEFT);
	private JTextField text = new JTextField("",20);
	private JLabel label2=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd = new JPasswordField("",20);
	private JButton login = new JButton("LOGIN");
	private JButton register = new JButton("REGISTER");
	private JButton exit = new JButton("EXIT");
	Toolkit tk = Toolkit.getDefaultToolkit();
	private String line;
	BufferedReader in;
	PrintWriter out;
	table g;
	register r=new register();
	Socket s;
}


