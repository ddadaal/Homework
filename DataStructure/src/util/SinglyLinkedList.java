package util;

import java.util.Iterator;

public class SinglyLinkedList implements Iterable<Integer>, Iterator<Integer> {
	public LinkedListNode start;
	public LinkedListNode end;
	private LinkedListNode current;
	
	public SinglyLinkedList(){
		start = new LinkedListNode(0, null);
		end = start;
		current = start;
	}
	
	public SinglyLinkedList(int...values){
		this();
		appendMany(values);
	}
	
	public SinglyLinkedList append(int value){
		LinkedListNode node = new LinkedListNode(value, null);
		end.next= node;
		end = node;
		start.number ++;
		return this;
	}
	
	public SinglyLinkedList appendMany(int... values){
		for(int i: values){
			append(i);
		}
		return this;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		LinkedListNode current = start;
		while(current.next!=null){
			result.append(current.next.number+", ");
			current = current.next;
		}
		return result.toString();
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current.next!=null;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		current = current.next;
		return current.number;
	}
}
