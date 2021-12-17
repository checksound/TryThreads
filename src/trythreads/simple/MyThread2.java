package trythreads.simple;

public class MyThread2 implements Runnable {

	@Override
	public void run() {
		
		for (int i = 0; i < Integer.MAX_VALUE; i ++) {
			if(i % 1_000_000 == 0)
				System.out.format("In thread: %s - counter: %d\n", 
						Thread.currentThread().getName(), i);
		}
	}

	public static void main(String[] args) {
		
		Thread th = new Thread(new MyThread2());
		th.start();   // start thread
		
		for (int i = 0; i < Integer.MAX_VALUE; i ++) {
			if(i % 1_000_000 == 0)
				System.out.format("In thread: %s - counter: %d\n", 
						Thread.currentThread().getName(), i);
		}
	}

}
