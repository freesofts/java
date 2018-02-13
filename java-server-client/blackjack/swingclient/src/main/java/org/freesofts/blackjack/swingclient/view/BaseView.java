package org.freesofts.blackjack.swingclient.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BaseView extends JFrame {
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this,msg);
	}
}
