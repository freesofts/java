package org.freesofts.blackjack.swingclient.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.freesofts.blackjack.swingclient.controller.LoginController;

public class LoginView extends BaseView {
	private JLabel label1=new JLabel("UserID",JLabel.LEFT);
	private JTextField text = new JTextField("",20);
	private JLabel label2=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd = new JPasswordField("",20);
	private JButton login = new JButton("LOGIN");
	private JButton register = new JButton("REGISTER");
	private JButton exit = new JButton("EXIT");
	Toolkit tk = Toolkit.getDefaultToolkit();
	GameView g;
	LoginController controller;
	
	public LoginView(LoginController controller)
	{
		this.controller = controller;
		setTitle("BLACK JACK");
		setSize(400,300);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				dispose();
				System.exit(0);
			}
		});

		getContentPane().add(format());
		Image img = tk.getImage("./images/B.gif");
		setIconImage(img);

		exit.addActionListener(e -> System.exit(0));
		login.addActionListener(e -> controller.login(text.getText(), new String(pwd.getPassword())));
		register.addActionListener(e -> controller.register());
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
}
