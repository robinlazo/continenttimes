package universalTime;
import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends JFrame{
	
	public Main() {
		setTitle("TimeZone By Country");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new TimeBoard());
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	static public void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
		
	}
}
