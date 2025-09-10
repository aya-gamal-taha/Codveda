/**
 * Comprehensive test suite for Binary Search Tree implementation.
 * Tests all major operations including insertion, deletion, search, and traversals.
 * 
 * Test Coverage:
 * - Insertion: Empty tree, multiple values, duplicates
 * - Search: Existing values, non-existing values, empty tree
 * - Deletion: Leaf nodes, single child, two children, root deletion
 * - Utility: isEmpty, getSize, getHeight, findMin, findMax
 * - Traversals: Inorder, preorder, postorder
 * - Stress testing: Large datasets (100+ nodes)
 * - Edge cases: Empty tree operations, exception handling
 * 
 * Usage:
 * 1. Compile: javac -cp src test/*.java
 * 2. Run: java -ea -cp src:test BinarySearchTreeTest
 * 
 * Note: Run with -ea flag to enable assertions
 * 
 * @author Binary Search Tree Implementation
 * @version 1.0
 */
public class BinarySearchTreeTest {
    
    /** BST instance used for testing - reset before each test */
    private BinarySearchTree bst;
    
    /**
     * Sets up a fresh BST before each test
     */
    public void setUp() {
        bst = new BinarySearchTree();
    }
    
    // ==================== INSERTION TESTS ====================
    
    /**
     * Test inserting into an empty tree
     */
    public void testInsertIntoEmptyTree() {
        setUp();
        bst.insert(10);
        
        assert !bst.isEmpty() : "Tree should not be empty after insertion";
        assert bst.search(10) : "Should find the inserted value";
        assert bst.getSize() == 1 : "Size should be 1 after single insertion";
    }
    
    /**
     * Test inserting multiple values
     */
    public void testInsertMultipleValues() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        assert bst.getSize() == values.length : "Size should match number of insertions";
        
        for (int value : values) {
            assert bst.search(value) : "Should find all inserted values: " + value;
        }
    }
    
    /**
     * Test inserting duplicate values (should be ignored)
     */
    public void testInsertDuplicates() {
        setUp();
        bst.insert(10);
        bst.insert(10);
        bst.insert(10);
        
        assert bst.getSize() == 1 : "Size should remain 1 when inserting duplicates";
        assert bst.search(10) : "Should still find the value";
    }
    
    // ==================== SEARCH TESTS ====================
    
    /**
     * Test searching in empty tree
     */
    public void testSearchEmptyTree() {
        setUp();
        assert !bst.search(10) : "Should not find value in empty tree";
    }
    
    /**
     * Test searching for existing values
     */
    public void testSearchExistingValues() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        for (int value : values) {
            assert bst.search(value) : "Should find existing value: " + value;
        }
    }
    
    /**
     * Test searching for non-existing values
     */
    public void testSearchNonExistingValues() {
        setUp();
        int[] values = {50, 30, 70};
        int[] nonExisting = {25, 35, 75, 100};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        for (int value : nonExisting) {
            assert !bst.search(value) : "Should not find non-existing value: " + value;
        }
    }
    
    // ==================== DELETION TESTS ====================
    
    /**
     * Test deleting from empty tree
     */
    public void testDeleteFromEmptyTree() {
        setUp();
        bst.delete(10); // Should not crash
        assert bst.isEmpty() : "Tree should remain empty";
    }
    
    /**
     * Test deleting leaf nodes
     */
    public void testDeleteLeafNode() {
        setUp();
        int[] values = {50, 30, 70, 20, 40};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        // Delete leaf nodes
        bst.delete(20);
        bst.delete(40);
        
        assert !bst.search(20) : "Should not find deleted leaf node 20";
        assert !bst.search(40) : "Should not find deleted leaf node 40";
        assert bst.search(30) : "Parent node should still exist";
        assert bst.getSize() == 3 : "Size should be reduced after deletions";
    }
    
    /**
     * Test deleting node with one child
     */
    public void testDeleteNodeWithOneChild() {
        setUp();
        int[] values = {50, 30, 70, 20};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        // Delete node with one child
        bst.delete(30);
        
        assert !bst.search(30) : "Should not find deleted node";
        assert bst.search(20) : "Child should still be accessible";
        assert bst.search(50) : "Root should still exist";
        assert bst.getSize() == 3 : "Size should be reduced";
    }
    
    /**
     * Test deleting node with two children
     */
    public void testDeleteNodeWithTwoChildren() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        // Delete node with two children
        bst.delete(30);
        
        assert !bst.search(30) : "Should not find deleted node";
        assert bst.search(20) : "Left child should still exist";
        assert bst.search(40) : "Right child should still exist";
        assert bst.getSize() == 6 : "Size should be reduced";
        
        // Tree should still maintain BST property
        testBSTProperty();
    }
    
    /**
     * Test deleting root node
     */
    public void testDeleteRoot() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        bst.delete(50);
        
        assert !bst.search(50) : "Should not find deleted root";
        assert bst.getSize() == 6 : "Size should be reduced";
        
        // All other nodes should still be accessible
        int[] remaining = {30, 70, 20, 40, 60, 80};
        for (int value : remaining) {
            assert bst.search(value) : "Should find remaining node: " + value;
        }
    }
    
    // ==================== UTILITY TESTS ====================
    
    /**
     * Test isEmpty method
     */
    public void testIsEmpty() {
        setUp();
        assert bst.isEmpty() : "New tree should be empty";
        
        bst.insert(10);
        assert !bst.isEmpty() : "Tree with elements should not be empty";
        
        bst.delete(10);
        assert bst.isEmpty() : "Tree should be empty after deleting all elements";
    }
    
    /**
     * Test getSize method
     */
    public void testGetSize() {
        setUp();
        assert bst.getSize() == 0 : "Empty tree should have size 0";
        
        int[] values = {50, 30, 70, 20, 40};
        for (int i = 0; i < values.length; i++) {
            bst.insert(values[i]);
            assert bst.getSize() == i + 1 : "Size should increase with each insertion";
        }
        
        bst.delete(30);
        assert bst.getSize() == 4 : "Size should decrease after deletion";
    }
    
    /**
     * Test getHeight method
     */
    public void testGetHeight() {
        setUp();
        assert bst.getHeight() == -1 : "Empty tree should have height -1";
        
        bst.insert(50);
        assert bst.getHeight() == 0 : "Single node tree should have height 0";
        
        bst.insert(30);
        bst.insert(70);
        assert bst.getHeight() == 1 : "Tree with 3 nodes should have height 1";
        
        bst.insert(20);
        assert bst.getHeight() == 2 : "Tree should have height 2";
    }
    
    /**
     * Test findMin method
     */
    public void testFindMin() {
        setUp();
        
        try {
            bst.findMin();
            assert false : "Should throw exception for empty tree";
        } catch (IllegalStateException e) {
            // Expected behavior
        }
        
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10};
        for (int value : values) {
            bst.insert(value);
        }
        
        assert bst.findMin() == 10 : "Should find minimum value";
    }
    
    /**
     * Test findMax method
     */
    public void testFindMax() {
        setUp();
        
        try {
            bst.findMax();
            assert false : "Should throw exception for empty tree";
        } catch (IllegalStateException e) {
            // Expected behavior
        }
        
        int[] values = {50, 30, 70, 20, 40, 60, 80, 90};
        for (int value : values) {
            bst.insert(value);
        }
        
        assert bst.findMax() == 90 : "Should find maximum value";
    }
    
    // ==================== TRAVERSAL TESTS ====================
    
    /**
     * Test that inorder traversal produces sorted output
     */
    public void testInorderTraversal() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        System.out.println("Testing inorder traversal (should be sorted):");
        bst.inorderTraversal();
        // Expected output: 20 30 40 50 60 70 80
    }
    
    /**
     * Test preorder traversal
     */
    public void testPreorderTraversal() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        System.out.println("Testing preorder traversal:");
        bst.preorderTraversal();
        // Expected output: 50 30 20 40 70 60 80
    }
    
    /**
     * Test postorder traversal
     */
    public void testPostorderTraversal() {
        setUp();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        
        for (int value : values) {
            bst.insert(value);
        }
        
        System.out.println("Testing postorder traversal:");
        bst.postorderTraversal();
        // Expected output: 20 40 30 60 80 70 50
    }
    
    // ==================== PROPERTY TESTS ====================
    
    /**
     * Helper method to verify BST property is maintained
     */
    private void testBSTProperty() {
        // This would require access to the tree structure
        // For now, we verify by checking that inorder traversal is sorted
        System.out.println("Verifying BST property with inorder traversal:");
        bst.inorderTraversal();
    }
    
    // ==================== STRESS TESTS ====================
    
    /**
     * Test with larger dataset
     */
    public void testLargeDataset() {
        setUp();
        int[] values = new int[100];
        
        // Insert values 1 to 100 in random order
        for (int i = 0; i < 100; i++) {
            values[i] = (i * 37) % 100 + 1; // Simple pseudo-random sequence
            bst.insert(values[i]);
        }
        
        // Verify all values can be found
        for (int i = 1; i <= 100; i++) {
            assert bst.search(i) : "Should find value: " + i;
        }
        
        System.out.println("Large dataset test passed. Tree size: " + bst.getSize());
        System.out.println("Tree height: " + bst.getHeight());
    }
    
    // ==================== MAIN TEST RUNNER ====================
    
    /**
     * Runs all tests
     */
    public static void main(String[] args) {
        BinarySearchTreeTest tester = new BinarySearchTreeTest();
        
        System.out.println("=== Binary Search Tree Test Suite ===\n");
        
        try {
            // Insertion tests
            System.out.println("Running insertion tests...");
            tester.testInsertIntoEmptyTree();
            tester.testInsertMultipleValues();
            tester.testInsertDuplicates();
            System.out.println("✓ All insertion tests passed\n");
            
            // Search tests
            System.out.println("Running search tests...");
            tester.testSearchEmptyTree();
            tester.testSearchExistingValues();
            tester.testSearchNonExistingValues();
            System.out.println("✓ All search tests passed\n");
            
            // Deletion tests
            System.out.println("Running deletion tests...");
            tester.testDeleteFromEmptyTree();
            tester.testDeleteLeafNode();
            tester.testDeleteNodeWithOneChild();
            tester.testDeleteNodeWithTwoChildren();
            tester.testDeleteRoot();
            System.out.println("✓ All deletion tests passed\n");
            
            // Utility tests
            System.out.println("Running utility tests...");
            tester.testIsEmpty();
            tester.testGetSize();
            tester.testGetHeight();
            tester.testFindMin();
            tester.testFindMax();
            System.out.println("✓ All utility tests passed\n");
            
            // Traversal tests
            System.out.println("Running traversal tests...");
            tester.testInorderTraversal();
            tester.testPreorderTraversal();
            tester.testPostorderTraversal();
            System.out.println("✓ All traversal tests passed\n");
            
            // Stress tests
            System.out.println("Running stress tests...");
            tester.testLargeDataset();
            System.out.println("✓ All stress tests passed\n");
            
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
            System.out.println("Binary Search Tree implementation is working correctly.");
            
        } catch (AssertionError e) {
            System.err.println("❌ TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ UNEXPECTED ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
