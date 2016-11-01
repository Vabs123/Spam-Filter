package welcome;

import gui.Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Welcome extends JFrame{
	Welcome(String s){
		super(s);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("A://algo project//laststep//Images//welcome.jpg"));
		add(background);
		setSize(600,600);
		setLocation(new java.awt.Point(400, 75));
		setVisible(true);
		try{
			Thread.sleep(3000);
		}
		catch(Exception e){}
		new Gui("SPAM FILTER");
		
		this.dispose();
}

public static void main(String[] args) {
	 try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	new Welcome("SPAM FILTER");
}
}