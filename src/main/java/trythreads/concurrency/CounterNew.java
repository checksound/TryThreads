package trythreads.concurrency;

public class CounterNew implements Counter {

	private int counter;
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	
	@Override
	public void increment() {
		synchronized(obj1) {
			counter++;
		}
	}

	@Override
	public int getValue() {
		synchronized(obj2) {
			return counter;
		}
	}

}
