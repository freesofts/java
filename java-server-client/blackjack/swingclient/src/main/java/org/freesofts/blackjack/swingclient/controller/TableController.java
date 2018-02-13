package org.freesofts.blackjack.swingclient.controller;

import org.freesofts.blackjack.swingclient.model.User;
import org.freesofts.blackjack.swingclient.view.TableView;

public class TableController implements Controller {
	private MainController controller;
	private TableView tableView;
	
	public TableController(MainController controller) {
		this.controller = controller;
		tableView = new TableView(this);
	}
	
	public void updateUser(User user) {
		tableView.setUser(user);
	}
	
	@Override
	public void render(boolean flag) {
		tableView.setVisible(flag);
	}

}
