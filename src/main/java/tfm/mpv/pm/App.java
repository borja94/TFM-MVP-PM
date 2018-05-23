package tfm.mpv.pm;

import tfm.mpv.pm.Views.MenuFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.*;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class App {

	public static void main(String args[]) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuFrame().setVisible(true);
			}
		});
	}
}
