package trythreads.simple;

public class MyThreadWithJoin implements Runnable {

	private int counter = 0;
	
	@Override
	public void run() {
		
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
		
		Runnable objRunnable = new MyThreadWithJoin();
		Thread th = new Thread(objRunnable);
		th.start();   // start thread

		//
		// int counter = ((MyThread3A) objRunnable).getCounter();
		// System.out.format("TOTAL: %d\n", counter);

		System.out.println("WAITING FINISH THREAD");

		try {
			// wait for finishing thread
			th.join();
			int counter = ((MyThreadWithJoin) objRunnable).getCounter();
			System.out.format("TOTAL RESULT COUNTER: %d\n", counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
