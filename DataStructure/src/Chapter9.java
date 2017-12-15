import util.Graph;
import util.Graph.CycleList;
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
		graph.addEdge(3, 1);
		graph.addEdge(1, 0);
		Graph<String>.CycleList cl = graph.outputCycles();
		if (cl.size()==0){
			System.out.println("no graph detected.");
		} else {
			System.out.println(cl.toString());
		}
	}
}
