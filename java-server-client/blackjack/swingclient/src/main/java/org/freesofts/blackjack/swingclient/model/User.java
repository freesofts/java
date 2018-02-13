package org.freesofts.blackjack.swingclient.model;

public class User {
	private String userId;
	private int amount;
	
	public void setId(String userId) {
		this.userId = userId;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getId() {
		return userId;
	}
	
	public int getAmount() {
		return amount;
	}
}
