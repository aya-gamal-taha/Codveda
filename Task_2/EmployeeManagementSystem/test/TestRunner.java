/**
 * Test runner to execute all unit tests for the Employee Management System.
 * This class provides a simple way to run all tests and see the results.
 */
public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM - TEST SUITE");
        System.out.println("========================================");
        System.out.println("Running comprehensive unit tests...\n");
        
        try {
            // Run Employee tests
            EmployeeTest.runAllTests();
            
            // Run EmployeeManager tests
            EmployeeManagerTest.runAllTests();
            
            System.out.println("========================================");
            System.out.println("ALL TESTS COMPLETED SUCCESSFULLY!");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("TEST SUITE FAILED WITH ERROR:");
            System.out.println(e.getMessage());
            System.out.println("========================================");
            e.printStackTrace();
        }
    }
}
