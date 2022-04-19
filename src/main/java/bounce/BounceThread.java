package bounce;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Shows animated bouncing balls.
 * 
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BounceThread {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new BounceFrameWithBallRunnable();
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
