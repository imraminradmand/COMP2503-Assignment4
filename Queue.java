public class Queue<T extends Comparable<T>> extends SLL<T> {

	public T dequeue() {
		return deleteHead();
	}
	
	public void enqueue(T data) {
		addTail(data);
	}
	public T peak() {
		return get(0).getData();
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void empty() {
		while(!isEmpty()) {
			dequeue();
		}
	}
}
