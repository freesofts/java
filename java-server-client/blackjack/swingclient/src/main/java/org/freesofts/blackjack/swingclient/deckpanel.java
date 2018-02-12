package org.freesofts.blackjack.swingclient;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class deckpanel extends JPanel{

  //Hand hand;
  	private Icon icon;

  public deckpanel(){//String playerName){
    //this.playerName.setText(playerName);
    setLayout( new BorderLayout(90,90) );
    int x=10,y=10;
    //status.setBackground(Color.red);
    try{
		       String fn = "./images/B.gif";
		       icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(fn));
		     }
		     catch(Exception ex){
		       System.out.println(ex);
     }
    //add(this.playerName,  BorderLayout.NORTH);

    //add(this.status,  BorderLayout.SOUTH);
    //setBackground(Color.yellow);


  }



 public void paint(Graphics g){
 	    int x=50;
 	    int y=40;
 	    super.paint(g);

 	for(int i=0;i<52;i++)
 	{

 	    icon.paintIcon(this, g, x, y);
 	    x=x+2;
	}
}
}