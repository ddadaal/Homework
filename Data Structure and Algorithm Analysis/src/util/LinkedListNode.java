package util;

public class LinkedListNode{
	public int number;
	public LinkedListNode next;
	
	public LinkedListNode(int number, LinkedListNode next){
		this.number = number;
		this.next = next;
	}
	
	@Override
	public String toString(){
		return number+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedListNode other = (LinkedListNode) obj;
		if (number != other.number)
			return false;
		return true;
	}
	

	

}