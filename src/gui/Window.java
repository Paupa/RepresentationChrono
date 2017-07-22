package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 7446192599263749847L;

	public Window() {
		setTitle("Representation chrono");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		ChronoButton women = new ChronoButton("Women");
		ChronoButton diana = new ChronoButton("Diana");
		
		women.addChild(diana);
		
		women.setPosition(158, 55);
		diana.setPosition(75, 155);
		
		women.beAddedTo(mainPanel);
		diana.beAddedTo(mainPanel);
		
		Timer timer = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				women.refreshTime();
				diana.refreshTime();
				
			}
			
		});
		
		timer.start();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
					//JFrame.setDefaultLookAndFeelDecorated(true);
					//JDialog.setDefaultLookAndFeelDecorated(true);
					//SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
					Window w = new Window();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error: " + e);
				}
			}
		});
	}
}
