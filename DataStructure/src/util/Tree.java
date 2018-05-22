package util;

import java.util.function.Consumer;

import sun.misc.Queue;

public class Tree<T> {
	TreeNode<T> root;
	
	public Tree(TreeNode<T> root){
		this.root = root;
	}
	/**
	 * Generates a String binary Tree using a space separated expression representing a full binary tree.
	 * @param expression space separated expression representing a full binary tree
	 * @param nullIndicator a string indicating the position should be null. Be null if no null is expected
	 * @return a String binary tree
	 */
	
	public static Tree<String> constructTreeFromFullTree(String expression, String nullIndicator){
		try {
			Queue<TreeNode<String>> queue = new Queue<>();
			String[] splitExpression = expression.split(" ");
			TreeNode<String> root = new TreeNode<String>(splitExpression[0], null, null);
			queue.enqueue(root);
			for (int i = 1; i < splitExpression.length; i += 2) {
				String str1 = splitExpression[i], str2 = splitExpression[i + 1];

				TreeNode<String> node1 = str1.equals(nullIndicator) ? null : new TreeNode<String>(str1, null, null);
				TreeNode<String> node2 = str2.equals(nullIndicator) ? null : new TreeNode<String>(str2, null, null);

				TreeNode<String> pre = queue.dequeue();
				if (pre!=null){
					pre.left = node1;
					pre.right = node2;
				}
				queue.enqueue(node1);
				queue.enqueue(node2);
			}
			return new Tree<String>(root);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Generates a String binary Tree using a space separated expression representing a complete binary tree.
	 * @param expression expression space separated expression representing a complete binary tree
	 * @param nullIndicator a string indicating the position should be null. Be null if no null is expected
	 * @return a String binary tree
	 */
	
	public static Tree<String> constructTreeFromCompleteTree(String expression, String nullIndicator){
		String[] splitExpression = expression.split(" ");
		TreeNode<String>[] nodes = new TreeNode[splitExpression.length];
		for(int i=0;i<splitExpression.length;i++){
			nodes[i] = splitExpression[i].equals(nullIndicator) ? null : new TreeNode<String>(splitExpression[i],null,null);
		}
		TreeNode<String> root = nodes[0];
		int i=0, j=1;
		while(j<splitExpression.length-1){
			if (nodes[i]!=null){
				nodes[i].left = nodes[j];
				nodes[i].right = nodes[j+1];
			}
			i++;
			j+=2;
		}
		return new Tree<String>(root);
		
	}
	

	/**
	 * Traverse a tree preorderly.
	 * @param visit visit function
	 */
	public void preorder(Consumer<T> visit) {
		preorderRec(root, visit);
	}
	
	/**
	 * Traverse a tree inorderly.
	 * @param visit visit function
	 */
	public void inorder(Consumer<T> visit) {
		inorderRec(root, visit);
	}
	
	/**
	 * Traverse a tree postoderly.
	 * @param visit visit function
	 */
	public void postorder(Consumer<T> visit) {
		postorderRec(root, visit);
	}
	
	private void preorderRec(TreeNode<T> node, Consumer<T> visit){
		if (node!=null){
			visit.accept(node.value);
			preorderRec(node.left, visit);
			preorderRec(node.right, visit);
		}
	}
	private void inorderRec(TreeNode<T> node, Consumer<T> visit){
		if (node!=null){
			inorderRec(node.left, visit);
			visit.accept(node.value);
			inorderRec(node.right, visit);
		}
	}
	private void postorderRec(TreeNode<T> node, Consumer<T> visit){
		if (node!=null){
			postorderRec(node.left, visit);
			postorderRec(node.right, visit);
			visit.accept(node.value);
		}
	}
	
}
