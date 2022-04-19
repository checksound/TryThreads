package bounce;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Shows animated bouncing balls.
 * 
 * @version 1.33 2022-01-16
 * @author Massimo Cappellano
 */
public class BounceThreadWithStop {
	private static List<Thread> listThreads = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new BounceFrameWithStop(listThreads);
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
