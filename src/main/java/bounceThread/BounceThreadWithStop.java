package bounceThread;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Shows animated bouncing balls.
 * @version 1.33 2022-01-16
 * @author Massimo Cappellano
 */
public class BounceThreadWithStop
{
	private static List<Thread> listThreads = new ArrayList<>();	
	/**
	 * A runnable that animates a bouncing ball.
	 */
	static class BallRunnable implements Runnable
	{
	   private Ball ball;
	   private Component component;
	   public static final int STEPS = 3000;
	   public static final int DELAY = 5;

	   /**
	    * Constructs the runnable.
	    * @param aBall the ball to bounce
	    * @param aComponent the component in which the ball bounces
	    */
	   public BallRunnable(Ball aBall, Component aComponent)
	   {
	      ball = aBall;
	      component = aComponent;
	   }

	   public void run()
	   {
	      try
	      {
	         for (int i = 1; i <= STEPS; i++)
	         {
	            ball.move(component.getBounds());
	            component.repaint();
	            Thread.sleep(DELAY);
	         }
	      }
	      catch (InterruptedException e)
	      {
	      }
	   }

	}

	/**
	 * The frame with panel and buttons.
	 */
	static class BounceFrame extends JFrame
	{
	   private BallComponent comp;

	   /**
	    * Constructs the frame with the component for showing the bouncing ball and Start and Close
	    * buttons
	    */
	   public BounceFrame()
	   {
	      comp = new BallComponent();
	      add(comp, BorderLayout.CENTER);
	      JPanel buttonPanel = new JPanel();
	      addButton(buttonPanel, "Start", new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               addBall();
	            }
	         });
	      
	      addButton(buttonPanel, "Stop", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for( Thread td: listThreads) {
					td.interrupt();
				}
				
			}
	    	  
	      });

	      addButton(buttonPanel, "Close", new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               System.exit(0);
	            }
	         });
	      add(buttonPanel, BorderLayout.SOUTH);
	      pack();
	   }

	   /**
	    * Adds a button to a container.
	    * @param c the container
	    * @param title the button title
	    * @param listener the action listener for the button
	    */
	   public void addButton(Container c, String title, ActionListener listener)
	   {
	      JButton button = new JButton(title);
	      c.add(button);
	      button.addActionListener(listener);
	   }

	   /**
	    * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	    */
	   public void addBall()
	   {
	      Ball b = new Ball();
	      comp.add(b);
	      Runnable r = new BallRunnable(b, comp);
	      Thread t = new Thread(r);
	      t.start();
	      
	      // access should be synchronized
	      listThreads.add(t);
	   }
	}

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new BounceFrame();
               frame.setTitle("BounceThread");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

