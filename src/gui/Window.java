package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import model.Chrono;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 7446192599263749847L;
	
	private Chrono women;
	private Chrono diana;

	public Window() {
		setTitle("Representation chrono");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JButton btnWomen = new JButton("Women");
		btnWomen.setBounds(158, 55, 97, 25);
		mainPanel.add(btnWomen);
		btnWomen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				women.switchState();
				
			}
		});
		
		JLabel lblWomenTime = new JLabel("Women Time");
		lblWomenTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblWomenTime.setBounds(158, 84, 97, 16);
		mainPanel.add(lblWomenTime);
		
		
		JButton btnDiana = new JButton("Diana");
		btnDiana.setBounds(75, 155, 97, 25);
		mainPanel.add(btnDiana);
		btnDiana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				diana.switchState();
				
			}
			
		});
		
		women = new Chrono("Women");
		diana = new Chrono("Diana");
		
		women.addChild(diana);
		
		JLabel lblDianaTime = new JLabel("Diana Time");
		lblDianaTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblDianaTime.setBounds(75, 180, 97, 16);
		mainPanel.add(lblDianaTime);
		
		Timer timer = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblWomenTime.setText(getTimeFormat(women.getMeasuredTime()));
				lblDianaTime.setText(getTimeFormat(diana.getMeasuredTime()));
				
			}
			
		});
		
		timer.start();
		
	}
	
	public String getTimeFormat(long millis) {
		return String.format("%02d:%02d:%02d", 
			    TimeUnit.MILLISECONDS.toHours(millis),
			    TimeUnit.MILLISECONDS.toMinutes(millis) - 
			    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
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
