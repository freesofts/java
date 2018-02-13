package org.freesofts.blackjack.swingclient;
import org.freesofts.blackjack.swingclient.controller.MainController;
import org.freesofts.blackjack.swingclient.util.ConnectionUtil;

public class Client
{
	public static void main(String args[])
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            ConnectionUtil.closeAll();
	        }
	    }, "Shutdown-thread"));
		MainController controller = new MainController();
		controller.render(true);
	}
}


