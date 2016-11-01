package gui;
import filechooser.FileChooser;
import terms_conditions_box.TermsConditionsBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JFrame{
	public Gui(String s){
		super(s);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//l1.setSize(600,400);
		//l1.add(b1);
		 
		JLabel background = new JLabel(new ImageIcon("A://algo project//laststep//Images//ppp.jpg"));

		add(background);
        setSize(600,600);
        setLocation(new java.awt.Point(400, 75));
        setVisible(true);
		
                TermsConditionsBox dialog = new TermsConditionsBox(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
                if(!dialog.isShowing())
                    this.dispose();
                    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileChooser().setVisible(true);
            }
        });

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	new Gui("SPAM FILTER");
}
}