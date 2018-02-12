package org.freesofts.blackjack.swingclient;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class dealer extends JPanel{

	//private JButton sitbutton;
  	Label status = new Label("RADHA");
	Label playerName = new Label(" ");
	private Icon icon;
	private int x,y;
  public dealer(){//String playerName){
    //this.playerName.setText(playerName);
     setLayout( new BorderLayout(150,200) );
    add(this.playerName,  BorderLayout.NORTH);
    //sitbutton = new JButton("SIT");
    //add(sitbutton,  BorderLayout.SOUTH);
    //setBackground(Color.red);
    try{

	       String fn = "./images/B.gif";
	       icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(fn));
	     }
	     catch(Exception ex){
	       System.out.println(ex);
     }

  }



  public void paint(Graphics g){
    int x=50;
    int y=50;
    super.paint(g);

        icon.paintIcon(this, g, x, y);

  }

}