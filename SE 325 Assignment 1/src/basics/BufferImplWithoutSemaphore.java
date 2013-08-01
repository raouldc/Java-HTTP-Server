package basics;

public class BufferImplWithoutSemaphore<T> implements Buffer<T> {

	// Buffer data structure variables.
	private int fCapacity;		// Number of elements that can be stored.
	private int fFront;			// Index of element at the front of the buffer.
	private int fBack;			// Index of next free slot within the buffer.
	private T[] fElements;		// Array to store elements.

	public BufferImplWithoutSemaphore(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException();
		}

		fCapacity = capacity;
		fElements = (T[]) new Object[fCapacity];
		fFront = 0;
		fBack = 0;
	}


	@Override
	public synchronized void put(T element) throws InterruptedException {
		// Wait if necessary for an empty slot.
		while(isFull())
		{
			wait();
		}
		// Lock buffer's elements.


		fElements[fBack] = element;
		fBack = (fBack + 1) % fCapacity;

		// Release buffer's lock.

		// Signal to an individual consumer thread, if one is suspended on the 
		// fFullSlots Semaphore, that a slot has been filled.
		notify();
	}

	@Override
	public synchronized T get() throws InterruptedException {
		// Wait if necessary for a slot to be filled.
		while(isEmpty())
		{
			wait();
		}
		// Lock buffer's elements.

		T result = fElements[fFront];
		fElements[fFront] = null;
		fFront = (fFront + 1) % fCapacity;

		// Release buffer's lock.

		// Signal to an individual producer thread, if one is suspended on the 
		// fEmptySlots Semaphore, that a slot has been emptied.
		notify();		
		return result;

	}

	@Override
	public synchronized boolean isFull() throws InterruptedException {
		boolean result = false;
		result = fFront == fBack && fElements[fFront] != null;
		return result;
	}

	@Override
	public synchronized boolean isEmpty() throws InterruptedException {
		boolean result = false;
		result =  fFront == fBack && fElements[fFront] == null;
		return result;
	}

}
