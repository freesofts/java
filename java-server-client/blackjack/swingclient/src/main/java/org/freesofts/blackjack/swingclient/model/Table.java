package org.freesofts.blackjack.swingclient.model;

import java.util.HashMap;;

public class Table {
	private String tableName;
	private User user;
	private Dealer dealer = new Dealer();
	private Deck deck = new Deck();
	private HashMap<String,Player> players = new HashMap<String,Player>();
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void addPlayer(Player player) {
		players.put(player.getName(),player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player.getName());
	}
	
	public Dealer getDealer() {
		return dealer;
	}
	
	public Card getNextCard() {
		return deck.pull();
	}
}
