import java.util.*;

import util.*;

public class Chapter3 {

	public static void main(String[] args) {
		Polynomial p = new Polynomial();
		p.add(3, 2).add(3,1).add(3,0);
		
		Polynomial p2 = new Polynomial();
		p2.add(4,3).add(3,2).add(4,0);
		
		p.add(p2);
		System.out.println(p);
		
	}
	
	public static void polynomialSum(){
		Polynomial p = new Polynomial();

		p.add(3, 2);
		p.add(4, 1);
		p.add(5, 0);
		
		System.out.println(p.toString());
		
		System.out.println(p.sum(2)); //25
	}
	
	public static void josephusArray(int m, int n){
		JosephusArraySolution.solve(m, n);
	}
	
	
	public static void josephusLinkedList(int m, int n){
		CircularLinkedList list = new CircularLinkedList();
		for(int i=1;i<=m;i++){
			list.append(i);
		}
		
		LinkedListNode current= list.end;
		
		
		while(list.count>1){
			list.displayAll();
			for(int i=0;i<n;i++){
				current = current.next;
			}
			System.out.println(list.remove(current).number+" is removed.");
			
		}
		
		System.out.println("The winner is "+list.start.number+".");
		
	}
	
	public void solve2(){
		SinglyLinkedList list1 = new SinglyLinkedList(1,3,6,8,10,13);
		SinglyLinkedList list2 = new SinglyLinkedList(1,2,6,7,9,10,13);
		System.out.println(intersect(list1,list2));
		
		System.out.println(list1);
		reverse(list1);		
		System.out.println(list1);
	}
	
	public SinglyLinkedList intersect(SinglyLinkedList list1, SinglyLinkedList list2){
		SinglyLinkedList result = new SinglyLinkedList();
		
		LinkedListNode current1 = list1.start.next;
		LinkedListNode current2 = list2.start.next;
		loop: while(current1!=null){
			int l1 = current1.number;
			int l2;
			while((l2=current2.number)<l1){
				current2 = current2.next;
				if (current2==null){
					break loop;
				}
			}
			if (l1==l2){
				result.append(l1);
			}
			current1 = current1.next;
		}
		
		return result;
	}
	

	
	public SinglyLinkedList union(SinglyLinkedList list1, SinglyLinkedList list2){
		
		if (list1.start.number==0){
			return list2;
		}
		
		if (list2.start.number==0){
			return list1;
		}
		
		SinglyLinkedList result = new SinglyLinkedList();
		
		LinkedListNode current1= list1.start.next;
		LinkedListNode current2= list2.start.next;
		
		while(true){
			int cl1 = current1.number, cl2 = current2.number;
			if (cl1<cl2){
				result.append(cl1);
				current1 = current1.next;
			}
			else if (cl1>cl2){
				result.append(cl2);
				current2 = current2.next;
			}else{
				result.append(cl1);
				current1 = current1.next;
				current2 = current2.next;
			}
			if (current1==null){
				while(current2!=null){
					result.append(current2.number);
					current2= current2.next;
				}
				break;
			}
			if (current2==null){
				while(current1!=null){
					result.append(current1.number);
					current1= current1.next;
				}
				break;
			}
		}
		return result;
	}
	
	public void reverse(SinglyLinkedList list){
		Stack<LinkedListNode> nodes = new Stack<>();
		
		int num = list.start.number;
		
		LinkedListNode current = list.start.next;
		while(current!=null){
			nodes.push(current);
			current= current.next;
		} // pushes all nodes into a stack
		
		list.start.next = nodes.peek();
		
		for(int i=0;i<num-1;i++){
			current = nodes.pop();
			current.next = nodes.peek();
		}
		
		nodes.pop().next=null;
		
	}
	
	public void betterReverse(SinglyLinkedList list){
		if (list.start.number<=1){
			return;
		}
		LinkedListNode current = list.start.next;
		LinkedListNode previous = current;
		LinkedListNode after = list.start.next.next;
		current.next = null;
		current = after;
		after = current.next;
		while(after!=null){
			current.next = previous;
			previous = current;
			current = after;
			after = after.next;
			
		}
		current.next = previous;
		list.start.next = current;
	}

}

class JosephusArraySolution{
	public static void solve(int m, int n){
		int[] array = new int[m];
		boolean[] removed = new boolean[m];
		for(int i=1;i<=m;i++){
			array[i-1] = i;
			removed[i-1]=false;
		}
		
		int pointer = m-1;
		while(count(array, removed)>1){
			displayAll(array,removed);
			for(int i=0;i<n;i++){
				do{
					pointer = increment(pointer,m);
				}while(removed[pointer]);
			}
			removed[pointer]=true;
			System.out.println(array[pointer]+" is removed.");
		}
		
		int winnerindex = 0;
		while(removed[winnerindex]){
			winnerindex++;
		}
		System.out.println("The winner is "+array[winnerindex]+".");
		
		
	}
	
	public static void displayAll(int[] array, boolean[] removed){
		for(int i=0;i<array.length;i++){
			if (!removed[i]){
				System.out.print(array[i]+", ");
			}
		}
		System.out.println();
	}
	
	public static int increment(int pointer, int m){
		return (pointer+1)%m;
	}
	
	public static int count(int[] array, boolean[] removed){
		int count = 0;
		for(int i=0;i<array.length;i++){
			if (!removed[i]){
				count++;
			}
		}
		return count;
	}
}

