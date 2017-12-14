package util;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<T> {
	ArrayList<T> nodes = new ArrayList<>();
	
	ArrayList<LinkedList<Integer>> edges = new ArrayList<>();
	
	public int addNode(T node) {
		nodes.add(node);
		edges.add(new LinkedList<>());
		return nodes.size()-1;
	}
	
	public int findNode(T node) {
		int size = nodes.size();
		for (int i=0;i<size;i++) {
			if (nodes.get(i).equals(node)) {
				return i;
			}
		}
		return -1;
	}
	
	public void addEdge(int start, int end) {
		LinkedList<Integer> node = edges.get(start);
		node.add(end);
	}
	
	public boolean outputCycles() {
		int size =nodes.size();
		boolean checked = false;
		for (int i=0;i<size;i++) {
			checked= checked | checkCycle(i,i,new ArrayList<>());
		}
		return checked;
	}
	
	private boolean checkCycle(int si, int ci, ArrayList<Integer> stack) {
		if (si == ci && stack.size() != 0) {
			for (int i=0;i<stack.size();i++) {
				System.out.print(nodes.get(stack.get(i)));
				if (i != stack.size()-1) {
					System.out.print("->");
				} else {
					System.out.println("->" + (nodes.get(stack.get(0))));
				}
			}
			return true;
		} else if (stack.size() == nodes.size()) {
			return false;
		} else {
			boolean result = false;
			stack.add(ci);
			for (int i : edges.get(ci)) {
				result = result | checkCycle(si, i, stack);
			}
			stack.remove(stack.size()-1);
			return result;
		}

	}
	
}
