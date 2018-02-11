package org.freesofts.blackjack.tcpserver;
import java.util.*;
public class Deck
{
	private static Random r;

	  static{
	    Deck.r = new Random();
  }
	public Deck()
	{
		for(int i=0;i<52;i++)
		cards[i]=i;
		shuffle();
	}
	/* returns an int from 0 to 51 */
	  private int getRandomCardIndex(){
	    return Deck.r.nextInt(52);
	  }

	public void shuffle()
	{
		for(int i=0;i<10000;i++)
		{
			int index1 = this.getRandomCardIndex();
    		int index2 = this.getRandomCardIndex();
    		int temp;
    		temp=cards[index1];
    		cards[index1]=cards[index2];
    		cards[index2]=temp;
    	}

	}
	public int getCard()
	{
		count++;
		return cards[count-1];
	}
	public int getCount()
	{
		return(52-count);
	}
	public void setCount()
	{
		count = 0;
		shuffle();
	}
	int count=0;
	int[] cards = new int[52];
}