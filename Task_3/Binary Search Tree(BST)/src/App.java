import java.util.Scanner;

/**
 * Interactive demonstration application for Binary Search Tree.
 * Provides a comprehensive menu-driven interface to test all BST operations
 * including insertion, deletion, search, traversals, and utility functions.
 * 
 * Features:
 * - Interactive menu with 9 different operations
 * - Pre-populated sample data for immediate testing
 * - Input validation and error handling
 * - Automated demo mode for showcasing BST capabilities
 * - User-friendly interface with emojis and clear feedback
 * 
 * @author Aya Gamal
 * @version 1.0
 */
public class App {
    
    /** Static BST instance shared across all methods */
    private static BinarySearchTree bst = new BinarySearchTree();
    
    /** Scanner for reading user input */
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🌳 Binary Search Tree Interactive Demo 🌳");
        System.out.println("==========================================");
        
        // Pre-populate with some sample data
        System.out.println("\nInitializing BST with sample data: [50, 30, 70, 20, 40, 60, 80]");
        int[] sampleData = {50, 30, 70, 20, 40, 60, 80};
        for (int value : sampleData) {
            bst.insert(value);
        }
        
        displayTreeInfo();
        
        // Main menu loop
        while (true) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    insertValue();
                    break;
                case 2:
                    searchValue();
                    break;
                case 3:
                    deleteValue();
                    break;
                case 4:
                    displayTraversals();
                    break;
                case 5:
                    displayTreeInfo();
                    break;
                case 6:
                    findMinMax();
                    break;
                case 7:
                    clearTree();
                    break;
                case 8:
                    runDemo();
                    break;
                case 9:
                    System.out.println("\n👋 Thank you for using BST Demo! Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 BINARY SEARCH TREE OPERATIONS MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. 📥 Insert a value");
        System.out.println("2. 🔍 Search for a value");
        System.out.println("3. 🗑️  Delete a value");
        System.out.println("4. 🚶 Display traversals");
        System.out.println("5. 📊 Show tree information");
        System.out.println("6. 🔢 Find min/max values");
        System.out.println("7. 🧹 Clear the tree");
        System.out.println("8. 🎬 Run automated demo");
        System.out.println("9. 🚪 Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-9): ");
    }
    
    /**
     * Gets user's menu choice with input validation
     */
    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }
    
    /**
     * Handles value insertion
     */
    private static void insertValue() {
        System.out.print("\n📥 Enter value to insert: ");
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            
            boolean existed = bst.search(value);
            bst.insert(value);
            
            if (existed) {
                System.out.println("⚠️  Value " + value + " already exists (duplicates ignored)");
            } else {
                System.out.println("✅ Successfully inserted " + value);
                System.out.println("Updated tree size: " + bst.getSize());
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid integer.");
        }
    }
    
    /**
     * Handles value searching
     */
    private static void searchValue() {
        System.out.print("\n🔍 Enter value to search: ");
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            
            boolean found = bst.search(value);
            if (found) {
                System.out.println("✅ Value " + value + " found in the tree!");
            } else {
                System.out.println("❌ Value " + value + " not found in the tree.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid integer.");
        }
    }
    
    /**
     * Handles value deletion
     */
    private static void deleteValue() {
        if (bst.isEmpty()) {
            System.out.println("❌ Cannot delete from empty tree!");
            return;
        }
        
        System.out.print("\n🗑️  Enter value to delete: ");
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            
            boolean existed = bst.search(value);
            bst.delete(value);
            
            if (existed) {
                System.out.println("✅ Successfully deleted " + value);
                System.out.println("Updated tree size: " + bst.getSize());
            } else {
                System.out.println("⚠️  Value " + value + " was not in the tree.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid integer.");
        }
    }
    
    /**
     * Displays all tree traversals
     */
    private static void displayTraversals() {
        if (bst.isEmpty()) {
            System.out.println("❌ Tree is empty - no traversals to display!");
            return;
        }
        
        System.out.println("\n🚶 TREE TRAVERSALS:");
        System.out.println("-".repeat(40));
        
        System.out.print("📈 ");
        bst.inorderTraversal();
        System.out.println("   (Sorted order)");
        
        System.out.print("📊 ");
        bst.preorderTraversal();
        System.out.println("   (Root first)");
        
        System.out.print("📉 ");
        bst.postorderTraversal();
        System.out.println("   (Root last)");
    }
    
    /**
     * Displays comprehensive tree information
     */
    private static void displayTreeInfo() {
        System.out.println("\n📊 TREE INFORMATION:");
        System.out.println("-".repeat(30));
        System.out.println("📏 Size: " + bst.getSize() + " nodes");
        System.out.println("📐 Height: " + bst.getHeight());
        System.out.println("🔄 Empty: " + (bst.isEmpty() ? "Yes" : "No"));
        
        if (!bst.isEmpty()) {
            System.out.println("⬇️  Minimum: " + bst.findMin());
            System.out.println("⬆️  Maximum: " + bst.findMax());
            
            System.out.print("🔢 Current values (sorted): ");
            bst.inorderTraversal();
        }
    }
    
    /**
     * Finds and displays min/max values
     */
    private static void findMinMax() {
        if (bst.isEmpty()) {
            System.out.println("❌ Tree is empty - no min/max values!");
            return;
        }
        
        System.out.println("\n🔢 MIN/MAX VALUES:");
        System.out.println("-".repeat(25));
        System.out.println("⬇️  Minimum value: " + bst.findMin());
        System.out.println("⬆️  Maximum value: " + bst.findMax());
    }
    
    /**
     * Clears the entire tree
     */
    private static void clearTree() {
        if (bst.isEmpty()) {
            System.out.println("⚠️  Tree is already empty!");
            return;
        }
        
        System.out.print("\n🧹 Are you sure you want to clear the tree? (y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            bst = new BinarySearchTree(); // Create new empty tree
            System.out.println("✅ Tree cleared successfully!");
        } else {
            System.out.println("❌ Clear operation cancelled.");
        }
    }
    
    /**
     * Runs an automated demonstration of BST operations
     */
    private static void runDemo() {
        System.out.println("\n🎬 AUTOMATED BST DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        // Clear and start fresh
        bst = new BinarySearchTree();
        
        // Demo 1: Insertion
        System.out.println("\n1️⃣  INSERTION DEMO:");
        int[] demoValues = {25, 15, 35, 10, 20, 30, 40, 5, 12, 18, 22};
        System.out.print("Inserting values: ");
        for (int value : demoValues) {
            System.out.print(value + " ");
            bst.insert(value);
        }
        System.out.println("\n✅ All values inserted!");
        
        // Demo 2: Tree structure
        System.out.println("\n2️⃣  TREE STRUCTURE:");
        displayTreeInfo();
        
        // Demo 3: Traversals
        System.out.println("\n3️⃣  TRAVERSAL DEMO:");
        displayTraversals();
        
        // Demo 4: Search operations
        System.out.println("\n4️⃣  SEARCH DEMO:");
        int[] searchValues = {20, 50, 5, 100};
        for (int value : searchValues) {
            boolean found = bst.search(value);
            System.out.println("Searching for " + value + ": " + 
                             (found ? "✅ Found" : "❌ Not found"));
        }
        
        // Demo 5: Deletion
        System.out.println("\n5️⃣  DELETION DEMO:");
        int[] deleteValues = {5, 15, 25}; // Leaf, one child, two children
        for (int value : deleteValues) {
            System.out.println("Deleting " + value + "...");
            bst.delete(value);
            System.out.print("Tree after deletion: ");
            bst.inorderTraversal();
        }
        
        System.out.println("\n🎉 Demo completed! Final tree size: " + bst.getSize());
    }
}
