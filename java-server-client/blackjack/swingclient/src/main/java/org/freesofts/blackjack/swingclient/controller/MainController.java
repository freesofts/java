package org.freesofts.blackjack.swingclient.controller;

import org.freesofts.blackjack.swingclient.model.User;

public class MainController implements Controller {
	Controller loginController;
	Controller registerController;
	TableController tableController;
	User user = null;
	
	public MainController() {
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
		tableController = new TableController(this);
	}
	
	public void login() {
		loginController.render(true);
		registerController.render(false);
		tableController.render(false);
	}
	
	public void register() {
		loginController.render(false);
		registerController.render(true);
		tableController.render(false);
	}
	
	public void loginSuccess(User user) {
		this.user = user;
		loginController.render(false);
		registerController.render(false);
		tableController.updateUser(user);
		tableController.render(true);
	}
	
	public void render(boolean flag) {
		//login();
		loginSuccess(null);
	}
}
