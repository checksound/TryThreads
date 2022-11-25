package trythreads.simple;

public class MyThreadWithJoinWithTime implements Runnable {

	private int counter = 0;
	
	@Override
	public void run() {

		// long run
		for (; counter < Integer.MAX_VALUE; counter ++) {
			if(counter % 10_000_000 == 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("FINISHED WORK");
	}
	
	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		
		Runnable objRunnable = new MyThreadWithJoinWithTime();
		Thread th = new Thread(objRunnable);
		th.start();   // start thread
		
		System.out.println("WAITING FINISH THREAD");
		//
		// int counter = ((MyThread3A) objRunnable).getCounter();
		// System.out.format("TOTAL: %d\n", counter);
		
		// wait for finishing thread
			while(th.isAlive()) {
				try {
					th.join(100);
					System.out.print(".");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("");
			int counter = ((MyThreadWithJoinWithTime) objRunnable).getCounter();
			System.out.format("TOTAL RESULT COUNTER: %d\n", counter);

		
	}

}
