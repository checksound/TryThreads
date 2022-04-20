package bounce.v2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The frame with panel and buttons.
 */
public class BounceFrame extends JFrame
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
   }
}
