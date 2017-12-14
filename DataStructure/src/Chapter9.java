import util.Graph;
public class Chapter9 {
	public static void main(String[] arg) {
		Graph<String> graph = new Graph<>();
		graph.addNode("0");
		graph.addNode("1");
		graph.addNode("2");
		graph.addNode("3");
		graph.addNode("4");
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		graph.addEdge(3, 0);
		if (!graph.outputCycles()) {
			System.out.println("no graph detected.");
		}
	}
}
