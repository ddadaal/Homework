package util;
import java.util.ArrayList;
import java.util.Comparator;
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
	
	public class CycleList {
		private ArrayList<Cycle> cycleList = new ArrayList<>();
		
		public void addCycle(Cycle cycle) {
			for (Cycle c: cycleList) {
				if (c.isEquivalent(cycle)) {
					return;
				}
			}
			cycleList.add(cycle);
		}
		
		public int size() {
			return cycleList.size();
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (Cycle c: cycleList) {
				builder.append(c.toString());
				builder.append("\n");
			}
			return builder.toString();
		}
	}
	
	public class Cycle {

		public ArrayList<Integer> points;
		public Cycle(ArrayList<Integer> points) {
			this.points = new ArrayList<>(points);
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (int i=0;i<points.size();i++) {
				builder.append(nodes.get(points.get(i)));
				builder.append("->");
				if (i == points.size()-1) {
					builder.append(nodes.get(points.get(0)));
				}
			}
			return builder.toString();
		}
		
		public boolean isEquivalent(Cycle c) {
			if (c.points.size()!= this.points.size()) {
				return false;
			}
			int j=0;
			for (;j<c.points.size();j++) {
				if (c.points.get(j).equals(points.get(0))) {
					break;
				}
			}
			
			for (int i=0;i<c.points.size();i++) {
				if (!points.get(i).equals(c.points.get((j++) % c.points.size()))) {
					return false;
				}
			}
			return true;
		}
	}
	
	public void addEdge(int start, int end) {
		LinkedList<Integer> node = edges.get(start);
		node.add(end);
	}
	
	public CycleList outputCycles() {
		int size =nodes.size();
		CycleList cl = new CycleList();
		for (int i=0;i<size;i++) {
			checkCycle(i,i,new ArrayList<>(), cl);
		}
		return cl;
	}
	
	private void checkCycle(int si, int ci, ArrayList<Integer> stack, CycleList cl) {
		if (si == ci && stack.size() != 0) {
			cl.addCycle(new Cycle(stack));
		} else if (stack.size() == nodes.size()) {
			return;
		} else {
			stack.add(ci);
			L1: for (int i : edges.get(ci)) {
				for (int j=0;j<stack.size()-1;j++) {
					if (stack.get(j).equals(ci) && stack.get(j+1).equals(i)) {
						continue L1;
					}
				}
				checkCycle(si, i, stack, cl);
			}
			stack.remove(stack.size()-1);
		}

	}
	
}
