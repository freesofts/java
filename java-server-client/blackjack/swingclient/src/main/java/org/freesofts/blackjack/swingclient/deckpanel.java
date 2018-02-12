package org.freesofts.blackjack.swingclient;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class deckpanel extends JPanel{

public deckpanel(){
	setLayout(new BorderLayout());
	setBackground(Color.red);
	try{
	    String fn = "./images/B.gif";
		icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(fn));
		}
    catch(Exception ex){
     System.out.println(ex);
     }
    //this.add(dog,BorderLayout.EAST);
  }

 public void paint(Graphics g){
 	    int x=10;
 	    int y=12;
 	    super.paint(g);

 	for(int i=0;i<cardcount;i++)
 	{

 	    icon.paintIcon(this, g, x, y);
 	    x=x+2;
	}
}
public void setCount(int count)
{
	cardcount=count;
}
public void setCount()
{
	cardcount--;
}
int cardcount=52;
private Icon icon;
private Icon icon1 = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("images/rundog.gif"));

private JLabel dog = new JLabel(icon1,JLabel.CENTER);
}