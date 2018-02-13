package org.freesofts.blackjack.swingclient.controller;

import org.freesofts.blackjack.swingclient.util.ConnectionUtil;
import org.freesofts.blackjack.swingclient.view.RegisterView;

public class RegisterController implements Controller {
	public static final String REG_MSG = "reg%s$%s";
	private static final String UNAME_3 = "Username should be atleast 3 chars";
	private static final String PASS_3 = "Password should be atleast 3 chars";
	private static final String CONFPASS_NSAME = "Password and Confirm Password are not same";
	RegisterView registerView;
	MainController mainController;
	
	public RegisterController(MainController mainController) {
		registerView = new RegisterView(this);
		this.mainController = mainController;
	}
	
	public boolean validate(String username, String password, String confirm) {
		if(username == null || username.length() < 3) {
			registerView.showError(UNAME_3);
			return false;
		}	
		if(password == null || password.length() < 3) {
			registerView.showError(PASS_3);
			return false;
		}
		if(!password.equals(confirm)) {
			registerView.showError(CONFPASS_NSAME);
			return false;
		}
		return true;
	}
	public void register(String username, String password, String confirm) {
		if(!validate(username, password, confirm)) return;
		try
		{
			String line=ConnectionUtil.sendAndReceive(String.format(REG_MSG, username, password));
			if(line.equals("invalid"))
			{
				registerView.showError("LOGIN ALREADY EXISTS");
			}
			else
			{
				registerView.showError("LOGIN CREATED SUCESSFULLY");
			}
			System.out.println(line);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void render(boolean flag) {
		registerView.setVisible(flag);
	}
}
