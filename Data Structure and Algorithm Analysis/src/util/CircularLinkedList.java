package util;


public class CircularLinkedList {
	public LinkedListNode start;
	public LinkedListNode end;
	public int count;

	
	public CircularLinkedList append(int value){
		if (start==null){
			start = new LinkedListNode(value,null);
			end= start;
			start.next = end;
			count=1;
		}else{
			LinkedListNode newNode = new LinkedListNode(value, start);
			end.next = newNode;
			end = newNode;
			count++;
		}
		return this;
	}
	
	public LinkedListNode remove(LinkedListNode node){
		findPrevious(node).next = node.next;
		if (node == start){
			start = start.next;
		}
		count--;
		return node;
	}
	
	public LinkedListNode findPrevious(LinkedListNode node){
		LinkedListNode current = start;
		while(current.next != node){
			current = current.next;
		}
		return current;
	}
	
	public LinkedListNode get(int index){
		LinkedListNode current = start;
		for(int i=0;i<index;i++){
			current = current.next;
		}
		return current;
	}
	
	@Override
	public String toString(){
		StringBuilder output= new StringBuilder();
		LinkedListNode current=  start;
		for(int i=0;i<count;i++){
			output.append(", "+current.number);
			current = current.next;
		}
		return output.indexOf(",")==-1 ? "" : output.substring(2);
	}
	
	
	public void displayAll(){
		System.out.println(toString());
	}

}


