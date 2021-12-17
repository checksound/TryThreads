package trythreads.concurrency;

public class CounterGeneric implements Counter {

	private int counter;
	private final boolean isSafe;
	
	public CounterGeneric(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
	@Override
	public void increment() {
		if(!isSafe)
			counter++;
		else {
			synchronized(this) {
				counter++;
			}
		}
	}

	@Override
	public int getValue() {
		if(!isSafe)
			return counter;
		else {
			synchronized(this) {
				return counter;
			}
		}
	}

}
