/**
 * Binary Search Tree implementation with standard BST operations.
 * This implementation maintains the BST property: for any node,
 * all values in the left subtree are smaller, and all values in
 * the right subtree are larger.
 * 
 * Features:
 * - Insert, search, and delete operations
 * - Three types of tree traversals (inorder, preorder, postorder)
 * - Utility methods for tree statistics (size, height, min/max)
 * - Handles duplicate values by ignoring them
 * - Recursive implementation for clean and readable code
 * 
 * Time Complexity:
 * - Average case: O(log n) for insert, search, delete
 * - Worst case: O(n) when tree becomes unbalanced
 * - Traversals: O(n) always
 * 
 * Space Complexity:
 * - Storage: O(n) for n nodes
 * - Recursion stack: O(log n) average, O(n) worst case
 * 
 * @author Aya Gamal
 * @version 1.0
 */
public class BinarySearchTree {
    
    /** The root node of the BST - entry point for all operations */
    private TreeNode root;
    
    /**
     * Constructor - initializes an empty BST
     */
    public BinarySearchTree() {
        this.root = null;
    }
    
    // ==================== INSERTION OPERATIONS ====================
    
    /**
     * Public method to insert a value into the BST
     * @param value the integer value to insert
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    /**
     * Private recursive helper method for insertion
     * @param node the current node being examined
     * @param value the value to insert
     * @return the node after insertion (may be a new node or the same node)
     */
    private TreeNode insertRecursive(TreeNode node, int value) {
        // Base case: if we reach an empty spot, create a new node
        if (node == null) {
            return new TreeNode(value);
        }
        
        // Recursive case: compare and go left or right
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }
        // If value equals node.value, we ignore duplicates
        
        return node;
    }
    
    // ==================== SEARCH OPERATIONS ====================
    
    /**
     * Public method to search for a value in the BST
     * @param value the value to search for
     * @return true if the value exists, false otherwise
     */
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    /**
     * Private recursive helper method for searching
     * @param node the current node being examined
     * @param value the value to search for
     * @return true if found, false otherwise
     */
    private boolean searchRecursive(TreeNode node, int value) {
        // Base case: if node is null, value not found
        if (node == null) {
            return false;
        }
        
        // If we found the value
        if (value == node.value) {
            return true;
        }
        
        // Recursively search in the appropriate subtree
        if (value < node.value) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }
    
    // ==================== DELETION OPERATIONS ====================
    
    /**
     * Public method to delete a value from the BST
     * @param value the value to delete
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    /**
     * Private recursive helper method for deletion
     * @param node the current node being examined
     * @param value the value to delete
     * @return the node after deletion (may be null, same node, or different node)
     */
    private TreeNode deleteRecursive(TreeNode node, int value) {
        // Base case: if tree is empty or value not found
        if (node == null) {
            return null;
        }
        
        // Recursively find the node to delete
        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node to be deleted found - handle three cases
            
            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2: Node has only one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
            // Case 3: Node has two children
            // Find the inorder successor (smallest value in right subtree)
            TreeNode successor = findMinimum(node.right);
            
            // Replace the node's value with successor's value
            node.value = successor.value;
            
            // Delete the successor (which has at most one child)
            node.right = deleteRecursive(node.right, successor.value);
        }
        
        return node;
    }
    
    /**
     * Helper method to find the node with minimum value in a subtree
     * @param node the root of the subtree
     * @return the node with the minimum value
     */
    private TreeNode findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // ==================== TRAVERSAL OPERATIONS ====================
    
    /**
     * Performs inorder traversal (Left -> Root -> Right)
     * Results in sorted order for BST
     */
    public void inorderTraversal() {
        System.out.print("Inorder traversal: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    /**
     * Private recursive helper for inorder traversal
     * @param node the current node
     */
    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }
    
    /**
     * Performs preorder traversal (Root -> Left -> Right)
     */
    public void preorderTraversal() {
        System.out.print("Preorder traversal: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    /**
     * Private recursive helper for preorder traversal
     * @param node the current node
     */
    private void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
    /**
     * Performs postorder traversal (Left -> Right -> Root)
     */
    public void postorderTraversal() {
        System.out.print("Postorder traversal: ");
        postorderRecursive(root);
        System.out.println();
    }
    
    /**
     * Private recursive helper for postorder traversal
     * @param node the current node
     */
    private void postorderRecursive(TreeNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Checks if the BST is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Gets the height of the BST
     * @return the height of the tree (-1 for empty tree)
     */
    public int getHeight() {
        return getHeightRecursive(root);
    }
    
    /**
     * Private recursive helper for calculating height
     * @param node the current node
     * @return the height of the subtree rooted at node
     */
    private int getHeightRecursive(TreeNode node) {
        if (node == null) {
            return -1; // Height of empty tree is -1
        }
        
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Counts the total number of nodes in the BST
     * @return the number of nodes
     */
    public int getSize() {
        return getSizeRecursive(root);
    }
    
    /**
     * Private recursive helper for counting nodes
     * @param node the current node
     * @return the number of nodes in the subtree rooted at node
     */
    private int getSizeRecursive(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return 1 + getSizeRecursive(node.left) + getSizeRecursive(node.right);
    }
    
    /**
     * Finds the minimum value in the BST
     * @return the minimum value, or throws exception if tree is empty
     */
    public int findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        
        TreeNode minNode = findMinimum(root);
        return minNode.value;
    }
    
    /**
     * Finds the maximum value in the BST
     * @return the maximum value, or throws exception if tree is empty
     */
    public int findMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
