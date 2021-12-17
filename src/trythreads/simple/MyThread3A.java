package trythreads.simple;

public class MyThread3A implements Runnable {

	private int counter = 0;
	
	@Override
	public void run() {
		
		for (; counter < Integer.MAX_VALUE; counter ++) {
			if(counter % 1_000_000 == 0)
				System.out.format("In thread: %s - counter: %d\n", 
						Thread.currentThread().getName(), counter);
		}
	}
	
	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		
		Runnable objRunnable = new MyThread3A();
		Thread th = new Thread(objRunnable);
		th.start();   // start thread
		
		for (int i = 0; i < Integer.MAX_VALUE/2; i ++) {
			if(i % 1_000_000 == 0)
				System.out.format("In thread: %s - counter: %d\n", 
						Thread.currentThread().getName(), i);
		}
		
		int counter = ((MyThread3B) objRunnable).getCounter();
		System.out.format("TOTAL: %d\n", counter);
		
				
	}

}
