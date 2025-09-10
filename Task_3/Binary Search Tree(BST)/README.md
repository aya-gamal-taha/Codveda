# Binary Search Tree (BST) Implementation

A comprehensive Binary Search Tree implementation in Java with full functionality, extensive testing, and clean code practices.

## 🌟 Features

- **Complete BST Operations**: Insert, search, delete with optimal algorithms
- **Tree Traversals**: Inorder, preorder, and postorder traversals
- **Utility Methods**: Size, height, min/max value detection, empty check
- **Robust Error Handling**: Proper exception handling for edge cases
- **Comprehensive Testing**: 24+ test cases covering all scenarios
- **Clean Code**: Well-documented, readable, and maintainable code

## 📁 Project Structure

```
Binary Search Tree(BST)/
├── src/
│   ├── TreeNode.java          # Node class representing BST nodes
│   ├── BinarySearchTree.java  # Main BST implementation
│   └── App.java              # Interactive demo application
├── test/
│   └── BinarySearchTreeTest.java # Comprehensive test suite
├── lib/                       # External libraries (if any)
└── README.md                 # Project documentation
```

## 🏗️ Class Overview

### TreeNode
```java
public class TreeNode {
    int value;           // Node's data value
    TreeNode left;       // Left child reference
    TreeNode right;      // Right child reference
}
```
- Represents individual nodes in the BST
- Simple structure with value and child pointers
- Clean constructor for easy node creation

### BinarySearchTree
```java
public class BinarySearchTree {
    private TreeNode root;  // Root of the BST
    
    // Core operations
    public void insert(int value)
    public boolean search(int value)
    public void delete(int value)
    
    // Traversal methods
    public void inorderTraversal()
    public void preorderTraversal()
    public void postorderTraversal()
    
    // Utility methods
    public boolean isEmpty()
    public int getSize()
    public int getHeight()
    public int findMin()
    public int findMax()
}
```

## 🚀 Quick Start

### Basic Usage Example

```java
// Create a new Binary Search Tree
BinarySearchTree bst = new BinarySearchTree();

// Insert some values
bst.insert(50);
bst.insert(30);
bst.insert(70);
bst.insert(20);
bst.insert(40);
bst.insert(60);
bst.insert(80);

// Search for values
System.out.println(bst.search(40)); // Output: true
System.out.println(bst.search(90)); // Output: false

// Display tree information
System.out.println("Tree size: " + bst.getSize());     // Output: 7
System.out.println("Tree height: " + bst.getHeight()); // Output: 2
System.out.println("Minimum value: " + bst.findMin()); // Output: 20
System.out.println("Maximum value: " + bst.findMax()); // Output: 80

// Traverse the tree
bst.inorderTraversal();  // Output: 20 30 40 50 60 70 80 (sorted order)

// Delete a value
bst.delete(30);
bst.inorderTraversal();  // Output: 20 40 50 60 70 80
```

### Tree Traversal Examples

```java
BinarySearchTree bst = new BinarySearchTree();
int[] values = {50, 30, 70, 20, 40, 60, 80};
for (int value : values) {
    bst.insert(value);
}

// Different traversal methods
bst.inorderTraversal();   // Left → Root → Right: 20 30 40 50 60 70 80
bst.preorderTraversal();  // Root → Left → Right: 50 30 20 40 70 60 80
bst.postorderTraversal(); // Left → Right → Root: 20 40 30 60 80 70 50
```

## 🔧 Compilation and Execution

### Compile the Project
```bash
# Navigate to the project directory
cd "Binary Search Tree(BST)"

# Compile all source files
javac src/*.java

# Compile test files (optional)
javac -cp src test/*.java
```

### Run the Demo Application
```bash
# Run the main application
java -cp src App
```

### Run the Test Suite
```bash
# Run tests with assertions enabled
java -ea -cp src:test BinarySearchTreeTest
```

## 📊 Performance Analysis

### Time Complexity
| Operation | Average Case | Worst Case | Best Case |
|-----------|-------------|------------|-----------|
| Insert    | O(log n)    | O(n)       | O(1)      |
| Search    | O(log n)    | O(n)       | O(1)      |
| Delete    | O(log n)    | O(n)       | O(1)      |
| Traversal | O(n)        | O(n)       | O(n)      |

### Space Complexity
- **Storage**: O(n) - one node per element
- **Recursion Stack**: O(log n) average, O(n) worst case

### When is Worst Case O(n)?
The worst case occurs when the tree becomes completely unbalanced (essentially a linked list):
```
    1
     \
      2
       \
        3
         \
          4
```

## 🧪 Testing

The project includes a comprehensive test suite with 24+ test cases:

### Test Categories
- **Insertion Tests**: Empty tree, multiple values, duplicates
- **Search Tests**: Existing values, non-existing values, empty tree
- **Deletion Tests**: Leaf nodes, single child, two children, root deletion
- **Utility Tests**: isEmpty, getSize, getHeight, findMin, findMax
- **Traversal Tests**: All three traversal methods
- **Stress Tests**: Large datasets (100+ nodes)
- **Edge Cases**: Empty tree operations, exception handling

### Running Tests
```bash
# Enable assertions and run tests
java -ea -cp src:test BinarySearchTreeTest
```

Expected output:
```
=== Binary Search Tree Test Suite ===

Running insertion tests...
✓ All insertion tests passed

Running search tests...
✓ All search tests passed

Running deletion tests...
✓ All deletion tests passed

Running utility tests...
✓ All utility tests passed

Running traversal tests...
✓ All traversal tests passed

Running stress tests...
✓ All stress tests passed

🎉 ALL TESTS PASSED! 🎉
Binary Search Tree implementation is working correctly.
```

## 🎯 Key Implementation Details

### BST Property Maintenance
- **Left Subtree**: All values < root value
- **Right Subtree**: All values > root value
- **No Duplicates**: Duplicate insertions are ignored

### Deletion Algorithm
The deletion operation handles three cases:
1. **Leaf Node**: Simply remove the node
2. **One Child**: Replace node with its child
3. **Two Children**: Replace with inorder successor (smallest value in right subtree)

### Error Handling
- `findMin()` and `findMax()` throw `IllegalStateException` for empty trees
- All operations handle null nodes gracefully
- Robust input validation throughout

## 🏆 Code Quality Features

- **Clean Code Principles**: Meaningful names, single responsibility, proper abstraction
- **Comprehensive Documentation**: JavaDoc comments for all public methods
- **Consistent Formatting**: Proper indentation and spacing
- **Modular Design**: Separate classes for different responsibilities
- **Extensive Testing**: High test coverage with edge cases

## 🔍 Example Tree Structure

After inserting values [50, 30, 70, 20, 40, 60, 80]:

```
        50
       /  \
      30   70
     / \   / \
    20 40 60 80
```

- **Inorder**: 20, 30, 40, 50, 60, 70, 80 (sorted)
- **Preorder**: 50, 30, 20, 40, 70, 60, 80
- **Postorder**: 20, 40, 30, 60, 80, 70, 50

## 🚨 Important Notes

- **Assertions**: Run tests with `-ea` flag to enable assertions
- **Balanced Trees**: This implementation doesn't auto-balance (consider AVL or Red-Black trees for guaranteed O(log n))
- **Thread Safety**: This implementation is not thread-safe
- **Memory**: Each node uses additional memory for left/right pointers

## 🎓 Educational Value

This implementation is perfect for:
- Learning BST concepts and algorithms
- Understanding recursive tree operations
- Practicing clean code principles
- Studying algorithm complexity analysis
- Preparing for technical interviews

## 📝 License

This project is created for educational purposes. Feel free to use, modify, and distribute.

---

**Author**: Aya Gamal 
**Language**: Java  
**Paradigm**: Object-Oriented Programming  
**Data Structure**: Binary Search Tree
