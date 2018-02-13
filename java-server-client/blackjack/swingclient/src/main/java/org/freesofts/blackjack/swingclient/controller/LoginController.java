package org.freesofts.blackjack.swingclient.controller;

import org.freesofts.blackjack.swingclient.model.User;
import org.freesofts.blackjack.swingclient.util.ConnectionUtil;
import org.freesofts.blackjack.swingclient.view.GameView;
import org.freesofts.blackjack.swingclient.view.LoginView;
import org.freesofts.blackjack.swingclient.view.RegisterView;

public class LoginController implements Controller {
	private static final String LOGIN_FAIL = "Invalid username/password";
	private static final String UNAME_3 = "Username should be atleast 3 chars";
	private static final String PASS_3 = "Password should be atleast 3 chars";
	private static final String LOGIN_MSG = "userid%s %s";
	private LoginView loginView = null;
	private MainController mainController;
	
	public LoginController(MainController mainController) {
		loginView = new LoginView(this);
		this.mainController = mainController;
	}
	
	public void render(boolean flag) {
		loginView.setVisible(true);
	}
	
	public void login(String username,String password){
		if(username == null || username.length() < 3) {
			loginView.showError(UNAME_3);
		}	
		else if(password == null || password.length() < 3) {
			loginView.showError(PASS_3);
		} else {
			String line=ConnectionUtil.sendAndReceive(String.format(LOGIN_MSG, username, password));
			System.out.println(line);
			if ( line.startsWith("success")) {						
				int amount = Integer.parseInt(line.substring(7));
				User u = new User();
				u.setId(username);
				u.setAmount(amount);
				System.out.println("User login is success");
				mainController.loginSuccess(u);
			} else {
				loginView.showError(LOGIN_FAIL);
			}
		}
	}
	public void register() {
		System.out.println("register");
		mainController.register();
	}
	GameView g;
}



