package org.freesofts.blackjack.swingclient.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Card {
	private int number;
	private Icon icon; 
	
	public Card(int number) {
		this.number = number;
		icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("images/"+getFaceSymbol()+getFaceValue()+".gif"));
	}
	
	public char getFaceValue() {
		int faceNumber = number%13 + 1;
		if(faceNumber >= 2 && faceNumber <= 9) {
			return Character.forDigit(faceNumber, 10); 
		}
		if(faceNumber == 10) {
			return 'J';
		}
		if(faceNumber == 11) {
			return 'Q';
		}
		if(faceNumber == 12) {
			return 'K';
		}
		return 'A';
	}
	
	public char getFaceSymbol() {
		int faceSymbol = number%4;
		if(faceSymbol == 0) return 'C';
		if(faceSymbol == 1) return 'D';
		if(faceSymbol == 2) return 'H';
		return 'S';
	}
	
	public Icon getIcon() {
		return icon;
	}
}
