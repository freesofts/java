package org.freesofts.blackjack.swingclient.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.freesofts.blackjack.swingclient.controller.Controller;
import org.freesofts.blackjack.swingclient.controller.RegisterController;

import java.lang.String;
import java.io.*;
import java.net.*;

public class RegisterView extends BaseView
{
	private JLabel label1=new JLabel("UserID",JLabel.LEFT);
	private JTextField text = new JTextField("",20);
	private JLabel label2=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd1 = new JPasswordField("",20);
	private JLabel label3=new JLabel("Password",JLabel.LEFT);
	private JPasswordField pwd2 = new JPasswordField("",20);
	private JButton submit = new JButton("SUBMIT");
	private JButton exit = new JButton("EXIT");
	private RegisterController regController = null;
	
	public RegisterView(RegisterController regController)
	{
		this.regController = regController;
		setTitle("REGISTER");
		setSize(400,300);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
		    dispose();
			}
    	});

    	getContentPane().add(format());
		exit.addActionListener(e->dispose());
		submit.addActionListener(e-> this.regController.register(text.getText(),
				new String(pwd1.getPassword()),new String(pwd1.getPassword())));

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
}