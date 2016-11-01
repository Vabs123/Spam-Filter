package ham_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HamWindow extends JFrame{
	public HamWindow(String s){
		super(s);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//l1.setSize(600,400);
		//l1.add(b1);
		 
		JLabel background = new JLabel(new ImageIcon("A://algo project//laststep//Images//hamImg.png"));

		add(background);
        setSize(900,700);
        setLocation(new java.awt.Point(248,45));
        setVisible(true);

		//background.add(b1);
		
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
            java.util.logging.Logger.getLogger(HamWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HamWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HamWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HamWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	new HamWindow("HAM");
}
}