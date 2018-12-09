package util;

public class TreeNode<T> {
	TreeNode<T> left;
	TreeNode<T> right;
	T value;
	public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
		super();
		this.left = left;
		this.right = right;
		this.value = value;
	}
	
	
}
