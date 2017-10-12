package util;

public class SinglyLinkedList {
	public LinkedListNode start; //head
	public LinkedListNode end;
	
	public SinglyLinkedList(){
		start = new LinkedListNode(0, null);
		end = start;
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
}
