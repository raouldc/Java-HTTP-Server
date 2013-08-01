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
		
		/**
		fEmptySlots = new Semaphore(fCapacity);
		fFullSlots = new Semaphore(0);
		fMutex = new Semaphore(1);
		
		**/

	}
	

	@Override
	public void put(Object element) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFull() throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

}
