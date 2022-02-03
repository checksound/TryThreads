package trythreads.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic implements Counter {

	private AtomicInteger counter = new AtomicInteger();
	
	@Override
	public void increment() {
		counter.incrementAndGet();
	}

	@Override
	public int getValue() {
		return counter.get();
	}

}
