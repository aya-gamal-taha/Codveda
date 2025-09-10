import java.time.LocalDate;

/**
 * Unit tests for the Employee class.
 * Tests all functionality including validation, getters, setters, and edge cases.
 */
public class EmployeeTest {
    
    /**
     * Test valid employee creation.
     */
    public static void testValidEmployeeCreation() {
        System.out.println("Testing valid employee creation...");
        
        try {
            Employee employee = new Employee(1, "John", "Doe", "john.doe@company.com", 
                                           "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            assert employee.getId() == 1 : "ID should be 1";
            assert employee.getFirstName().equals("John") : "First name should be John";
            assert employee.getLastName().equals("Doe") : "Last name should be Doe";
            assert employee.getFullName().equals("John Doe") : "Full name should be John Doe";
            assert employee.getEmail().equals("john.doe@company.com") : "Email should match";
            assert employee.getDepartment().equals("Engineering") : "Department should be Engineering";
            assert employee.getSalary() == 75000 : "Salary should be 75000";
            assert employee.getHireDate().equals(LocalDate.of(2022, 1, 15)) : "Hire date should match";
            assert employee.isActive() : "Employee should be active by default";
            
            System.out.println("✓ Valid employee creation test passed");
        } catch (Exception e) {
            System.out.println("✗ Valid employee creation test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - invalid ID.
     */
    public static void testInvalidId() {
        System.out.println("Testing invalid ID validation...");
        
        try {
            new Employee(0, "John", "Doe", "john.doe@company.com", 
                        "Engineering", 75000, LocalDate.of(2022, 1, 15));
            System.out.println("✗ Invalid ID test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Invalid ID test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Invalid ID test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - null first name.
     */
    public static void testNullFirstName() {
        System.out.println("Testing null first name validation...");
        
        try {
            new Employee(1, null, "Doe", "john.doe@company.com", 
                        "Engineering", 75000, LocalDate.of(2022, 1, 15));
            System.out.println("✗ Null first name test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null first name test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Null first name test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - empty email.
     */
    public static void testEmptyEmail() {
        System.out.println("Testing empty email validation...");
        
        try {
            new Employee(1, "John", "Doe", "", 
                        "Engineering", 75000, LocalDate.of(2022, 1, 15));
            System.out.println("✗ Empty email test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Empty email test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Empty email test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - invalid email format.
     */
    public static void testInvalidEmailFormat() {
        System.out.println("Testing invalid email format validation...");
        
        try {
            new Employee(1, "John", "Doe", "invalid-email", 
                        "Engineering", 75000, LocalDate.of(2022, 1, 15));
            System.out.println("✗ Invalid email format test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Invalid email format test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Invalid email format test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - negative salary.
     */
    public static void testNegativeSalary() {
        System.out.println("Testing negative salary validation...");
        
        try {
            new Employee(1, "John", "Doe", "john.doe@company.com", 
                        "Engineering", -1000, LocalDate.of(2022, 1, 15));
            System.out.println("✗ Negative salary test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Negative salary test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Negative salary test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee validation - future hire date.
     */
    public static void testFutureHireDate() {
        System.out.println("Testing future hire date validation...");
        
        try {
            new Employee(1, "John", "Doe", "john.doe@company.com", 
                        "Engineering", 75000, LocalDate.now().plusDays(1));
            System.out.println("✗ Future hire date test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Future hire date test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Future hire date test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test employee setters with validation.
     */
    public static void testSettersWithValidation() {
        System.out.println("Testing setters with validation...");
        
        try {
            Employee employee = new Employee(1, "John", "Doe", "john.doe@company.com", 
                                           "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            // Test valid updates
            employee.setFirstName("Jane");
            assert employee.getFirstName().equals("Jane") : "First name should be updated to Jane";
            
            employee.setLastName("Smith");
            assert employee.getLastName().equals("Smith") : "Last name should be updated to Smith";
            
            employee.setEmail("jane.smith@company.com");
            assert employee.getEmail().equals("jane.smith@company.com") : "Email should be updated";
            
            employee.setDepartment("Marketing");
            assert employee.getDepartment().equals("Marketing") : "Department should be updated to Marketing";
            
            employee.setSalary(80000);
            assert employee.getSalary() == 80000 : "Salary should be updated to 80000";
            
            System.out.println("✓ Setters with validation test passed");
        } catch (Exception e) {
            System.out.println("✗ Setters with validation test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test employee activation/deactivation.
     */
    public static void testActivationDeactivation() {
        System.out.println("Testing activation/deactivation...");
        
        try {
            Employee employee = new Employee(1, "John", "Doe", "john.doe@company.com", 
                                           "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            assert employee.isActive() : "Employee should be active by default";
            
            employee.deactivate();
            assert !employee.isActive() : "Employee should be inactive after deactivation";
            
            employee.activate();
            assert employee.isActive() : "Employee should be active after activation";
            
            System.out.println("✓ Activation/deactivation test passed");
        } catch (Exception e) {
            System.out.println("✗ Activation/deactivation test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test employee equals and hashCode methods.
     */
    public static void testEqualsAndHashCode() {
        System.out.println("Testing equals and hashCode...");
        
        try {
            Employee employee1 = new Employee(1, "John", "Doe", "john.doe@company.com", 
                                            "Engineering", 75000, LocalDate.of(2022, 1, 15));
            Employee employee2 = new Employee(1, "Jane", "Smith", "jane.smith@company.com", 
                                            "Marketing", 65000, LocalDate.of(2022, 3, 20));
            Employee employee3 = new Employee(2, "John", "Doe", "john.doe@company.com", 
                                            "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            assert employee1.equals(employee2) : "Employees with same ID should be equal";
            assert !employee1.equals(employee3) : "Employees with different ID should not be equal";
            assert employee1.hashCode() == employee2.hashCode() : "Equal employees should have same hash code";
            
            System.out.println("✓ Equals and hashCode test passed");
        } catch (Exception e) {
            System.out.println("✗ Equals and hashCode test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test employee toString method.
     */
    public static void testToString() {
        System.out.println("Testing toString method...");
        
        try {
            Employee employee = new Employee(1, "John", "Doe", "john.doe@company.com", 
                                           "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            String result = employee.toString();
            assert result.contains("John Doe") : "toString should contain full name";
            assert result.contains("john.doe@company.com") : "toString should contain email";
            assert result.contains("Engineering") : "toString should contain department";
            assert result.contains("75000") : "toString should contain salary";
            
            System.out.println("✓ ToString test passed");
        } catch (Exception e) {
            System.out.println("✗ ToString test failed: " + e.getMessage());
        }
    }
    
    /**
     * Run all Employee tests.
     */
    public static void runAllTests() {
        System.out.println("=== Running Employee Tests ===");
        
        testValidEmployeeCreation();
        testInvalidId();
        testNullFirstName();
        testEmptyEmail();
        testInvalidEmailFormat();
        testNegativeSalary();
        testFutureHireDate();
        testSettersWithValidation();
        testActivationDeactivation();
        testEqualsAndHashCode();
        testToString();
        
        System.out.println("=== Employee Tests Completed ===\n");
    }
}
