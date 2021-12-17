package trythreads.concurrency;

public class CounterUnsafe implements Counter {

	private int counter;
	
	@Override
	public void increment() {
		counter++;
	}

	@Override
	public int getValue() {
		return counter;
	}

}
