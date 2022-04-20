package bounce.v2;

import java.awt.Component;

/**
 * A runnable that animates a bouncing ball.
 */
public class BallRunnable implements Runnable
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