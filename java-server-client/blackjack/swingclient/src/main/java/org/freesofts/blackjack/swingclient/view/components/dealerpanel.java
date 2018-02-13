package org.freesofts.blackjack.swingclient.view.components;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class dealerpanel extends JPanel{

public dealerpanel()
{
	setLayout(new BorderLayout());
	JLabel player=new JLabel("DEALER");
	this.add(countlabel,BorderLayout.SOUTH);
	this.add(player,BorderLayout.NORTH);
	setBackground(Color.red);
}
public void paint(Graphics g)
{
	x=10;
	y=20;
    super.paint(g);
    for(int i=0;i<count;i++)
    {
	loadIcon(i);
    icon.paintIcon(this, g, x, y);
	x=x+13;
	}
}
public void loadIcon(int i)
{
	fn = "images/"+getFaceSymbol(a[i])+getFaceValue(a[i])+".gif";
	icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(fn));
}
public char getFaceSymbol(int card)
{
	char symbol = 'c';
	if (card/13==0)
		symbol = 'c';
	if (card/13==1)
		symbol = 'd';
	if (card/13==2)
		symbol = 'h';
	if (card/13==3)
		symbol = 's';
	return symbol;
}
public char getFaceValue(int card)
{
	char number='0';
	if(card%13==0)
		number='2';
	if(card%13==1)
		number='3';
	if(card%13==2)
		number='4';
	if(card%13==3)
		number='5';
	if(card%13==4)
		number='6';
	if(card%13==5)
		number='7';
	if(card%13==6)
		number='8';
	if(card%13==7)
		number='9';
	if(card%13==8)
		number='T';
	if(card%13==9)
		number='J';
	if(card%13==10)
		number='Q';
	if(card%13==11)
		number='K';
	if(card%13==12)
		number='A';
	return number;
}
public void add(int card)
{
	a[count]=card;
	count++;
	setLabel();
}
public int getFaceCount()
{
	char number;
	int value=0,acount=0;
	for(int i=0;i<count;i++)
	{
	number = getFaceValue(a[i]);
	if((number=='T')||(number=='J')||(number=='Q')||(number=='K'))
	value+=10;
	else
	if(number=='A')
	acount++;
	else
	value+=((int)(number)-48);
	}
	for(int i=0;i<acount;i++)
		if(value>(11-acount))
			value+=1;
		else
			value+=11;
	return value;
}
public void setLabel()
{
	countlabel.setText("Count : "+getFaceCount());
}
public void setStart()
{
	count=0;
}
int x,y,count=0;
int[] a = new int[10];
private Icon icon;
private int handle;
private JLabel countlabel= new JLabel();
String fn;
}