package trythreads.concurrency;

import java.util.Locale;

public class TestCounterGeneric {
	
	private static void testCounter(Counter counter) 
			throws InterruptedException {
				
		int incrementValue1 = 200_000_000;
		int incrementValue2 = 100_000_000;
		
		Incrementer incrementer1 = 
				new Incrementer(counter, incrementValue1);
				
		Incrementer incrementer2 = 
				new Incrementer(counter, incrementValue2);
		
		incrementer1.start();
		incrementer2.start();
		
		incrementer1.join();
		incrementer2.join();
		
		int counterValue = counter.getValue();
				
		System.out.format(Locale.ITALIAN, "SUM VALUE: %,d - SHOULD BE: %,d\n", 
				counterValue, 
				(incrementValue1 + incrementValue2));
		
		int difference = incrementValue1 + incrementValue2 - counterValue; 
		
		double percent = ((0.0 + difference) / 
				(incrementValue1 + incrementValue2)) * 100;
		
		System.out.format(Locale.ITALIAN, 
				"DEFFERENCE: %,d - DIFF: %f %%\n", difference, percent);
	}

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("STARTING COUNTER NOT SYNCHRONIZED");
		Counter counter1 = new CounterGeneric(false);
		long startTimeA = System.currentTimeMillis();
		testCounter(counter1);
		long timeElapsedA = System.currentTimeMillis() - startTimeA;
		System.out.format(Locale.ITALIAN, 
				"FINISHED Counter NOT SYNCHRONIZED, elapsed time: %,d ms\n", 
				timeElapsedA);
		
		System.out.println("\nSTARTING COUNTER SYNCHRONIZED");
		Counter counter2 = new CounterGeneric(true);
		long startTimeB = System.currentTimeMillis();
		testCounter(counter2);
		long timeElapsedB = System.currentTimeMillis() - startTimeB;
		System.out.format(Locale.ITALIAN, 
				"FINISHED Counter SYNCHRONIZED, elapsed time: %,d ms\n", 
				timeElapsedB);
	}

}
