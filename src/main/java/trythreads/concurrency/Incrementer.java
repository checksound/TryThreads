package trythreads.concurrency;

public class Incrementer extends Thread {
	
	private final Counter counter;
	private final int incrementValue; 
	
	public Incrementer(Counter counter, int incrementValue) {
		this.counter = counter;
		this.incrementValue = incrementValue;
	}
	
	public void run() {
		for (int i = 0; i < incrementValue; i++) {
			counter.increment();
		}
	}
	
	

}
