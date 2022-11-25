package trythreads.simple;

public class MyThreadWithoutJoin implements Runnable {

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
		
		Runnable objRunnable = new MyThreadWithoutJoin();
		Thread th = new Thread(objRunnable);
		th.start();   // start thread
		
		int counter = ((MyThreadWithoutJoin) objRunnable).getCounter();
		System.out.format("TOTAL: %d\n", counter);
		
				
	}

}
