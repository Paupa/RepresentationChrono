package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Chrono;

public class ChronoButton extends JButton {
	
	private Chrono chrono;
	
	private JLabel time;

	private static final long serialVersionUID = 1382683416360842617L;
	
	public ChronoButton(String name) {
		this(new Chrono(name));
	}
	
	public ChronoButton(Chrono chrono) {
		super(chrono.getName());
		this.chrono = chrono;
		
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chrono.switchState();
			}
			
		});
		
		time = new JLabel(chrono.getName() + " time");
		time.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setPosition(int x, int y) {
		this.setBounds(x, y, 97, 25);
		time.setBounds(x, y + 30, 97, 16);
	}
	
	public void refreshTime() {
		time.setText(chrono.getMeasuredTime());
	}
	
	public void addChild(ChronoButton cb) {
		addChild(cb.chrono);
	}
	
	public void addChild(Chrono child) {
		chrono.addChild(child);
	}
	
	public void removeChild(ChronoButton cb) {
		removeChild(cb.chrono);
	}
	
	public void removeChild(Chrono child) {
		chrono.removeChild(child);
	}
	
	public void beAddedTo(JPanel panel) {
		panel.add(time);
		panel.add(this);
	}

}
