import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Unit tests for the EmployeeManager class.
 * Tests all CRUD operations, search functionality, validation, and statistics.
 */
public class EmployeeManagerTest {
    
    /**
     * Test employee creation.
     */
    public static void testEmployeeCreation() {
        System.out.println("Testing employee creation...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee employee = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                      "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            assert employee != null : "Employee should not be null";
            assert employee.getId() > 0 : "Employee should have valid ID";
            assert employee.getFirstName().equals("John") : "First name should be John";
            assert employee.getEmail().equals("john.doe@company.com") : "Email should match";
            assert employee.isActive() : "Employee should be active by default";
            
            System.out.println("✓ Employee creation test passed");
        } catch (Exception e) {
            System.out.println("✗ Employee creation test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test duplicate email validation.
     */
    public static void testDuplicateEmailValidation() {
        System.out.println("Testing duplicate email validation...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                 "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            // Try to create another employee with same email
            manager.createEmployee("Jane", "Smith", "john.doe@company.com", 
                                 "Marketing", 65000, LocalDate.of(2022, 3, 20));
            
            System.out.println("✗ Duplicate email test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Duplicate email test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Duplicate email test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test get employee by ID.
     */
    public static void testGetEmployeeById() {
        System.out.println("Testing get employee by ID...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee created = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                    "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            Employee retrieved = manager.getEmployeeById(created.getId()).orElse(null);
            assert retrieved != null : "Retrieved employee should not be null";
            assert retrieved.equals(created) : "Retrieved employee should equal created employee";
            
            Employee notFound = manager.getEmployeeById(999).orElse(null);
            assert notFound == null : "Non-existent employee should return null";
            
            System.out.println("✓ Get employee by ID test passed");
        } catch (Exception e) {
            System.out.println("✗ Get employee by ID test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test get employee by email.
     */
    public static void testGetEmployeeByEmail() {
        System.out.println("Testing get employee by email...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee created = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                    "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            Employee retrieved = manager.getEmployeeByEmail("john.doe@company.com").orElse(null);
            assert retrieved != null : "Retrieved employee should not be null";
            assert retrieved.equals(created) : "Retrieved employee should equal created employee";
            
            Employee notFound = manager.getEmployeeByEmail("nonexistent@company.com").orElse(null);
            assert notFound == null : "Non-existent employee should return null";
            
            System.out.println("✓ Get employee by email test passed");
        } catch (Exception e) {
            System.out.println("✗ Get employee by email test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test get all active employees.
     */
    public static void testGetAllActiveEmployees() {
        System.out.println("Testing get all active employees...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee emp1 = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                 "Engineering", 75000, LocalDate.of(2022, 1, 15));
            Employee emp2 = manager.createEmployee("Jane", "Smith", "jane.smith@company.com", 
                                                 "Marketing", 65000, LocalDate.of(2022, 3, 20));
            
            List<Employee> activeEmployees = manager.getAllActiveEmployees();
            assert activeEmployees.size() == 2 : "Should have 2 active employees";
            assert activeEmployees.contains(emp1) : "Should contain first employee";
            assert activeEmployees.contains(emp2) : "Should contain second employee";
            
            // Deactivate one employee
            manager.deactivateEmployee(emp1.getId());
            activeEmployees = manager.getAllActiveEmployees();
            assert activeEmployees.size() == 1 : "Should have 1 active employee after deactivation";
            assert !activeEmployees.contains(emp1) : "Should not contain deactivated employee";
            assert activeEmployees.contains(emp2) : "Should still contain active employee";
            
            System.out.println("✓ Get all active employees test passed");
        } catch (Exception e) {
            System.out.println("✗ Get all active employees test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test get employees by department.
     */
    public static void testGetEmployeesByDepartment() {
        System.out.println("Testing get employees by department...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee eng1 = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                 "Engineering", 75000, LocalDate.of(2022, 1, 15));
            Employee eng2 = manager.createEmployee("Mike", "Johnson", "mike.johnson@company.com", 
                                                 "Engineering", 80000, LocalDate.of(2022, 2, 10));
            Employee marketing = manager.createEmployee("Jane", "Smith", "jane.smith@company.com", 
                                                       "Marketing", 65000, LocalDate.of(2022, 3, 20));
            
            List<Employee> engineeringEmployees = manager.getEmployeesByDepartment("Engineering");
            assert engineeringEmployees.size() == 2 : "Should have 2 engineering employees";
            assert engineeringEmployees.contains(eng1) : "Should contain first engineering employee";
            assert engineeringEmployees.contains(eng2) : "Should contain second engineering employee";
            
            List<Employee> marketingEmployees = manager.getEmployeesByDepartment("Marketing");
            assert marketingEmployees.size() == 1 : "Should have 1 marketing employee";
            assert marketingEmployees.contains(marketing) : "Should contain marketing employee";
            
            List<Employee> hrEmployees = manager.getEmployeesByDepartment("HR");
            assert hrEmployees.size() == 0 : "Should have 0 HR employees";
            
            System.out.println("✓ Get employees by department test passed");
        } catch (Exception e) {
            System.out.println("✗ Get employees by department test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test update employee.
     */
    public static void testUpdateEmployee() {
        System.out.println("Testing update employee...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee employee = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                     "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            boolean updated = manager.updateEmployee(employee.getId(), "Jane", "Smith", 
                                                   "jane.smith@company.com", "Marketing", 80000.0);
            assert updated : "Update should return true";
            
            Employee updatedEmployee = manager.getEmployeeById(employee.getId()).orElse(null);
            assert updatedEmployee.getFirstName().equals("Jane") : "First name should be updated";
            assert updatedEmployee.getLastName().equals("Smith") : "Last name should be updated";
            assert updatedEmployee.getEmail().equals("jane.smith@company.com") : "Email should be updated";
            assert updatedEmployee.getDepartment().equals("Marketing") : "Department should be updated";
            assert updatedEmployee.getSalary() == 80000 : "Salary should be updated";
            
            boolean notUpdated = manager.updateEmployee(999, "Test", "Test", "test@test.com", "Test", 50000.0);
            assert !notUpdated : "Update of non-existent employee should return false";
            
            System.out.println("✓ Update employee test passed");
        } catch (Exception e) {
            System.out.println("✗ Update employee test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test deactivate and activate employee.
     */
    public static void testDeactivateActivateEmployee() {
        System.out.println("Testing deactivate/activate employee...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee employee = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                     "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            assert employee.isActive() : "Employee should be active initially";
            
            boolean deactivated = manager.deactivateEmployee(employee.getId());
            assert deactivated : "Deactivation should return true";
            assert !employee.isActive() : "Employee should be inactive after deactivation";
            
            boolean activated = manager.activateEmployee(employee.getId());
            assert activated : "Activation should return true";
            assert employee.isActive() : "Employee should be active after activation";
            
            boolean notDeactivated = manager.deactivateEmployee(999);
            assert !notDeactivated : "Deactivation of non-existent employee should return false";
            
            System.out.println("✓ Deactivate/activate employee test passed");
        } catch (Exception e) {
            System.out.println("✗ Deactivate/activate employee test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test search employees by name.
     */
    public static void testSearchEmployeesByName() {
        System.out.println("Testing search employees by name...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee john = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                 "Engineering", 75000, LocalDate.of(2022, 1, 15));
            Employee jane = manager.createEmployee("Jane", "Smith", "jane.smith@company.com", 
                                                 "Marketing", 65000, LocalDate.of(2022, 3, 20));
            Employee johnny = manager.createEmployee("Johnny", "Johnson", "johnny.johnson@company.com", 
                                                   "Engineering", 70000, LocalDate.of(2022, 4, 10));
            
            List<Employee> johnResults = manager.searchEmployeesByName("John");
            assert johnResults.size() == 2 : "Should find 2 employees with 'John' in name";
            assert johnResults.contains(john) : "Should contain John Doe";
            assert johnResults.contains(johnny) : "Should contain Johnny Johnson";
            
            List<Employee> smithResults = manager.searchEmployeesByName("Smith");
            assert smithResults.size() == 1 : "Should find 1 employee with 'Smith' in name";
            assert smithResults.contains(jane) : "Should contain Jane Smith";
            
            List<Employee> noResults = manager.searchEmployeesByName("Wilson");
            assert noResults.size() == 0 : "Should find 0 employees with 'Wilson' in name";
            
            System.out.println("✓ Search employees by name test passed");
        } catch (Exception e) {
            System.out.println("✗ Search employees by name test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test get employees by salary range.
     */
    public static void testGetEmployeesBySalaryRange() {
        System.out.println("Testing get employees by salary range...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            manager.createEmployee("Low", "Salary", "low@company.com", 
                                 "Support", 50000, LocalDate.of(2022, 1, 15));
            Employee mid = manager.createEmployee("Mid", "Salary", "mid@company.com", 
                                                "Engineering", 75000, LocalDate.of(2022, 2, 10));
            manager.createEmployee("High", "Salary", "high@company.com", 
                                 "Management", 100000, LocalDate.of(2022, 3, 20));
            
            List<Employee> midRange = manager.getEmployeesBySalaryRange(60000, 80000);
            assert midRange.size() == 1 : "Should find 1 employee in mid salary range";
            assert midRange.contains(mid) : "Should contain mid salary employee";
            
            List<Employee> allRange = manager.getEmployeesBySalaryRange(40000, 110000);
            assert allRange.size() == 3 : "Should find all 3 employees in wide range";
            
            List<Employee> noRange = manager.getEmployeesBySalaryRange(120000, 150000);
            assert noRange.size() == 0 : "Should find 0 employees in high range";
            
            System.out.println("✓ Get employees by salary range test passed");
        } catch (Exception e) {
            System.out.println("✗ Get employees by salary range test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test salary range validation.
     */
    public static void testSalaryRangeValidation() {
        System.out.println("Testing salary range validation...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            manager.getEmployeesBySalaryRange(80000, 60000); // min > max
            System.out.println("✗ Salary range validation test failed - should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Salary range validation test passed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Salary range validation test failed with unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test get statistics.
     */
    public static void testGetStatistics() {
        System.out.println("Testing get statistics...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                 "Engineering", 80000, LocalDate.of(2022, 1, 15));
            manager.createEmployee("Jane", "Smith", "jane.smith@company.com", 
                                 "Engineering", 70000, LocalDate.of(2022, 2, 10));
            manager.createEmployee("Mike", "Johnson", "mike.johnson@company.com", 
                                 "Marketing", 60000, LocalDate.of(2022, 3, 20));
            
            Map<String, Long> deptCounts = manager.getDepartmentStatistics();
            assert deptCounts.get("Engineering") == 2 : "Engineering should have 2 employees";
            assert deptCounts.get("Marketing") == 1 : "Marketing should have 1 employee";
            
            double avgSalary = manager.getAverageSalary();
            assert Math.abs(avgSalary - 70000) < 0.01 : "Average salary should be 70000";
            
            int activeCount = manager.getActiveEmployeeCount();
            assert activeCount == 3 : "Should have 3 active employees";
            
            System.out.println("✓ Get statistics test passed");
        } catch (Exception e) {
            System.out.println("✗ Get statistics test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test remove employee.
     */
    public static void testRemoveEmployee() {
        System.out.println("Testing remove employee...");
        
        try {
            EmployeeManager manager = new EmployeeManager();
            Employee employee = manager.createEmployee("John", "Doe", "john.doe@company.com", 
                                                     "Engineering", 75000, LocalDate.of(2022, 1, 15));
            
            int employeeId = employee.getId();
            assert manager.getEmployeeById(employeeId) != null : "Employee should exist before removal";
            
            boolean removed = manager.removeEmployee(employeeId);
            assert removed : "Removal should return true";
            assert manager.getEmployeeById(employeeId) == null : "Employee should not exist after removal";
            
            boolean notRemoved = manager.removeEmployee(999);
            assert !notRemoved : "Removal of non-existent employee should return false";
            
            System.out.println("✓ Remove employee test passed");
        } catch (Exception e) {
            System.out.println("✗ Remove employee test failed: " + e.getMessage());
        }
    }
    
    /**
     * Run all EmployeeManager tests.
     */
    public static void runAllTests() {
        System.out.println("=== Running EmployeeManager Tests ===");
        
        testEmployeeCreation();
        testDuplicateEmailValidation();
        testGetEmployeeById();
        testGetEmployeeByEmail();
        testGetAllActiveEmployees();
        testGetEmployeesByDepartment();
        testUpdateEmployee();
        testDeactivateActivateEmployee();
        testSearchEmployeesByName();
        testGetEmployeesBySalaryRange();
        testSalaryRangeValidation();
        testGetStatistics();
        testRemoveEmployee();
        
        System.out.println("=== EmployeeManager Tests Completed ===\n");
    }
}
