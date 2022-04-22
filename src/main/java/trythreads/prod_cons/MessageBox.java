package trythreads.prod_cons;

//Testing wait() and notify()
public class MessageBox {
	private String message;
	private boolean hasMessage;

    // producer
	public synchronized void putMessage(String message) {
		while (hasMessage) {
			// no room for new message
			try {
				wait(); // release the lock of this object
			} catch (InterruptedException e) {
			}
		}
		// acquire the lock and continue
		hasMessage = true;
		this.message = message + " Put @ " + System.nanoTime();
		notifyAll();
	}

    // consumer
	public synchronized String getMessage() {
		while (!hasMessage) {
			// no new message
			try {
				wait(); // release the lock of this object
			} catch (InterruptedException e) {
			}
		}
		// acquire the lock and continue
		hasMessage = false;
		notifyAll();
		return message + " Get @ " + System.nanoTime();
	}
}