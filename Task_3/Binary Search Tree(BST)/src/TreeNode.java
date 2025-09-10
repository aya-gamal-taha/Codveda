/**
 * TreeNode class represents a single node in the Binary Search Tree.
 * Each node contains an integer value and references to left and right child nodes.
 * 
 * @author Aya Gamal
 * @version 1.0
 */
public class TreeNode {
    
    /** The integer value stored in this node */
    int value;
    
    /** Reference to the left child node (contains values smaller than this node's value) */
    TreeNode left;
    
    /** Reference to the right child node (contains values larger than this node's value) */
    TreeNode right;

    /**
     * Constructor to create a new TreeNode with the specified value.
     * Initializes left and right children to null.
     * 
     * @param value the integer value to store in this node
     */
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Returns a string representation of this node.
     * Useful for debugging and display purposes.
     * 
     * @return string representation of the node's value
     */
    @Override
    public String toString() {
        return "TreeNode{value=" + value + "}";
    }
    
    /**
     * Checks if this node is a leaf node (has no children).
     * 
     * @return true if this node has no left or right children, false otherwise
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    /**
     * Checks if this node has only one child.
     * 
     * @return true if this node has exactly one child, false otherwise
     */
    public boolean hasOneChild() {
        return (left == null && right != null) || (left != null && right == null);
    }
    
    /**
     * Checks if this node has two children.
     * 
     * @return true if this node has both left and right children, false otherwise
     */
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
}
