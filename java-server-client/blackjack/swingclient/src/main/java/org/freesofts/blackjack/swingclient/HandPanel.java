package org.freesofts.blackjack.swingclient;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class HandPanel extends JPanel implements ActionListener {

public HandPanel(game g)
{

    this.g = g;
	setLayout(new BorderLayout());
    this.add(this.space, BorderLayout.NORTH);
    this.add(but(),BorderLayout.SOUTH);
    try
    {
		String fn = "./images/ck.gif";
		icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(fn));
	}
	catch(Exception ex)
	{
		System.out.println(ex);
    }
    sitbutton.addActionListener(this);
}

public void actionPerformed(ActionEvent evt)
{
	if(sitbutton.getText()!="STAND")
	{
		playerName.setText(g.getplayer());
		sitbutton.setText("STAND");
//		space.setText("$"+String.valueOf(g.getamount()));
//		isSit = false;
//		g.setsit(this,getindex());
	}
	else
	{
		playerName.setText(" ");

		sitbutton.setText("SIT       ");
		space.setText(" ");
		bet.setText(" ");
		isSit = true;
		//g.setstand(this,getindex());
	}
}



public void paint(Graphics g)
{
    int x=10;
    int y=0;
    super.paint(g);
    icon.paintIcon(this, g, x, y);
	y=y+15;
}

public JPanel but()
{
	JPanel p=new JPanel();
	p.setLayout(new GridLayout(4,1,5,5));
	sitbutton = new JButton("SIT       ");
	p.add(bet);
	p.add(playerName);
	p.add(sitbutton);
	p.add(space);
	return p;
}
public void betamount(int a,int b)
{
	bet.setText("BET : $"+String.valueOf(b));
	space.setText("$"+String.valueOf(a));
}
public void setindex(int index)
{
	this.handle=index;
}
public int getindex()
{
	return handle;
}

private game g;
private boolean isSit=true;
private JButton sitbutton;
Label playerName = new Label(" ");
Label bet = new Label(" ");
Label space = new Label(" ");
private Icon icon;
private int handle;

}