package trythreads.concurrency;

public class CounterSafe implements Counter {

	private int counter;

	@Override
	public synchronized void increment() {
		counter++;
	}

	@Override
	public synchronized int getValue() {
		return counter;
	}

}
