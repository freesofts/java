package org.freesofts.blackjack.swingclient.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.freesofts.blackjack.swingclient.controller.TableController;
import org.freesofts.blackjack.swingclient.model.User;

import java.io.*;
import java.net.*;

public class TableView extends JFrame
{
	private JButton exit = new JButton("EXIT");
	private List tableList=new List(5);  
	private User user;
	private TableController controller;
	public TableView(TableController controller)
	{		
		this.controller = controller;
		setTitle("Game tables");
		setSize(800,600);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				dispose();
			    System.exit(0);
		    }
    	});

		getContentPane().add(getlay());
	 	exit.addActionListener(e -> System.exit(0));	
	 	tableList.add("table1");
	 	tableList.add("table2");
	 	tableList.add("table3");
	 	tableList.add("table4");
	}
	public void setUser(User user) {
		this.user = user;
	}
	public JPanel getlay()
	{
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		//GridBagConstraints c = new GridBagConstraints();		
		p.add(tableList);
		p.add(exit);	
		return p;
	}
}